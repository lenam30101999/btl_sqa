package com.btl.sqa.controller;

import com.btl.sqa.dto.*;
import com.btl.sqa.model.*;
import com.btl.sqa.service.*;
import com.btl.sqa.util.Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Log4j2
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @Autowired private UserService userService;
  @Autowired private StudentService studentService;
  @Autowired private LecturerService lecturerService;

  @CrossOrigin(origins = "*")
  @PostMapping(path = "/login")
  public ResponseEntity<?> login(@RequestBody UserDTO user) {
    String messageError = userService.checkLogin(user);
    UserDTO data = userService.userLogin(user.getUsername(), user.getPassword());
    String messageError2 = userService.checkAfterLogin(data);

    if (messageError == null || messageError2 == null){
      return new ResponseEntity<>(data, HttpStatus.OK);
    }else return new ResponseEntity<>(new MessageDTO(messageError), HttpStatus.BAD_REQUEST);
  }

  @CrossOrigin(origins = "*")
  @PostMapping(path = "/create")
  public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {
    if (userDTO.getIdentifyCard() != null){
      StudentDTO dto = studentService.addStudent(userDTO);
      if (dto != null){
        return new ResponseEntity<>(new MessageDTO(Util.ADD_SUCCESS), HttpStatus.OK);
    }else {
        String messageError = Util.IDENTIFY_CARD_WRONG_FORMAT;
        return new ResponseEntity<>(new MessageDTO(messageError), HttpStatus.BAD_REQUEST);
      }
    }else {
      lecturerService.addLecturer(userDTO);
      return new ResponseEntity<>(new MessageDTO(Util.ADD_SUCCESS), HttpStatus.OK);
    }
  }

  @CrossOrigin(origins = "*")
  @PutMapping(path = "/updateStudent")
  public ResponseEntity<?> updateStudent(@Valid @RequestBody StudentDTO studentDTO) {
    StudentDTO updated = studentService.updateStudent(studentDTO);
    if (Objects.nonNull(updated)){
      return new ResponseEntity<>(new MessageDTO(Util.UPDATED_SUCCESS), HttpStatus.OK);
    }else {
      String messageError = Util.IDENTIFY_CARD_WRONG_FORMAT;
      return new ResponseEntity<>(new MessageDTO(messageError), HttpStatus.BAD_REQUEST);
    }
  }

  @CrossOrigin(origins = "*")
  @PutMapping(path = "/updateLecturer")
  public ResponseEntity<?> updateLecturer(@Valid @RequestBody LecturerDTO lecturerDTO) {
    lecturerService.updateLecturerInfo(lecturerDTO);
    return new ResponseEntity<>(new MessageDTO(Util.UPDATED_SUCCESS), HttpStatus.OK);
  }

  @CrossOrigin(origins = "*")
  @DeleteMapping(path = "/deleteStudent/{id}")
  public ResponseEntity<?> deleteStudent(@PathVariable("id") int studentId) {
    studentService.deleteStudent(studentId);
    return new ResponseEntity<>(new MessageDTO(Util.DELETE_SUCCESS), HttpStatus.OK);
  }

  @CrossOrigin(origins = "*")
  @DeleteMapping(path = "/deleteLecturer/{id}")
  public ResponseEntity<?> deleteLecturer(@PathVariable("id") int lecturerId) {
    lecturerService.deleteLecturer(lecturerId);
    return new ResponseEntity<>(new MessageDTO(Util.DELETE_SUCCESS), HttpStatus.OK);
  }

  @CrossOrigin(origins = "*")
  @GetMapping(path = "/getAllStudent")
  public ResponseEntity<?> getAllStudent() {
    List<StudentDTO> students = studentService.getAllStudent();
    students.forEach(p -> p.setIdentifyCard(p.getIdentifyCard().toUpperCase()));
    return new ResponseEntity<>(students, HttpStatus.OK);
  }

  @CrossOrigin(origins = "*")
  @GetMapping(path = "/getAllStudent", params = "name")
  public ResponseEntity<?> getAllStudentByName(@RequestParam("name") String name) {
    List<StudentDTO> students = studentService.findAllStudentByNameLike(name);
    students.forEach(p -> p.setIdentifyCard(p.getIdentifyCard().toUpperCase()));
    return new ResponseEntity<>(students, HttpStatus.OK);
  }

  @CrossOrigin(origins = "*")
  @GetMapping(path = "/getAllLecturer")
  public ResponseEntity<?> getAllLecturer() {
    List<LecturerDTO> lecturers = lecturerService.getAllLecturer();
    return new ResponseEntity<>(lecturers, HttpStatus.OK);
  }

  @CrossOrigin(origins = "*")
  @GetMapping(path = "/getAllLecturer", params = "name")
  public ResponseEntity<?> getAllLecturerByName(@RequestParam("name") String name) {
    List<LecturerDTO> lecturers = lecturerService.findAllLecturerByNameLike(name);
    return new ResponseEntity<>(lecturers, HttpStatus.OK);
  }

}
