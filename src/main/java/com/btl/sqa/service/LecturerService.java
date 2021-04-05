package com.btl.sqa.service;

import com.btl.sqa.dto.UserDTO;
import com.btl.sqa.model.Lecturer;
import com.btl.sqa.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;

@Log4j2
@Service
public class LecturerService extends BaseService{

  public Lecturer addLecturer(UserDTO userDTO) {
    try {
      User user = saveUser(userDTO);
      Lecturer lecturer = Lecturer.builder()
          .user(user)
          .faculty(userDTO.getFacultyName())
          .build();
      lecturer = lecturerRepository.save(lecturer);
      return lecturer;
    }catch (Throwable e){
      log.debug(e);
    }
    return null;
  }

  public List<Lecturer> getAllLecturer(){
    return lecturerRepository.findAll();
  }
}
