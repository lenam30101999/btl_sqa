package com.btl.sqa.controller;

import com.btl.sqa.dto.*;
import com.btl.sqa.service.*;
import com.btl.sqa.util.Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Log4j2
@RestController
@RequestMapping("/api/v1/users")
public class UserController {

  @Autowired
  private UserService userService;
  @Autowired
  private StudentService studentService;
  @Autowired
  private LecturerService lecturerService;

  @CrossOrigin(origins = "*")
  @PostMapping(path = "/login", produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> login(@RequestBody UserDTO user) {
    String messageError = userService.checkLogin(user);
    UserDTO data = userService.userLogin(user.getUsername(), user.getPassword());
    String messageError2 = userService.checkAfterLogin(data);

    if (messageError != null) {
      return new ResponseEntity<>(new MessageDTO(messageError), HttpStatus.BAD_REQUEST);
    } else if (messageError2 != null) {
      return new ResponseEntity<>(new MessageDTO(messageError2), HttpStatus.BAD_REQUEST);
    }else {
      return new ResponseEntity<>(data, HttpStatus.OK);
    }
  }

  @CrossOrigin(origins = "*")
  @PostMapping(path = "/create", produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> createUser(@Valid @RequestBody UserDTO userDTO) {
    if (userDTO.getIdentifyCard() != null){
      StudentDTO dto = studentService.addStudent(userDTO);
      if (dto.getName() != null){
        return new ResponseEntity<>(new MessageDTO(Util.ADD_SUCCESS), HttpStatus.OK);
      }else {
        return new ResponseEntity<>(new MessageDTO(dto.getUsername()), HttpStatus.BAD_REQUEST);
      }
    }else {
      LecturerDTO dto = lecturerService.addLecturer(userDTO);
      if (dto.getName() != null){
        return new ResponseEntity<>(new MessageDTO(Util.ADD_SUCCESS), HttpStatus.OK);
      }else {
        return new ResponseEntity<>(new MessageDTO(dto.getUsername()), HttpStatus.BAD_REQUEST);
      }
    }
  }

  @CrossOrigin(origins = "*")
  @PutMapping(path = "/updateStudent", produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> updateStudent(@Valid @RequestBody StudentDTO studentDTO) {
    StudentDTO updated = studentService.updateStudent(studentDTO);
    if (Objects.nonNull(updated)){
      if (Objects.nonNull(updated.getName())) {
        return new ResponseEntity<>(new MessageDTO(Util.UPDATED_SUCCESS), HttpStatus.OK);
      }else if (Objects.nonNull(updated.getUsername())) {
        return new ResponseEntity<>(new MessageDTO(updated.getUsername()), HttpStatus.BAD_REQUEST);
      }
    }
    return new ResponseEntity<>(new MessageDTO(Util.UPDATED_NOT_SUCCESS), HttpStatus.BAD_REQUEST);
  }

  @CrossOrigin(origins = "*")
  @PutMapping(path = "/updateLecturer", produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> updateLecturer(@Valid @RequestBody LecturerDTO lecturerDTO) {
    LecturerDTO updated = lecturerService.updateLecturerInfo(lecturerDTO);
    if (Objects.nonNull(updated)){
      if (Objects.nonNull(updated.getName())) {
        return new ResponseEntity<>(new MessageDTO(Util.UPDATED_SUCCESS), HttpStatus.OK);
      }else if (Objects.nonNull(updated.getUsername())) {
        return new ResponseEntity<>(new MessageDTO(updated.getUsername()), HttpStatus.BAD_REQUEST);
      }
    }
    return new ResponseEntity<>(new MessageDTO(Util.UPDATED_NOT_SUCCESS), HttpStatus.BAD_REQUEST);
  }

  @CrossOrigin(origins = "*")
  @DeleteMapping(path = "/deleteStudent/{id}", produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> deleteStudent(@PathVariable("id") int studentId) {
    boolean deleted = studentService.deleteStudent(studentId);
    if (deleted){
      return new ResponseEntity<>(new MessageDTO(Util.DELETE_SUCCESS), HttpStatus.OK);
    }else {
      return new ResponseEntity<>(new MessageDTO(Util.USER_NOT_FOUND), HttpStatus.BAD_REQUEST);
    }
  }

  @CrossOrigin(origins = "*")
  @DeleteMapping(path = "/deleteLecturer/{id}", produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> deleteLecturer(@PathVariable("id") int lecturerId) {
    boolean deleted = lecturerService.deleteLecturer(lecturerId);
    if (deleted){
      return new ResponseEntity<>(new MessageDTO(Util.DELETE_SUCCESS), HttpStatus.OK);
    }else {
      return new ResponseEntity<>(new MessageDTO(Util.USER_NOT_FOUND), HttpStatus.BAD_REQUEST);
    }
  }

  @CrossOrigin(origins = "*")
  @GetMapping(path = "/getAllStudent", produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> getAllStudent() {
    List<StudentDTO> students = studentService.getAllStudent();
    students.forEach(p -> p.setIdentifyCard(p.getIdentifyCard().toUpperCase()));
    return new ResponseEntity<>(students, HttpStatus.OK);
  }

  @CrossOrigin(origins = "*")
  @GetMapping(path = "/getAllStudent", params = "search", produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> getAllStudentByName(@RequestParam("search") String search) {
    List<StudentDTO> students = studentService.findAllStudentByNameOrStudentCodeLike(search);
    students.forEach(p -> p.setIdentifyCard(p.getIdentifyCard().toUpperCase()));
    return new ResponseEntity<>(students, HttpStatus.OK);
  }

  @CrossOrigin(origins = "*")
  @GetMapping(path = "/getAllLecturer", produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> getAllLecturer() {
    List<LecturerDTO> lecturers = lecturerService.getAllLecturer();
    return new ResponseEntity<>(lecturers, HttpStatus.OK);
  }

  @CrossOrigin(origins = "*")
  @GetMapping(path = "/getAllLecturer", params = "name", produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> getAllLecturerByName(@RequestParam("name") String name) {
    List<LecturerDTO> lecturers = lecturerService.findAllLecturerByNameLike(name);
    return new ResponseEntity<>(lecturers, HttpStatus.OK);
  }

}
