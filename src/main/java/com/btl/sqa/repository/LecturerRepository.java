package com.btl.sqa.repository;

import com.btl.sqa.model.Lecturer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LecturerRepository extends JpaRepository<Lecturer, Integer> {
  Optional<Lecturer> findLecturerById(Integer id);
}
