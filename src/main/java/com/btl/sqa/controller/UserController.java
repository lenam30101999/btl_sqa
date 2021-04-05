package com.btl.sqa.controller;

import com.btl.sqa.dto.MessageResponse;
import com.btl.sqa.dto.UserDTO;
import com.btl.sqa.model.Lecturer;
import com.btl.sqa.model.Student;
import com.btl.sqa.model.User;
import com.btl.sqa.service.LecturerService;
import com.btl.sqa.service.StudentService;
import com.btl.sqa.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Controller
public class UserController {

  @Autowired private UserService userService;
  @Autowired private StudentService studentService;
  @Autowired private LecturerService lecturerService;

  @PostMapping(value = "/")
  public ModelAndView login(@ModelAttribute("user") User user, Model model) {
    ModelMap modelMap = new ModelMap();
    model.addAttribute("user", user);
    User data = userService.userLogin(user.getUsername(), user.getPassword());
    if (Objects.isNull(data)){
      MessageResponse errorMessage = new MessageResponse("Tài khoản không tồn tại");
      modelMap.addAttribute("errorMessage", errorMessage);
      return new ModelAndView("index", modelMap);
    }else if (Objects.isNull(data.getPassword())){
      MessageResponse errorMessage = new MessageResponse("Sai mật khẩu");
      modelMap.addAttribute("errorMessage", errorMessage);
      return new ModelAndView("index", modelMap);
    }
    return modelAndView(data, modelMap);
  }

  @PostMapping(value = "/create")
  public String createUser(@Valid @ModelAttribute("userDTO") UserDTO userDTO, Model model) {
    model.addAttribute("userDTO", userDTO);

    if (userDTO.getRole().equalsIgnoreCase("STUDENT")){
      studentService.addStudent(userDTO);
    }else if (userDTO.getRole().equalsIgnoreCase("LECTURER")){
      lecturerService.addLecturer(userDTO);
    }
    return "home";
  }

  @GetMapping("/getAllStudent")
  public String getAllStudent(Model model) {
    List<Student> students = studentService.getAllStudent();
    model.addAttribute("students", students);
    return "listSV";
  }

  @GetMapping("/getAllLecturer")
  public String getAllLecturer(Model model) {
    List<Lecturer> lecturers = lecturerService.getAllLecturer();
    model.addAttribute("lecturers", lecturers);
    return "listGV";
  }

  //----------INIT VIEW----------//

  @GetMapping("/")
  public String login(Model model) {
    model.addAttribute("user", new User());
    return "index";
  }

  @GetMapping("/student")
  public String student() {
    return "student";
  }


  private ModelAndView modelAndView(User result, ModelMap modelMap){
    modelMap.addAttribute("user", result);
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
