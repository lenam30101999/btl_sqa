package com.btl.sqa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "semester")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Semester implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  private String description;

  @OneToMany(mappedBy = "semester")
  private List<Point> points;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(name = "Semester_Subject",
      joinColumns = @JoinColumn(name = "SemesterId"),
      inverseJoinColumns = @JoinColumn(name = "SubjectId")
  )
  List<Subject> subjects;
}
