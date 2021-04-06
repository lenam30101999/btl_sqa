package com.btl.sqa.repository;

import com.btl.sqa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
  Student save(Student student);

  Student findStudentById(int studentId);
}
