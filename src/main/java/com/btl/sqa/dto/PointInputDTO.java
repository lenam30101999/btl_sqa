package com.btl.sqa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointInputDTO {
  private int userId;

  private float diemCC;

  private float diemTH;

  private float diemBTL;

  private float diemKT;

  private float diemCuoiKy;

  private int subjectId;

  private int managerId;

  private int semesterId;

}
