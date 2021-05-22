package com.btl.sqa.controller;

import com.btl.sqa.dto.MessageResponse;
import com.btl.sqa.dto.PointInputDTO;
import com.btl.sqa.dto.SemesterDTO;
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
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

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
    MessageResponse msgError = userService.checkLogin(user);
    if (msgError == null) {
      User data = userService.userLogin(user.getUsername(), user.getPassword());
      msgError = userService.checkAfterLogin(data);
      if (Objects.nonNull(msgError)){
        modelMap.addAttribute("errorMessage", msgError);
        return new ModelAndView("index", modelMap);
      }else {
        return modelAndView(data, modelMap, session);
      }
    }
    modelMap.addAttribute("errorMessage", msgError);
    return new ModelAndView("index", modelMap);
  }

  @PostMapping(value = "/create")
  public RedirectView createUser(@Valid @ModelAttribute("userDTO") UserDTO userDTO, Model model) {
    model.addAttribute("userDTO", userDTO);

    if (userDTO.getIdentifyCard() != null){
      studentService.addStudent(userDTO);
      List<Student> students = studentService.getAllStudent();
      model.addAttribute("students", students);
    }else {
      lecturerService.addLecturer(userDTO);
      List<Lecturer> lecturers = lecturerService.getAllLecturer();
      model.addAttribute("lecturers", lecturers);
    }
    return new RedirectView("/getAllStudent");
  }

  @PostMapping(value = "/updateStudent")
  public String updateStudent(@Valid @ModelAttribute("student") Student student, Model model) {
    model.addAttribute("student", student);
    studentService.updateStudent(student);
    List<Student> students = studentService.getAllStudent();
    model.addAttribute("students", students);
    return "DanhSachSinhVien";
  }

  @PostMapping(value = "/inputPoint")
  public String inputPoint(@Valid @ModelAttribute("pointInputDTO") PointInputDTO pointInputDTO, Model model) {
    model.addAttribute("pointInputDTO", pointInputDTO);
    studentService.inputPoint(pointInputDTO);
    List<Student> students = studentService.getAllStudent();
    model.addAttribute("students", students);
    return "DanhSachSinhVien";
  }

  @PostMapping(value = "/updateLecturer")
  public String updateLecturer(@Valid @ModelAttribute("lecturer") Lecturer lecturer, Model model) {
    model.addAttribute("lecturer", lecturer);
    lecturerService.updateLecturerInfo(lecturer);
    List<Lecturer> lecturers = lecturerService.getAllLecturer();
    model.addAttribute("lecturers", lecturers);
    return "listGV";
  }

  @GetMapping(value = "/inputPoint/{id}")
  public String inputPointView(@PathVariable("id") int studentId, Model model, HttpSession session) {
    User user = getUserFromSession(session);
    List<Subject> subjectsList = subjectService.getSubjectByStudentId(studentId);
    List<String> subjects = subjectsList.stream().map(Subject::getName).collect(toList());
    Student student = studentService.getStudent(studentId);
    model.addAttribute("user", user);
    model.addAttribute("subjects", subjects);
    model.addAttribute("student", student);
    return "NhapDiem";
  }

  @GetMapping(value = "/deleteStudent/{id}")
  public RedirectView deleteStudent(@PathVariable("id") int studentId) {
    studentService.deleteStudent(studentId);
    return new RedirectView("/getAllStudent");
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
  public ModelAndView getAllStudent(HttpSession session) {
    ModelMap modelMap = new ModelMap();
    User user = getUserFromSession(session);
    List<Student> students = studentService.getAllStudent();
    students.stream().peek(p -> p.setIdentifyCard(p.getIdentifyCard().toUpperCase())).collect(toList());
    modelMap.addAttribute("user", user);
    modelMap.addAttribute("students", students);
    return new ModelAndView("DanhSachSinhVien", modelMap);
  }

  @GetMapping("/getAllLecturer")
  public String getAllLecturer(Model model, HttpSession session) {
    User user = getUserFromSession(session);
    List<Lecturer> lecturers = lecturerService.getAllLecturer();
    model.addAttribute("user", user);
    model.addAttribute("lecturers", lecturers);
    return "DanhSachGiaoVien";
  }

  @PostMapping("/configPoint")
  public String configPoint(@ModelAttribute("subjects") List<Subject> subjects, Model model) {
    model.addAttribute("subjects", subjects);
    subjectService.updatePercent(subjects);
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

  @GetMapping("/configPoint")
  public String initViewConfigPoint(Model model, HttpSession session) {
    User user = getUserFromSession(session);
    List<Subject> subjects = subjectService.getSubjectByStudentId(user.getId());
    List<Semester> semesters = semesterService.getAllSemester(subjects);

    model.addAttribute("user", user);
    model.addAttribute("semesters", semesters);
    model.addAttribute("subjects", subjects);
    return "point";
  }

  @GetMapping("/create")
  public String createUserView(Model model, HttpSession session){
    User user = getUserFromSession(session);
    UserDTO userDTO = new UserDTO();
    model.addAttribute("userDTO", userDTO);
    model.addAttribute("user", user);
    return "ThemNguoiDung";
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
        return new ModelAndView("TrangChuQuanLy", modelMap);
      case "LECTURER":
        return new ModelAndView("trangChuSqa", modelMap);
      case "STUDENT":
        List<SemesterDTO> semesters = semesterService.getAllSemesterByStudent(result.getId());
        modelMap.addAttribute("pointList",semesters);
        return new ModelAndView("BangDiemCaNhanToanKhoa", modelMap);
      default:
        return new ModelAndView("index");
    }
  }

}
