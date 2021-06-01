package com.btl.sqa.dto;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {
  private int id;

  @NotNull
  protected String name;

  @NotNull
  protected String username;

  @NotNull
  protected String password;

  protected String dob;

  protected String phoneNo;

  protected String role;

  protected String gender;

  protected String address;

  protected String email;

  private String identifyCard;

  private int classId;

  private String className;

  @NotNull
  private String facultyName;

}
