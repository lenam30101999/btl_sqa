package com.btl.sqa.repository;

import com.btl.sqa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
  User findUserByUsernameIgnoreCase(String username);
}
