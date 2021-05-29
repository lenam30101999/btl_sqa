package com.btl.sqa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PointInputDTO {
  private int studentId;

  private float diemCC;

  private float diemTH;

  private float diemBTL;

  private float diemKT;

  private float diemCuoiKy;

  private int subjectId;

  private int managerId;

  private int semesterId;

}
