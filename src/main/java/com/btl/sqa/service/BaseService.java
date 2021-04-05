package com.btl.sqa.service;

import com.btl.sqa.repository.*;
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
}
