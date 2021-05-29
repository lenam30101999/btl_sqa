package com.btl.sqa.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SemesterPointDTO {
  private int id;
  private String name;
  private String description;
  private List<PointDTO> pointDTOs;
}
