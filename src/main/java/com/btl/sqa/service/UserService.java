package com.btl.sqa.service;

import com.btl.sqa.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Log4j2
@Service
@Transactional
public class UserService extends BaseService{

  public User userLogin(String username, String password) {
    try {
      return userRepository.findUserByUsernameIgnoreCaseAndPassword(username, password).orElse(null);
    }catch (Exception e){
      log.error(e);
      return null;
    }
  }
}
