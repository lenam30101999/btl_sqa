package com.btl.sqa.service;

import com.btl.sqa.dto.StudentDTO;
import com.btl.sqa.dto.UserDTO;
import com.btl.sqa.model.*;
import com.btl.sqa.model.Class;
import com.btl.sqa.util.ReportUtil;
import com.btl.sqa.util.ServiceUtil;
import com.btl.sqa.util.Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@Service
public class StudentService extends BaseService{

  /**
   * @Description add Student
   * @param studentDTO
   */
  public StudentDTO addStudent(UserDTO studentDTO) {
      try {
        User existing = checkExisting(studentDTO.getUsername());
        if (existing != null){
          StudentDTO dto = new StudentDTO();
          dto.setUsername(Util.ACCOUNT_EXISTS);
          dto.setName("");
          return dto;
        }
        studentDTO = ServiceUtil.checkUsername(studentDTO);
        if (Objects.nonNull(studentDTO)){
          if (ServiceUtil.formatIdentifyCard(studentDTO.getIdentifyCard())){
            studentDTO.setRole("STUDENT");
            User user = saveUser(studentDTO);
            Student student = Student.builder()
                .identifyCard(studentDTO.getIdentifyCard())
                .facultyName(studentDTO.getFacultyName())
                .room(getClass(studentDTO.getClassId()))
                .user(user)
                .gpa(0)
                .points(null)
                .build();

            studentRepository.save(student);
            return modelMapper.convertStudentDTO(student);
          }else {
            StudentDTO dto = new StudentDTO();
            dto.setUsername(Util.IDENTIFY_CARD_WRONG_FORMAT);
            dto.setName("");
            return dto;
          }
      }else {
          StudentDTO dto = new StudentDTO();
          dto.setUsername(Util.CHECK_AGAIN);
          dto.setName("");
          return dto;
        }
    } catch (Exception e) {
      log.debug(e);
    }
    return null;
  }

  public StudentDTO updateStudent(StudentDTO studentDTO){
    try {
      studentDTO = ServiceUtil.checkUsernameStudent(studentDTO);
      if (Objects.nonNull(studentDTO) && ServiceUtil.formatIdentifyCard(studentDTO.getIdentifyCard())){
        Student updated = getStudent(studentDTO.getId());
        User user = findUserById(studentDTO.getId());
        if (Objects.nonNull(user) && Objects.nonNull(updated)){
            user.setAddress(studentDTO.getAddress());
            user.setUsername(studentDTO.getUsername());
            user.setPassword(studentDTO.getPassword());
            user.setEmail(studentDTO.getEmail());
            user.setName(studentDTO.getName());
            user.setGender(studentDTO.getGender());
            user.setPhoneNo(studentDTO.getPhoneNo());
            user.setDob(ServiceUtil.formatDate(studentDTO.getDob()));

            updated.setIdentifyCard(studentDTO.getIdentifyCard());
            updated.setFacultyName(studentDTO.getFacultyName());
            updated.setRoom(getClass(studentDTO.getClassId()));

          updated = studentRepository.saveAndFlush(updated);
          return modelMapper.convertStudentDTO(updated);
        }
      }else {
        StudentDTO dto = new StudentDTO();
        dto.setUsername(Util.CHECK_AGAIN);
        dto.setName("");
        return dto;
      }
    }catch (Exception e) {
      log.debug(e);
    }
    return null;
  }

  public boolean deleteStudent(int studentId) {
    try {
      Student student = getStudent(studentId);
      if (Objects.nonNull(student)){
        userRepository.deleteById(studentId);
        studentRepository.delete(student);
        return true;
      }
    }catch (Exception e) {
      log.debug(e);
    }
    return false;
  }

  public List<StudentDTO> getAllStudent(){
    return convertToStudentDTOs(studentRepository.findAll());
  }

  public List<StudentDTO> findAllStudentByNameOrStudentCodeLike(String search){
    List<Student> students =
        studentRepository.findAllByIdentifyCardContainingOrUserNameContaining(search, search);
    return convertToStudentDTOs(students);
  }

  private Class getClass(int classId){
    return classRepository.findClassById(classId).orElse(null);
  }

  private List<StudentDTO> convertToStudentDTOs(List<Student> students){
    return students.stream().map(modelMapper::convertStudentDTO).collect(Collectors.toList());
  }

  private User checkExisting(String username){
    return userRepository.findUserByUsernameIgnoreCase(username);
  }

  @Transactional
  public List<StudentDTO> getALlStudentReport(){
    List<Student> students = studentRepository.findAll();
    List<StudentDTO> studentDTOS = convertToStudentDTOs(students);
    for(StudentDTO s:studentDTOS){
      s = ReportUtil.gpatoReport(s);
    }
    return studentDTOS;
  }

  @Transactional
  public List<StudentDTO> getALlStudentReportByClass(){
    List<Student> students = studentRepository.findAll(Sort.by(Sort.Direction.ASC,"room"));
    List<StudentDTO> studentDTOS = convertToStudentDTOs(students);
    for(StudentDTO s:studentDTOS){
      s = ReportUtil.gpatoReport(s);
    }
    return studentDTOS;
  }

  @Transactional
  public List<StudentDTO> getALlStudentReportBySchoolarship(Double gpa){
    List<Student> students = studentRepository.findByGpaGreaterThan(gpa);
    List<StudentDTO> studentDTOS = convertToStudentDTOs(students);
    for(StudentDTO s:studentDTOS){
      s = ReportUtil.gpatoReport(s);
    }
    return studentDTOS;
  }
  @Transactional
  public List<StudentDTO> getALlStudentReportByFailure(Double gpa){
    List<Student> students = studentRepository.findByGpaLessThan(gpa);
    List<StudentDTO> studentDTOS = convertToStudentDTOs(students);
    for(StudentDTO s:studentDTOS){
      s = ReportUtil.gpatoReport(s);
    }
    return studentDTOS;
  }
}
