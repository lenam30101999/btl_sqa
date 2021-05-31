package com.btl.sqa.repository;

import com.btl.sqa.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
  List<Subject> findSubjectsByClassModelId(int classModelId);
  List<Subject> findSubjectByNameContaining(String name);
  Optional<Subject> findById(int id);
}
