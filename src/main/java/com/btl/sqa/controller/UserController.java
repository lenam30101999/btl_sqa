package com.btl.sqa.controller;

import com.btl.sqa.model.User;
import com.btl.sqa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Objects;

@Controller
public class UserController {

  @Autowired
  private UserService userService;

  @PostMapping(value = "/")
  public ModelAndView login(@ModelAttribute("user") User user, Model model) {
    ModelMap modelMap = new ModelMap();
    model.addAttribute("user", user);
    User result = userService.userLogin(user.getUsername(), user.getPassword());
    if (Objects.nonNull(result)){
      modelMap.addAttribute("user", result);
      return modelAndView(result, modelMap);
    }else return new ModelAndView("index");
  }

    @GetMapping("/")
    public String login(Model model) {
      model.addAttribute("user", new User());
    return "index";
  }

  private ModelAndView modelAndView(User result, ModelMap modelMap){
    switch (result.getRole()) {
      case "ADMIN":
        return new ModelAndView("admin", modelMap);
      case "LECTURER":
        return new ModelAndView("lecturer", modelMap);
      case "STUDENT":
        return new ModelAndView("student", modelMap);
      default:
        return new ModelAndView("index");
    }
  }
}
