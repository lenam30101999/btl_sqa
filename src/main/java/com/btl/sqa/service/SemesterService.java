package com.btl.sqa.service;

import com.btl.sqa.dto.PointDTO;
import com.btl.sqa.dto.SemesterDTO;
import com.btl.sqa.dto.SemesterPointDTO;
import com.btl.sqa.model.Semester;
import com.btl.sqa.model.Student;
import com.btl.sqa.model.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional
public class SemesterService extends BaseService{

  @Autowired
  private PointService pointService;

  public List<Semester> getAllSemester(List<Subject> subjects){
//    List<Integer> subjectIds = subjects.stream().map(p -> p.getId()).collect(toList());
    return semesterRepository.findAll();
  }

  public List<Semester> getAllSemesterByStudentId(int studentId){
    Student student = new Student();
    student.setId(studentId);
    return semesterRepository.findAll();
  }

  public List<SemesterDTO> getAllSemesterByStudent(int studentId){
    List<Semester> result = semesterRepository.findAllByPointsStudentId(studentId);
    return convertSemesterDTOs(result);
  }

  private List<SemesterDTO> convertSemesterDTOs(List<Semester> semesters){
    return semesters.stream().map(modelMapper::convertToSemesterDTO).distinct().collect(Collectors.toList());
  }
}
