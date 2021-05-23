package com.btl.sqa.controller;

import com.btl.sqa.dto.SubjectDTO;
import com.btl.sqa.service.SubjectService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequestMapping("/api/v1/subjects")
public class SubjectController {

  @Autowired private SubjectService subjectService;

  @CrossOrigin(origins = "*")
  @GetMapping
  public ResponseEntity<?> getSubjectsByStudent(@RequestParam("studentId") int studentId){
    List<SubjectDTO> subjectDTOS = subjectService.getSubjectByStudentId(studentId);
    return new ResponseEntity<>(subjectDTOS, HttpStatus.OK);
  }
}
