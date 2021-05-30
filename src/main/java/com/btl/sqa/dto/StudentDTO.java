package com.btl.sqa.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class StudentDTO {
  private int id;

  @NotNull
  protected String name;

  @NotNull
  protected String username;

  @NotNull
  protected String password;

  protected String dob;

  @NotNull
  protected String phoneNo;

  @NotNull
  protected String role;

  protected String gender;

  protected String address;

  protected String email;

  @NotNull
  private String identifyCard;

  private int classId;

  private String className;

  @NotNull
  private String facultyName;

  private Double gpa;
  private Double gpa10;
  private String gpaApha;
}
