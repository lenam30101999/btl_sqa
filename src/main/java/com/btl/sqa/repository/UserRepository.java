package com.btl.sqa.repository;

import com.btl.sqa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  Optional<User> findUserByUsernameIgnoreCaseAndPassword(String username, String password);
}
