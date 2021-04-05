package com.btl.sqa.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user")
@Builder
@ToString
public class User implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name", nullable = false)
  private String name;

  @Column(name = "Username", nullable = false, unique = true)
  private String username;

  @Column(name = "Password", nullable = false)
  private String password;

  @Column(name = "Dob")
  private Date dob;

  @Column(name = "phoneNo", unique = true)
  private String phoneNo;

  @Column(name = "role", nullable = false)
  private String role;

  private String gender;

  private String address;

  private String email;

  @OneToOne(mappedBy = "user")
  private Student student;

  @OneToOne(mappedBy = "user")
  private Lecturer lecturer;
}
