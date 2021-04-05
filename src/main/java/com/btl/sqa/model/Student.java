package com.btl.sqa.model;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Student {
  @Id
  protected int id;

  @MapsId
  @OneToOne
  @JoinColumn(name = "id")
  private User user;

  @Column(name = "IdentifyCard", nullable = false, unique = true)
  private String identifyCard;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "class_id", nullable = false)
  private Class room;

  @Column(name = "Faculty")
  private String facultyName;

  private double gpa;

  @OneToMany(mappedBy = "student")
  private List<Point> points;
}
