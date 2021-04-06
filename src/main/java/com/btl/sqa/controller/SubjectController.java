package com.btl.sqa.controller;

import com.btl.sqa.service.SubjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class SubjectController {
  @Autowired
  private SubjectService subjectService;

}
