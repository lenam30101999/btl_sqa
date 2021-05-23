package com.btl.sqa.controller;

import com.btl.sqa.dto.ClassDTO;
import com.btl.sqa.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/classrooms")
public class ClassroomController {
  @Autowired
  private ClassService classService;
  
  @GetMapping
  public ResponseEntity<?> getAllClassroom(){
    List<ClassDTO> classDTOS = classService.getAllClass();
    return new ResponseEntity<>(classDTOS, HttpStatus.OK);
  }
}
