package com.btl.sqa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@ToString
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name", nullable = false)
  protected String name;

  @Column(name = "Username", nullable = false)
  protected String username;

  @Column(name = "Password", nullable = false)
  protected String password;

  @Column(name = "Dob")
  protected Date dob;

  protected String phoneNo;

  @Column(name = "role", nullable = false)
  protected String role;

  protected String gender;

  protected String address;

  protected String email;
}
