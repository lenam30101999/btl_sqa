package com.btl.sqa.service;

import com.btl.sqa.dto.UserDTO;
import com.btl.sqa.model.User;
import com.btl.sqa.repository.*;
import com.btl.sqa.util.ModelMapper;
import com.btl.sqa.util.ServiceUtil;
import org.springframework.beans.factory.annotation.Autowired;

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
//    user = userRepository.save(user);
    return user;
  }
}
