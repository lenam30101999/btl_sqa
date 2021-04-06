package com.btl.sqa.controller;

import com.btl.sqa.service.PointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class PointController {

  @Autowired
  private PointService pointService;

}
