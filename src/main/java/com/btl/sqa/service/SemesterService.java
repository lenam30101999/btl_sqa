package com.btl.sqa.service;

import com.btl.sqa.model.Semester;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SemesterService extends BaseService{

  public List<Semester> getAllSemester(){
    return semesterRepository.findAll();
  }
}
