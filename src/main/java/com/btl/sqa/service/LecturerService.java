package com.btl.sqa.service;

import com.btl.sqa.dto.LecturerDTO;
import com.btl.sqa.dto.UserDTO;
import com.btl.sqa.model.Lecturer;
import com.btl.sqa.model.User;
import com.btl.sqa.util.ServiceUtil;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@Service
public class LecturerService extends BaseService{

  public void addLecturer(UserDTO userDTO) {
    try {
      userDTO.setRole("LECTURER");
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

  public LecturerDTO updateLecturerInfo(LecturerDTO lecturerDTO){
    try {
      Lecturer updated = getLecturer(lecturerDTO.getId());
      User user = findUserById(lecturerDTO.getId());
      if (Objects.nonNull(user) && Objects.nonNull(updated)){
        user.setAddress(lecturerDTO.getAddress());
        user.setUsername(lecturerDTO.getUsername());
        user.setPassword(lecturerDTO.getPassword());
        user.setEmail(lecturerDTO.getEmail());
        user.setName(lecturerDTO.getName());
        user.setGender(lecturerDTO.getGender());
        user.setPhoneNo(lecturerDTO.getPhoneNo());
        user.setDob(ServiceUtil.formatDate(lecturerDTO.getDob()));

        updated.setFaculty(lecturerDTO.getFacultyName());
        lecturerRepository.saveAndFlush(updated);
        return modelMapper.convertToLecturerDTO(updated);
      }
    }catch (Exception e){
      e.printStackTrace();
    }
    return null;
  }

  public boolean deleteLecturer(int lecturerId) {
    try {
      Lecturer lecturer = getLecturer(lecturerId);
      if (Objects.nonNull(lecturer)){
        lecturerRepository.deleteById(lecturerId);
        return true;
      }
    }catch (Exception e) {
      log.debug(e);
    }
    return false;
  }

  public List<LecturerDTO> findAllLecturerByNameLike(String name){
    List<Lecturer> lecturers = lecturerRepository.findAllByUserNameIsContaining(name);
    return convertToStudentDTOs(lecturers);
  }

  public Lecturer getLecturer(int lecturerId){
    return lecturerRepository.findLecturerById(lecturerId).orElse(null);
  }

  public List<LecturerDTO> getAllLecturer(){
    return convertToStudentDTOs(lecturerRepository.findAll());
  }

  private List<LecturerDTO> convertToStudentDTOs(List<Lecturer> lecturers){
    return lecturers.stream().map(modelMapper::convertToLecturerDTO).collect(Collectors.toList());
  }
}
