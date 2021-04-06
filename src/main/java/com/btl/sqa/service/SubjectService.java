package com.btl.sqa.service;

import com.btl.sqa.model.Student;
import com.btl.sqa.model.Subject;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
public class SubjectService extends BaseService{

  public List<Subject> getSubjectByStudentId(int studentId){
    Student student = studentRepository.findStudentById(studentId);
    int classId = student.getRoom().getId();
    List<Subject> subjects = subjectRepository.findSubjectsByClassModelId(classId);
    return subjects;
  }

  public void updatePercent(List<Subject> subjects){
    subjects = subjects.stream().peek(this::updateSubject).collect(Collectors.toList());
    if (subjects.size() > 0){
      log.info("SUBJECT: " + subjects.get(0).getPercentCuoiKy());
    }
  }

  private void updateSubject(Subject subject){
    Subject updated = subjectRepository.findById(subject.getId()).get();
    updated.setPercentBTL(subject.getPercentBTL());
    updated.setPercentCC(subject.getPercentCC());
    updated.setPercentCuoiKy(subject.getPercentCuoiKy());
    updated.setPercentKT(subject.getPercentKT());
    updated.setPercentTH(subject.getPercentTH());
    subjectRepository.saveAndFlush(updated);
  }
}
