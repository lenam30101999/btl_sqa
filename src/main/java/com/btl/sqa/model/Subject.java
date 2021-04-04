package com.btl.sqa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "subject")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Subject implements Serializable {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String name;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(name = "Subject_Class",
      joinColumns = @JoinColumn(name = "SubjectId"),
      inverseJoinColumns = @JoinColumn(name = "ClassId")
  )
  private List<Class> classModel;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(name = "Lecturer_Subject",
      joinColumns = @JoinColumn(name = "LecturerId"),
      inverseJoinColumns = @JoinColumn(name = "SubjectId")
  )
  private List<Lecturer> lecturers;

  @ManyToMany(mappedBy = "subjects")
  private List<Semester> semesters;
}
