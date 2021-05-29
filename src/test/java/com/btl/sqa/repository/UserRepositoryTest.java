package com.btl.sqa.repository;

import com.btl.sqa.model.User;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
@Rollback(value = true)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@PropertySource("classpath:application.properties")
public class UserRepositoryTest {

  @Autowired
  private UserRepository userRepository;

  private final User user = User.builder()
      .username("test003")
      .password("test004")
      .phoneNo("091480196")
      .role("ADMIN")
      .email("test003@gmail.com")
      .name("test3")
      .gender("MALE")
      .address("Hà Nội")
      .build();

  @Test
  @Order(1)
  public void findUserById() {
    User user = userRepository.findUserById(2);
    assertThat(user.getId()).isGreaterThan(0);
  }

  @Test
  @Order(2)
  public void saveNewUser(){
    User saved = userRepository.save(user);
    assertThat(saved.getId()).isGreaterThan(0);
  }

  @Test
  @Order(3)
  public void findUserByUsernameIgnoreCase() {
    String name = "B17DCDT456";
    User user = userRepository.findUserByUsernameIgnoreCase(name);
    assertThat(user.getId()).isGreaterThan(0);
  }

  @Test
  @Order(4)
  public void getAllUser(){
    List<User> users = userRepository.findAll();
    assertThat(users).size().isGreaterThan(0);
  }

  @Test
  @Order(5)
  public void searchAllStudentByName(){
    String name = "Van";
    List<User> users = userRepository.findUsersByNameIsContaining(name);
    assertThat(users.size()).isGreaterThan(0);
  }

  @Test
  @Order(6)
  public void searchUserByAddress(){
    String address = "Ha";
    List<User> users = userRepository.findUsersByAddressIsContaining(address);
    assertThat(users.size()).isGreaterThan(0);
  }
}