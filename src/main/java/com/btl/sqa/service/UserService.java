package com.btl.sqa.service;

import com.btl.sqa.dao.UserDAO;
import com.btl.sqa.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Objects;

@Service
@Transactional
public class UserService {

  @Autowired
  private UserDAO userDAO;

  public User userLogin(String username, String password) {
    User user = userDAO.findUserByUsernameAndPassword(username, password).orElse(null);
    return user;
  }
}
