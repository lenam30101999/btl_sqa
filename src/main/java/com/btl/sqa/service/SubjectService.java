package com.btl.sqa.service;

import com.btl.sqa.dto.SubjectDTO;
import com.btl.sqa.model.Student;
import com.btl.sqa.model.Subject;
import com.btl.sqa.util.ServiceUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@Service
@Transactional
public class SubjectService extends BaseService{

  public List<SubjectDTO> getSubjectByStudentId(int studentId){
    Student student = studentRepository.findStudentById(studentId);
    if (Objects.isNull(student)){
      return null;
    }
    int classId = student.getRoom().getId();
    List<Subject> subjects = subjectRepository.findSubjectsByClassModelId(classId);
    return subjects.stream().map(modelMapper::convertSubjectDTO).collect(Collectors.toList());
  }

  public List<SubjectDTO> findSubjectById(int id){
    List<Subject> subjects=new ArrayList<>();
    Subject subject =
            subjectRepository.findById(id).get();
    subjects.add(subject);
    List<SubjectDTO> subjectDTOs = subjects.stream().map(modelMapper::convertSubjectDTO).collect(Collectors.toList());
    subjectDTOs.forEach(this::editPercent);
    return subjectDTOs;
  }

  public List<SubjectDTO> findAllSubjectByName(String search){
    List<Subject> subjects =
            subjectRepository.findSubjectByNameContaining(search);
    List<SubjectDTO> subjectDTOs = subjects.stream().map(modelMapper::convertSubjectDTO).collect(Collectors.toList());
    subjectDTOs.forEach(this::editPercent);
    return subjectDTOs;
  }


  public List<SubjectDTO> getAllSubject(){
    List<Subject> subjects = subjectRepository.findAll();
    List<SubjectDTO> subjectDTOs = subjects.stream().map(modelMapper::convertSubjectDTO).collect(Collectors.toList());
    subjectDTOs.forEach(this::editPercent);
    return subjectDTOs;
  }

  private void editPercent(SubjectDTO subjectDTO){
    subjectDTO.setPercentCC(ServiceUtil.checkDecimal(subjectDTO.getPercentCC() * 100));
    subjectDTO.setPercentBTL(ServiceUtil.checkDecimal(subjectDTO.getPercentBTL() * 100));
    subjectDTO.setPercentCuoiKy(ServiceUtil.checkDecimal(subjectDTO.getPercentCuoiKy() * 100.0f));
    subjectDTO.setPercentKT(ServiceUtil.checkDecimal(subjectDTO.getPercentKT() * 100.0f));
    subjectDTO.setPercentTH(ServiceUtil.checkDecimal(subjectDTO.getPercentTH() * 100.0f));
  }

  public SubjectDTO updatePercent(SubjectDTO subjectDTO){
    Subject updated = subjectRepository.findById(subjectDTO.getId()).orElse(null);
    if (Objects.nonNull(updated) && checkPercent(subjectDTO)){
      updated.setPercentBTL(subjectDTO.getPercentBTL() / 100);
      updated.setPercentCC(subjectDTO.getPercentCC() / 100);
      updated.setPercentCuoiKy(subjectDTO.getPercentCuoiKy() / 100);
      updated.setPercentKT(subjectDTO.getPercentKT() / 100);
      updated.setPercentTH(subjectDTO.getPercentTH() / 100);
      subjectRepository.saveAndFlush(updated);
      return modelMapper.convertSubjectDTO(updated);
    }else return null;
  }

  private boolean checkPercent(SubjectDTO subjectDTO){
    return ServiceUtil.formatPercent(subjectDTO.getPercentBTL()) != null &&
        ServiceUtil.formatPercent(subjectDTO.getPercentCC()) != null &&
        ServiceUtil.formatPercent(subjectDTO.getPercentCuoiKy()) != null &&
        ServiceUtil.formatPercent(subjectDTO.getPercentKT()) != null &&
        ServiceUtil.formatPercent(subjectDTO.getPercentTH()) != null;
  }

}
