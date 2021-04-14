package com.btl.sqa.controller;

import com.btl.sqa.dto.PointDTO;
import com.btl.sqa.model.Student;
import com.btl.sqa.repository.StudentRepository;
import com.btl.sqa.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class PointController {

  @Autowired private PointService pointService;
  @Autowired private StudentRepository studentRepository;

  @Transactional
  @GetMapping("/getAllPoint/{id}")
  public String getAllPoint(Model model, @PathVariable Integer id){
    Student student = studentRepository.findStudentById(id);
    List<PointDTO> pointList = pointService.getPointList(student);
    model.addAttribute("student", student);
    model.addAttribute("pointList",pointList);
    return "pointList";
  }

}
