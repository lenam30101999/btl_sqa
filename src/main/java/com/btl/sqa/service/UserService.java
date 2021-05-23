package com.btl.sqa.service;

import com.btl.sqa.dto.UserDTO;
import com.btl.sqa.model.User;
import com.btl.sqa.util.Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Log4j2
@Service
@Transactional
public class UserService extends BaseService{

  public String checkLogin(UserDTO user){
    if (user.getUsername().equals("")) {
      return Util.FILL_USERNAME;
    }
    if (user.getPassword().equals("")){
      return Util.FILL_PASSWORD;
    }
    if (user.getPassword().length() < 5 || user.getUsername().length() < 5) {
      return Util.WRONG_USERNAME_OR_PASSWORD;
    }
    return null;
  }

  public String checkAfterLogin(UserDTO user) {
    if (Objects.isNull(user)){
      return Util.ACCOUNT_NOT_EXISTS;
    }else if (Objects.isNull(user.getPassword())){
      return Util.PASSWORD_WRONG;
    }
    return null;
  }

  public UserDTO userLogin(String username, String password) {
    try {
      UserDTO userDTO;
      User user = userRepository.findUserByUsernameIgnoreCase(username);
      if (Objects.isNull(user)){
        return null;
      }else if (user.getPassword().equals(password)){
        userDTO = modelMapper.convertToUserDTO(user);
        if (Objects.nonNull(user.getStudent())){
          userDTO.setFacultyName(user.getStudent().getFacultyName());
        }else if (Objects.nonNull(user.getLecturer())){
          userDTO.setFacultyName(user.getLecturer().getFaculty());
        }
        return userDTO;
      }else {
        userDTO = new UserDTO();
        userDTO.setUsername(username);
        return userDTO;
      }
    }catch (Exception e){
      log.error(e);
      return null;
    }
  }

}
