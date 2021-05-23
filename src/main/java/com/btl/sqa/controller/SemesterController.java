package com.btl.sqa.controller;

import com.btl.sqa.dto.SemesterResponse;
import com.btl.sqa.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/semesters")
public class SemesterController {

  @Autowired
  private SemesterService semesterService;

  @CrossOrigin(origins = "*")
  @GetMapping
  public ResponseEntity<?> getAllSemester(){
    List<SemesterResponse> responses = semesterService.getAllSemester();
    return new ResponseEntity<>(responses, HttpStatus.OK);
  }
}
