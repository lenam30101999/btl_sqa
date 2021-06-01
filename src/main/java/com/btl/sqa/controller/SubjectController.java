package com.btl.sqa.controller;

import com.btl.sqa.dto.MessageDTO;
import com.btl.sqa.dto.SubjectDTO;
import com.btl.sqa.service.SubjectService;
import com.btl.sqa.util.Util;
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

  @Autowired
  private SubjectService subjectService;

  @CrossOrigin(origins = "*")
  @GetMapping(params = "studentId", produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> getSubjectsByStudent(@RequestParam("studentId") int studentId){
    List<SubjectDTO> subjectDTOS = subjectService.getSubjectByStudentId(studentId);
    if (subjectDTOS != null){
      return new ResponseEntity<>(subjectDTOS, HttpStatus.OK);
    }else return new ResponseEntity<>(new MessageDTO(Util.USER_NOT_FOUND), HttpStatus.BAD_REQUEST);
  }

  @CrossOrigin(origins = "*")
  @GetMapping(produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> getAllSubject(){
    List<SubjectDTO> subjectDTOS = subjectService.getAllSubject();
    return new ResponseEntity<>(subjectDTOS, HttpStatus.OK);
  }

  @CrossOrigin(origins = "*")
  @GetMapping( params = "search", produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> findAllSubjectByName(@RequestParam("search") String search){
    List<SubjectDTO> subjectDTOS = subjectService.findAllSubjectByName(search);
    return new ResponseEntity<>(subjectDTOS, HttpStatus.OK);
  }

  @CrossOrigin(origins = "*")
  @GetMapping( params = "id", produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> findSubjectById(@RequestParam("id") int id){
    List<SubjectDTO> s=subjectService.findSubjectById(id);
    return new ResponseEntity<>(s, HttpStatus.OK);
  }

}
