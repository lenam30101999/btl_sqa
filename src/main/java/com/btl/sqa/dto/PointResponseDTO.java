package com.btl.sqa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
@AllArgsConstructor
@Builder
public class PointResponseDTO {
  private Integer id;

  private String studentName;

  private String studentCode;

  private float diemCC;

  private float diemTH;

  private float diemBTL;

  private float diemKT;

  private float diemCuoiKy;

  private float point4;

  private double point10;

  private String semesterName;

  private String pointString;

  private String subjectName;

  private String codeSubject;
}
