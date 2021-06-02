package com.btl.sqa.service;

import com.btl.sqa.dto.LecturerDTO;
import com.btl.sqa.dto.UserDTO;
import com.btl.sqa.model.Lecturer;
import com.btl.sqa.model.User;
import com.btl.sqa.util.ServiceUtil;
import com.btl.sqa.util.Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Log4j2
@Service
public class LecturerService extends BaseService{

  public LecturerDTO addLecturer(UserDTO userDTO) {
    try {
      User existing = checkExisting(userDTO.getUsername());
      if (existing != null){
        LecturerDTO dto = new LecturerDTO();
        dto.setUsername(Util.ACCOUNT_EXISTS);
        dto.setName("");
        return dto;
      }
      userDTO = ServiceUtil.checkUsername(userDTO);
      if (Objects.nonNull(userDTO)){
        userDTO.setRole("LECTURER");
        User user = saveUser(userDTO);
        Lecturer lecturer = Lecturer.builder()
            .user(user)
            .faculty(userDTO.getFacultyName())
            .build();
        lecturerRepository.save(lecturer);
        return modelMapper.convertToLecturerDTO(lecturer);
      }else {
        LecturerDTO dto = new LecturerDTO();
        dto.setUsername(Util.CHECK_AGAIN);
        dto.setName("");
        return dto;
      }
    }catch (Throwable e){
      log.debug(e);
    }
    return null;
  }

  public LecturerDTO updateLecturerInfo(LecturerDTO lecturerDTO){
    try {
      lecturerDTO = ServiceUtil.checkUsernameLecturer(lecturerDTO);
      if (Objects.nonNull(lecturerDTO)){
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
      }else {
        LecturerDTO dto = new LecturerDTO();
        dto.setUsername(Util.CHECK_AGAIN);
        dto.setName("");
        return dto;
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

  private User checkExisting(String username){
    return userRepository.findUserByUsernameIgnoreCase(username);
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
