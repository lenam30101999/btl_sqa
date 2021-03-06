package com.btl.sqa.model;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Builder
@Table(name = "class")
public class Class implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  @Column(name = "name")
  private String name;

  @Column(name = "location")
  private String location;

  @OneToMany(mappedBy = "room")
  private List<Student> students;

  @ManyToMany(mappedBy = "classModel")
  private List<Subject> points;
}
