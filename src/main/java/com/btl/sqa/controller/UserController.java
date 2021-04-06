package com.btl.sqa.controller;

import com.btl.sqa.dto.MessageResponse;
import com.btl.sqa.dto.PointInputDTO;
import com.btl.sqa.dto.UserDTO;
import com.btl.sqa.model.*;
import com.btl.sqa.service.*;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Log4j2
@Controller
public class UserController {

  @Autowired private UserService userService;
  @Autowired private StudentService studentService;
  @Autowired private LecturerService lecturerService;
  @Autowired private SemesterService semesterService;
  @Autowired private SubjectService subjectService;

  @PostMapping(value = "/")
  public ModelAndView login(@ModelAttribute("user") User user, Model model, HttpSession session) {
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
    return modelAndView(data, modelMap, session);
  }

  @PostMapping(value = "/create")
  public String createUser(@Valid @ModelAttribute("userDTO") UserDTO userDTO, Model model) {
    model.addAttribute("userDTO", userDTO);

    if (userDTO.getRole().equalsIgnoreCase("STUDENT")){
      studentService.addStudent(userDTO);
      List<Student> students = studentService.getAllStudent();
      model.addAttribute("students", students);
      return "listSV";
    }else if (userDTO.getRole().equalsIgnoreCase("LECTURER")){
      lecturerService.addLecturer(userDTO);
      List<Lecturer> lecturers = lecturerService.getAllLecturer();
      model.addAttribute("lecturers", lecturers);
      return "listGV";
    }
    return "home";
  }

  @PostMapping(value = "/updateStudent")
  public String updateStudent(@Valid @ModelAttribute("student") Student student, Model model) {
    model.addAttribute("student", student);
    studentService.updateStudent(student);
    List<Student> students = studentService.getAllStudent();
    model.addAttribute("students", students);
    return "listSV";
  }

  @PostMapping(value = "/inputPoint")
  public String inputPoint(@Valid @ModelAttribute("pointDTO") PointInputDTO pointDTO, Model model) {
    model.addAttribute("pointDTO", pointDTO);
    studentService.inputPoint(pointDTO);
    List<Student> students = studentService.getAllStudent();
    model.addAttribute("students", students);
    return "listSV";
  }

  @PostMapping(value = "/updateLecturer")
  public String updateLecturer(@Valid @ModelAttribute("lecturer") Lecturer lecturer, Model model) {
    model.addAttribute("lecturer", lecturer);
    lecturerService.updateLecturerInfo(lecturer);
    List<Lecturer> lecturers = lecturerService.getAllLecturer();
    model.addAttribute("lecturers", lecturers);
    return "listGV";
  }

  @GetMapping(value = "/deleteStudent")
  public String deleteStudent(@ModelAttribute("student") Student student, Model model) {
    model.addAttribute("student", student);
    studentService.deleteStudent(student.getId());
    List<Student> students = studentService.getAllStudent();
    model.addAttribute("students", students);
    return "listSV";
  }

  @GetMapping(value = "/deleteLecturer")
  public String deleteLecturer(@ModelAttribute("lecturer") Lecturer lecturer, Model model) {
    model.addAttribute("lecturer", lecturer);
    lecturerService.deleteLecturer(lecturer.getId());
    List<Lecturer> lecturers = lecturerService.getAllLecturer();
    model.addAttribute("lecturers", lecturers);
    return "listGV";
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

  @PostMapping("/configPoint")
  public String configPoint(Model model) {
    List<Subject> subjects = (List<Subject>) model.getAttribute("subjects");

    return "point";
  }

  //================== INIT VIEW ==================//

  @GetMapping("/")
  public String login(Model model) {
    model.addAttribute("user", new User());
    return "index";
  }

  @GetMapping("/student")
  public String student() {
    return "student";
  }

  @GetMapping("/inputPoint")
  public String initViewInputPoint(Model model, HttpSession session) {
    User user = getUserFromSession(session);
    List<Subject> subjects = subjectService.getClassByStudentId(user.getId());
    List<Semester> semesters = semesterService.getAllSemester(subjects);

    model.addAttribute("user", user);
    model.addAttribute("semesters", semesters);
    model.addAttribute("subjects", subjects);
    return "point";
  }

  @GetMapping("/configPoint")
  public String initViewConfigPoint(Model model, HttpSession session) {
    User user = getUserFromSession(session);
    List<Subject> subjects = subjectService.getClassByStudentId(user.getId());
    List<Semester> semesters = semesterService.getAllSemester(subjects);

    model.addAttribute("user", user);
    model.addAttribute("semesters", semesters);
    model.addAttribute("subjects", subjects);
    return "point";
  }

  private User getUserFromSession(HttpSession session){
    Integer userId = (Integer) session.getAttribute("userId");
    return userService.getUser(userId);
  }


  private ModelAndView modelAndView(User result, ModelMap modelMap, HttpSession session){
    session.setAttribute("userId", result.getId());
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
