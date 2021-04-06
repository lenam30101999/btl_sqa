package com.btl.sqa.repository;

import com.btl.sqa.model.Semester;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SemesterRepository extends JpaRepository<Semester, Integer> {
  Semester findById(int semesterId);
  List<Semester> findDistinctBySubjectsId(List<Integer> subjectIds);
}
