package com.btl.sqa.service;

import com.btl.sqa.dto.UserDTO;
import com.btl.sqa.model.Class;
import com.btl.sqa.model.Student;
import com.btl.sqa.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.ws.rs.NotFoundException;
import java.util.List;
import java.util.Objects;

@Log4j2
@Service
public class StudentService extends BaseService{

  public Student addStudent(UserDTO studentDTO) {
    User user = saveUser(studentDTO);
    try {
      Student student = Student.builder()
          .identifyCard(studentDTO.getIdentifyCard())
          .facultyName(studentDTO.getFacultyName())
          .room(getClass(studentDTO.getClassName()))
          .user(user)
          .gpa(0)
          .points(null)
          .build();

      student = studentRepository.save(student);
      return student;
    }catch (Exception e) {
      log.debug(e);
    }
    return null;
  }

  public Student updateStudent(Student student){
    try {
      Student updated = getStudent(student.getId());
         if (Objects.nonNull(updated)){
           updated = Student.builder()
                     .identifyCard(student.getIdentifyCard())
                     .facultyName(student.getFacultyName())
                     .room(getClass(student.getRoom().getName()))
                     .user(student.getUser())
                     .gpa(student.getGpa())
                     .points(student.getPoints())
                     .build();

           updated = studentRepository.saveAndFlush(updated);
           return updated;
         }
    }catch (Exception e) {
      log.debug(e);
    }
    return null;
  }

  public void deleteStudent(int studentId) {
    try {
      Student student = getStudent(studentId);
      if (Objects.nonNull(student)){
        studentRepository.deleteById(studentId);
      }
    }catch (Exception e) {
      log.debug(e);
    }
  }

  public Student getStudent(int studentId){
    return studentRepository.findById(studentId).orElse(null);
  }

  public List<Student> getAllStudent(){
    return studentRepository.findAll();
  }

  private Class getClass(String className){
    return classRepository.findByName(className).orElseThrow(() -> new NotFoundException("Class not found!"));
  }
}
