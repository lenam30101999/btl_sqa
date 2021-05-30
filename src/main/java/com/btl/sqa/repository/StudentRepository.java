package com.btl.sqa.repository;

import com.btl.sqa.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
  Student save(Student student);

  Student findStudentById(int studentId);

  List<Student> findAllByUserNameIsContaining(String name);

  List<Student> findAllByIdentifyCardContainingOrUserNameContaining(String identifyCard, String name);

  void deleteStudentByIdentifyCard(String identifyCard);

  List<Student> findByGpaGreaterThan(Double gpa);
  List<Student> findByGpaLessThan(Double gpa);
}
