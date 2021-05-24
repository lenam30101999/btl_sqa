package com.btl.sqa.controller;

import com.btl.sqa.dto.MessageDTO;
import com.btl.sqa.dto.PointInputDTO;
import com.btl.sqa.dto.SemesterDTO;
import com.btl.sqa.dto.SubjectDTO;
import com.btl.sqa.model.Student;
import com.btl.sqa.model.Subject;
import com.btl.sqa.service.PointService;
import com.btl.sqa.service.SemesterService;
import com.btl.sqa.service.SubjectService;
import com.btl.sqa.util.Util;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Objects;

@Log4j2
@RestController
@RequestMapping("/api/v1/points")
public class PointController {

  @Autowired private PointService pointService;
  @Autowired private SemesterService semesterService;
  @Autowired private SubjectService subjectService;

  @CrossOrigin(origins = "*")
  @GetMapping(params = "studentId")
  public ResponseEntity<?> getAllPoint(@RequestParam("studentId") Integer id){
    List<SemesterDTO> semesters = semesterService.getAllSemesterByStudent(id);
    if (Objects.nonNull(semesters)){
      return new ResponseEntity<>(semesters, HttpStatus.OK);
    }else return new ResponseEntity<>(new MessageDTO(Util.USER_NOT_FOUND), HttpStatus.NOT_FOUND);
  }

  @CrossOrigin(origins = "*")
  @GetMapping(params = {"studentId", "subjectName"})
  public ResponseEntity<?> getAllPointAndSubjectName(@RequestParam("studentId") Integer id,
                                                     @RequestParam("subjectName") String subjectName){
    List<SemesterDTO> semesters = semesterService.getAllSemesterByStudentAndSubjectName(id, subjectName);
    if (Objects.nonNull(semesters)){
      return new ResponseEntity<>(semesters, HttpStatus.OK);
    }else return new ResponseEntity<>(new MessageDTO(Util.USER_NOT_FOUND), HttpStatus.NOT_FOUND);
  }

  @CrossOrigin(origins = "*")
  @PostMapping
  public ResponseEntity<?> inputPoint(@Valid @RequestBody PointInputDTO pointInputDTO) {
    pointService.inputPoint(pointInputDTO);
    return new ResponseEntity<>(new MessageDTO(Util.ADD_SUCCESS), HttpStatus.OK);
  }

  @CrossOrigin(origins = "*")
  @PutMapping("/configPoint")
  public ResponseEntity<?> configPoint(@RequestBody SubjectDTO subjectDTO) {
    subjectService.updatePercent(subjectDTO);
    return new ResponseEntity<>(new MessageDTO(Util.UPDATED_SUCCESS), HttpStatus.OK);
  }
}
