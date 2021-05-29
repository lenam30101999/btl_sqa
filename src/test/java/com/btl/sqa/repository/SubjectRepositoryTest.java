package com.btl.sqa.repository;

import com.btl.sqa.model.Subject;
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

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;

@DataJpaTest
@RunWith(SpringRunner.class)
@Rollback(value = true)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@PropertySource("classpath:application.properties")
public class SubjectRepositoryTest {

  @Autowired
  private SubjectRepository subjectRepository;

  @Test
  public void findSubjectsByClassModelId() {
    int classModelId = 1;
    List<Subject> subjects = subjectRepository.findSubjectsByClassModelId(classModelId);
    assertThat(subjects).size().isGreaterThan(0);
  }
}