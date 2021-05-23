package com.btl.sqa.repository;

import com.btl.sqa.model.Class;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClassRepository extends JpaRepository<Class, Integer> {
  Optional<Class> findByName(String name);
  Optional<Class> findClassById(int classId);
}
