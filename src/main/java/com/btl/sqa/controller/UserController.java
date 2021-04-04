package com.btl.sqa.controller;

import com.btl.sqa.model.User;
import com.btl.sqa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping(value = "/")
  public String loginPage(@ModelAttribute("user") User user, Model model) {
    model.addAttribute("user", user);
    User result = userService.userLogin(user.getUsername(), user.getPassword());
    System.out.println(user.toString());
    if (Objects.isNull(result)){
      return "index";
    }else return "success";
  }

  @GetMapping("/")
  public String login(Model model) {
    model.addAttribute("user", new User());
    return "index";
  }
}
