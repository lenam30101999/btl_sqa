package com.btl.sqa.service;

import com.btl.sqa.model.Semester;
import com.btl.sqa.model.Subject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@Transactional
public class SemesterService extends BaseService{

  public List<Semester> getAllSemester(List<Subject> subjects){
    List<Integer> subjectIds = subjects.stream().map(p -> p.getId()).collect(toList());
    return semesterRepository.findDistinctBySubjectsId(subjectIds.get(0));
  }
}
