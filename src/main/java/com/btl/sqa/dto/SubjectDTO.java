package com.btl.sqa.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubjectDTO {
  private int id;
  private String subjectName;
  private String codeSubject;
  private float percentCC;
  private float percentTH;
  private float percentBTL;
  private float percentKT;
  private float percentCuoiKy;
}
