package com.btl.sqa.repository;

import com.btl.sqa.model.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Integer> {
  List<Subject> findSubjectsByClassModelId(int classModelId);
}
