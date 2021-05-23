package com.btl.sqa.service;

import com.btl.sqa.dto.SemesterDTO;
import com.btl.sqa.dto.SemesterResponse;
import com.btl.sqa.model.Point;
import com.btl.sqa.model.Semester;
import com.btl.sqa.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
public class SemesterService extends BaseService{

  public List<SemesterResponse> getAllSemester(){
    return modelMapper.convertToSemesterResponse(semesterRepository.findAll());
  }

  public List<SemesterDTO> getAllSemesterByStudent(int studentId){
    User user = findUserById(studentId);
    if (user != null){
      List<Semester> result = semesterRepository.findAllByPointsStudentId(studentId);
      remove(result, studentId);
      return convertSemesterDTOs(result);
    }else return null;
  }

  private List<SemesterDTO> convertSemesterDTOs(List<Semester> semesters){
    return semesters.stream().map(modelMapper::convertToSemesterDTO).distinct().collect(Collectors.toList());
  }

  private void remove(List<Semester> semesters, int studentId){
    for (Semester semester : semesters){
      semester.getPoints().removeIf(point -> point.getStudent().getId() != studentId);
    }
  }

}
