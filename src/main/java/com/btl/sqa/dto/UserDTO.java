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

  @NotNull
  protected String phoneNo;

  @NotNull
  protected String role;

  protected String gender;

  protected String address;

  protected String email;

  @NotNull
  private String identifyCard;

  private String className;

  @NotNull
  private String facultyName;


}
