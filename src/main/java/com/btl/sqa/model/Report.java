package com.btl.sqa.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "report")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Report implements Serializable {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private int id;

  private String description;

  private String content;

  @Column(name = "createdAt")
  @CreationTimestamp
  private LocalDateTime createdAt;

  @Column(name = "updatedAt")
  @UpdateTimestamp
  private LocalDateTime updatedAt;

  @ManyToMany(mappedBy = "reports")
  private List<Lecturer> lecturers;

}
