package com.btl.sqa.model;

import lombok.*;

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

  @Column(name = "code_subject")
  private String codeSubject;

  @Column(name = "percent_cc")
  private float percentCC;

  @Column(name = "percent_th")
  private float percentTH;

  @Column(name = "percent_btl")
  private float percentBTL;

  @Column(name = "percent_kt")
  private float percentKT;

  @Column(name = "percent_cuoi_ky")
  private float percentCuoiKy;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(name = "subject_class",
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
