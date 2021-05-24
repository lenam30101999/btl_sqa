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

  List<Student> findAllByIdentifyCardIsContaining(String identifyCard);

  void deleteStudentByIdentifyCard(String identifyCard);
}
