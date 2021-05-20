package com.btl.sqa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PointDTO {
  private Integer id;

  private float diemCC;

  private float diemTH;

  private float diemBTL;

  private float diemKT;

  private float diemCuoiKy;

  private float point4;

  private float point10;

  private String pointString;

}
