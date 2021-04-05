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

  @Column(name = "diem_cc")
  private float diemCC;

  @Column(name = "diem_th")
  private float diemTH;

  @Column(name = "diem_btl")
  private float diemBTL;

  @Column(name = "diem_kt")
  private float diemKT;

  @Column(name = "diem_cuoi_ky")
  private float diemCuoiKy;

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
