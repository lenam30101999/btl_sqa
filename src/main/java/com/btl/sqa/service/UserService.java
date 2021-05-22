package com.btl.sqa.service;

import com.btl.sqa.dto.MessageResponse;
import com.btl.sqa.model.User;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import javax.transaction.Transactional;
import java.util.Objects;

@Log4j2
@Service
@Transactional
public class UserService extends BaseService{

  public MessageResponse checkLogin(User user){
    MessageResponse message;
    if (user.getUsername().equals("")) {
      message = new MessageResponse( "Vui lòng điền tên tài khoản");
      return message;
    }
    if (user.getPassword().equals("")){
      message = new MessageResponse( "Vui lòng điền mật khẩu");
      return message;
    }
    if (user.getPassword().length() < 5) {
      message = new MessageResponse( "Mật khẩu cần 5 ký tự trở lên");
      return message;
    }
    return null;
  }

  public MessageResponse checkAfterLogin(User user) {
    MessageResponse message;
    if (Objects.isNull(user)){
      message = new MessageResponse("Tài khoản không tồn tại");
      return message;
    }else if (Objects.isNull(user.getPassword())){
      message = new MessageResponse("Sai mật khẩu");
      return message;
    }
    return null;
  }

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

  public User getUser(int userId){
    return userRepository.findUserById(userId);
  }
}
