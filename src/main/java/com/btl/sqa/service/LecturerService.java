package com.btl.sqa.service;

import com.btl.sqa.dto.UserDTO;
import com.btl.sqa.model.Lecturer;
import com.btl.sqa.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Log4j2
@Service
public class LecturerService extends BaseService{

  public void addLecturer(UserDTO userDTO) {
    try {
      User user = saveUser(userDTO);
      Lecturer lecturer = Lecturer.builder()
          .user(user)
          .faculty(userDTO.getFacultyName())
          .build();
      lecturerRepository.save(lecturer);
    }catch (Throwable e){
      log.debug(e);
    }
  }

  public void updateLecturerInfo(Lecturer lecturer){
    try {
      Lecturer updated = getLecturer(lecturer.getId());
      if (Objects.nonNull(updated)){
        updated = Lecturer.builder()
                .user(lecturer.getUser())
                .faculty(lecturer.getFaculty())
                .subjects(lecturer.getSubjects())
                .build();
        lecturerRepository.saveAndFlush(updated);
      }
    }catch (Exception e){
      e.printStackTrace();
    }
  }

  public void deleteLecturer(int lecturerId) {
    try {
      Lecturer lecturer = getLecturer(lecturerId);
      if (Objects.nonNull(lecturer)){
        lecturerRepository.deleteById(lecturerId);
      }
    }catch (Exception e) {
      log.debug(e);
    }
  }

  public Lecturer getLecturer(int lecturerId){
    return lecturerRepository.findById(lecturerId).orElse(null);
  }

  public List<Lecturer> getAllLecturer(){
    return lecturerRepository.findAll();
  }
}
