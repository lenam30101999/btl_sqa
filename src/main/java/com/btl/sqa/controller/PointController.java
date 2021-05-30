package com.btl.sqa.controller;

import com.btl.sqa.dto.*;
import com.btl.sqa.service.PointService;
import com.btl.sqa.service.SemesterService;
import com.btl.sqa.service.SubjectService;
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
@RequestMapping("/api/v1/points")
public class PointController {

  @Autowired private PointService pointService;
  @Autowired private SemesterService semesterService;
  @Autowired private SubjectService subjectService;

  @CrossOrigin(origins = "*")
  @GetMapping(params = "studentId", produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> getAllPointByStudentId(@RequestParam("studentId") Integer id){
    if (id == null){
      return new ResponseEntity<>(new MessageDTO(Util.USER_NOT_FOUND), HttpStatus.NOT_FOUND);
    }
    List<SemesterDTO> semesters = semesterService.getAllSemesterByStudent(id);
    if (Objects.nonNull(semesters)){
      return new ResponseEntity<>(semesters, HttpStatus.OK);
    }else return new ResponseEntity<>(new MessageDTO(Util.USER_NOT_FOUND), HttpStatus.NOT_FOUND);
  }

  @CrossOrigin(origins = "*")
  @GetMapping(produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> getAllPoint(){
    List<PointResponseDTO> pointResponseDTOs = pointService.getAllPoints();
    return new ResponseEntity<>(pointResponseDTOs, HttpStatus.OK);
  }

  @CrossOrigin(origins = "*")
  @GetMapping(params = {"studentId", "subjectName"}, produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> getAllPointByStudentIdAndSubjectName(@RequestParam("studentId") Integer id,
                                                     @RequestParam("subjectName") String subjectName){
    if (id == null){
      return new ResponseEntity<>(new MessageDTO(Util.USER_NOT_FOUND), HttpStatus.NOT_FOUND);
    }
    List<SemesterDTO> semesters = semesterService.getAllSemesterByStudentAndSubjectName(id, subjectName);
    if (Objects.nonNull(semesters)){
      return new ResponseEntity<>(semesters, HttpStatus.OK);
    }else return new ResponseEntity<>(new MessageDTO(Util.USER_NOT_FOUND), HttpStatus.NOT_FOUND);
  }

  @CrossOrigin(origins = "*")
  @PostMapping(produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> inputPoint(@Valid @RequestBody PointInputDTO pointInputDTO) {
    PointInputDTO dto = pointService.inputPoint(pointInputDTO);
    if (Objects.nonNull(dto)){
      return new ResponseEntity<>(new MessageDTO(Util.ADD_SUCCESS), HttpStatus.OK);
    }else return new ResponseEntity<>(new MessageDTO(Util.ADD_NOT_SUCCESS), HttpStatus.BAD_REQUEST);
  }

  @CrossOrigin(origins = "*")
  @PutMapping(path = "/configPoint", produces = "application/json;charset=UTF-8")
  public ResponseEntity<?> configPoint(@RequestBody SubjectDTO subjectDTO) {
    SubjectDTO dto = subjectService.updatePercent(subjectDTO);
    if (Objects.nonNull(dto)){
      return new ResponseEntity<>(new MessageDTO(Util.UPDATED_SUCCESS), HttpStatus.OK);
    }else {
      return new ResponseEntity<>(new MessageDTO(Util.UPDATED_NOT_SUCCESS), HttpStatus.BAD_REQUEST);
    }
  }
}
