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
@Table(name = "lecturer")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Lecturer {

  @Id
  private int id;

  @OneToOne(fetch = FetchType.LAZY, optional = false)
  @PrimaryKeyJoinColumn(name = "id", referencedColumnName = "id")
  @NotFound(action = NotFoundAction.IGNORE)
  private User user;

  private String faculty;

  @ManyToMany(mappedBy = "lecturers")
  private List<Subject> subjects;

  @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
  @JoinTable(name = "Lecturer_Report",
      joinColumns = @JoinColumn(name = "LecturerId"),
      inverseJoinColumns = @JoinColumn(name = "ReportId")
  )
  private List<Report> reports;
}
