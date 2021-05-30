package com.btl.sqa.controller;

import com.btl.sqa.dto.StudentDTO;
import com.btl.sqa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    @Autowired
     private StudentService studentService;

    @GetMapping("/getAllStudentReport")
    public ResponseEntity<List<StudentDTO>> getAllStudentReport(){
        return ResponseEntity.ok(studentService.getALlStudentReport());
    }

    @GetMapping("/getAllStudentReportByClass")
    public ResponseEntity<List<StudentDTO>> getALlStudentReportByClass(){
        return ResponseEntity.ok(studentService.getALlStudentReportByClass());
    }

    @GetMapping("/getAllStudentReportBySchoolarship")
    public ResponseEntity<List<StudentDTO>> getALlStudentReportBySchoolarship(@RequestParam Double gpa){
        return ResponseEntity.ok(studentService.getALlStudentReportBySchoolarship(gpa));
    }

    @GetMapping("/getAllStudentReportByFailure")
    public ResponseEntity<List<StudentDTO>> getALlStudentReportByFailure(@RequestParam Double gpa){
        return ResponseEntity.ok(studentService.getALlStudentReportByFailure(gpa));
    }
}
