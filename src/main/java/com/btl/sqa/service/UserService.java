package com.btl.sqa.service;

import com.btl.sqa.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Log4j2
@Service
@Transactional
public class UserService extends BaseService{

  public User userLogin(String username, String password) {
    try {
      User user = userRepository.findUserByUsernameIgnoreCase(username);
      if (Objects.isNull(user)){
        return null;
      }else if (user.getPassword().equals(password)){
        return user;
      }else {
        user = new User();
        user.setUsername(username);
        return user;
      }
    }catch (Exception e){
      log.error(e);
      return null;
    }
  }
}
