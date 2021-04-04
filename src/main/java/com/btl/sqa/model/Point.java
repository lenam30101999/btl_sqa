package com.btl.sqa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "point")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Point implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;
  private float diemCC;
  private float percentCC;
  private float diemBTL;
  private float percentBTL;
  private float diemKT;
  private float percentKT;
  private float diemCuoiKy;
  private float percentCuoiKy;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "subjectId", nullable = false)
  private Subject subject;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "studentId", nullable = false)
  private Student student;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "managerId", nullable = false)
  private Manager manager;

  @ManyToMany(mappedBy = "points")
  private List<Semester> semesters;
}
