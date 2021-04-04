package com.btl.sqa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected int id;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
  @NotFound(action = NotFoundAction.IGNORE)
  private User user;

  @Column(name = "IdentifyCard", nullable = false)
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
