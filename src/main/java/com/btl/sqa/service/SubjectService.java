package com.btl.sqa.service;

import com.btl.sqa.dto.SubjectDTO;
import com.btl.sqa.model.Student;
import com.btl.sqa.model.Subject;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
public class SubjectService extends BaseService{

  public List<SubjectDTO> getSubjectByStudentId(int studentId){
    Student student = studentRepository.findStudentById(studentId);
    int classId = student.getRoom().getId();
    List<Subject> subjects = subjectRepository.findSubjectsByClassModelId(classId);
    return subjects.stream().map(modelMapper::convertSubjectDTO).collect(Collectors.toList());
  }

  public void updatePercent(SubjectDTO subjectDTO){
    Subject updated = subjectRepository.findById(subjectDTO.getId()).orElse(null);
    if (Objects.nonNull(updated)){
      updated.setPercentBTL(subjectDTO.getPercentBTL());
      updated.setPercentCC(subjectDTO.getPercentCC());
      updated.setPercentCuoiKy(subjectDTO.getPercentCuoiKy());
      updated.setPercentKT(subjectDTO.getPercentKT());
      updated.setPercentTH(subjectDTO.getPercentTH());
      subjectRepository.saveAndFlush(updated);
    }
  }

}
