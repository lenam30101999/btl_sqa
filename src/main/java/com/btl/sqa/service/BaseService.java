package com.btl.sqa.service;

import com.btl.sqa.dto.UserDTO;
import com.btl.sqa.model.*;
import com.btl.sqa.repository.*;
import com.btl.sqa.util.ModelMapper;
import com.btl.sqa.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Objects;

public class BaseService {
  @Autowired protected ClassRepository classRepository;
  @Autowired protected LecturerRepository lecturerRepository;
  @Autowired protected ManagerRepository managerRepository;
  @Autowired protected UserRepository userRepository;
  @Autowired protected PointRepository pointRepository;
  @Autowired protected ReportRepository reportRepository;
  @Autowired protected SemesterRepository semesterRepository;
  @Autowired protected StudentRepository studentRepository;
  @Autowired protected SubjectRepository subjectRepository;
  @Autowired protected ModelMapper modelMapper;
  @Autowired protected ServiceUtil serviceUtil;

  protected User saveUser(UserDTO userDTO) {
    User user = modelMapper.convertStudentToUser(userDTO);
    return user;
  }

  protected User findUserById(int id){
    User user = userRepository.findUserById(id);
    if (Objects.nonNull(user)){
      return user;
    }
    return null;
  }

  public Student getStudent(int studentId){
    return studentRepository.findById(studentId).orElse(null);
  }

  protected Subject getSubject(int subjectId){
    return subjectRepository.findById(subjectId).orElse(null);
  }

  protected Manager getManager(int managerId){
    return managerRepository.findById(managerId).orElse(null);
  }

  protected Semester getSemester(int semesterId){
    return semesterRepository.findById(semesterId);
  }
}
