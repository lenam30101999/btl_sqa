package com.btl.sqa.service;

import com.btl.sqa.dto.ClassDTO;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ClassService extends BaseService{
  public List<ClassDTO> getAllClass(){
    return modelMapper.convertToClassDTO(classRepository.findAll());
  }
}
