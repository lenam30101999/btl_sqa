package com.btl.sqa.controller;

import com.btl.sqa.dto.PointDTO;
import com.btl.sqa.dto.SemesterDTO;
import com.btl.sqa.model.Semester;
import com.btl.sqa.model.Student;
import com.btl.sqa.repository.StudentRepository;
import com.btl.sqa.service.PointService;
import com.btl.sqa.service.SemesterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PointController {

  @Autowired private StudentRepository studentRepository;
  @Autowired private SemesterService semesterService;

  @Transactional
  @GetMapping("/getAllPoint/{id}")
  public String getAllPoint(Model model, @PathVariable Integer id){
    Student student = studentRepository.findStudentById(id);
    List<SemesterDTO> semesters = semesterService.getAllSemesterByStudent(id);
    model.addAttribute("student", student);
    model.addAttribute("pointList",semesters);
    return "BangDiemCaNhanToanKhoa";
  }

}
