package com.btl.sqa.dao;

import com.btl.sqa.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserDAO extends JpaRepository<User, Integer> {
  Optional<User> findUserByUsernameAndPassword(String username, String password);
}
