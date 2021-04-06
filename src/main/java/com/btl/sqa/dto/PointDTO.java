package com.btl.sqa.dto;

import com.btl.sqa.model.Student;
import com.btl.sqa.model.Subject;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointDTO {
  private Integer id;

  private float point4;

  private float point10;

  private String pointApla;

  private Subject subject;

  private Student student;
}
