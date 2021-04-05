package com.btl.sqa.model;

import lombok.*;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "lecturer")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lecturer {

  @Id
  private int id;

  @MapsId
  @OneToOne
  @JoinColumn(name = "id")
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
