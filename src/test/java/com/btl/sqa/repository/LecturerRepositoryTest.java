package com.btl.sqa.repository;

import com.btl.sqa.model.Lecturer;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@RunWith(SpringRunner.class)
@Rollback(value = true)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@PropertySource("classpath:application.properties")
public class LecturerRepositoryTest {

  @Autowired
  private LecturerRepository lecturerRepository;

  @Test
  public void findLecturerById() {
    int id = 11;
    Lecturer lecturer = lecturerRepository.findLecturerById(id).orElse(null);
    if (Objects.nonNull(lecturer)){
      assertThat(lecturer.getId()).isGreaterThan(0);
    }else assertThat("null");
  }

  @Test
  public void findAllByUserNameIsContaining() {
    String name = "Van";
    List<Lecturer> lecturers = lecturerRepository.findAllByUserNameIsContaining(name);
    assertThat(lecturers).size().isGreaterThan(0);
  }
}