package com.btl.sqa.service;

import com.btl.sqa.model.Student;
import com.btl.sqa.model.Subject;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class SubjectService extends BaseService{

  public List<Subject> getClassByStudentId(int studentId){
    Student student = studentRepository.findStudentById(studentId);
    int classId = student.getRoom().getId();
    List<Subject> subjects = subjectRepository.findSubjectsByClassModelId(classId);
    return subjects;
  }
}
