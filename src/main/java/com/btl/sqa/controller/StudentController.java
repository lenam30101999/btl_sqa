package com.btl.sqa.controller;

import com.btl.sqa.dto.StudentDTO;
import com.btl.sqa.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/student")
public class StudentController {
    @Autowired
     private StudentService studentService;

    @GetMapping(value = "/getAllStudentReport", produces = "application/json;charset=UTF-8")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<StudentDTO>> getAllStudentReport(){
        return ResponseEntity.ok(studentService.getALlStudentReport());
    }

    @GetMapping(value = "/getAllStudentReportByClass", produces = "application/json;charset=UTF-8")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<StudentDTO>> getALlStudentReportByClass(){
        return ResponseEntity.ok(studentService.getALlStudentReportByClass());
    }

    @GetMapping(value = "/getAllStudentReportBySchoolarship", produces = "application/json;charset=UTF-8")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<StudentDTO>> getALlStudentReportBySchoolarship(@RequestParam Double gpa){
        return ResponseEntity.ok(studentService.getALlStudentReportBySchoolarship(gpa));
    }

    @GetMapping(value = "/getAllStudentReportByFailure", produces = "application/json;charset=UTF-8")
    @CrossOrigin(origins = "*")
    public ResponseEntity<List<StudentDTO>> getALlStudentReportByFailure(@RequestParam Double gpa){
        return ResponseEntity.ok(studentService.getALlStudentReportByFailure(gpa));
    }
}
