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
@Table(name = "manager")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Manager {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  protected int id;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
  @NotFound(action = NotFoundAction.IGNORE)
  private User user;

  private String position;

  @OneToMany(mappedBy = "manager")
  private List<Point> points;
}
