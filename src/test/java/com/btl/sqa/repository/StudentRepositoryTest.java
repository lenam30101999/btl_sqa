package com.btl.sqa.repository;

import com.btl.sqa.model.Class;
import com.btl.sqa.model.Student;
import com.btl.sqa.model.User;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.domain.Sort;
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
public class StudentRepositoryTest {

  @Autowired
  private StudentRepository studentRepository;

  private final User user = User.builder()
      .username("test005")
      .password("test005")
      .phoneNo("091480198")
      .role("STUDENT")
      .email("test005@gmail.com")
      .name("test5")
      .gender("MALE")
      .address("Hà Nội")
      .build();

  private final Class room = Class.builder()
      .id(1)
      .build();

  @Test
  public void insertNewStudentWithCorrectData() {
    Student student = Student.builder()
        .facultyName("Công nghệ thông tin")
        .identifyCard("B17DCCN123")
        .gpa(0)
        .user(user)
        .room(room)
        .build();
    student = studentRepository.save(student);
    assertThat(student.getId()).isGreaterThan(0);
  }

  @Test
  public void insertNewStudentWithIncorrectData() {
    Student student = Student.builder()
        .facultyName("Công nghệ thông tin")
        .identifyCard("B17123CBW")
        .gpa(0)
        .user(user)
        .room(room)
        .build();
    student = studentRepository.save(student);
    assertThat(student == null);
  }

  @Test
  public void findStudentById() {
    Student student = studentRepository.findStudentById(9);
    assertThat(student.getId()).isGreaterThan(0);
  }

  @Test
  public void findAllByUserNameIsContaining() {
    String name = "Van";
    List<Student> students = studentRepository.findAllByUserNameIsContaining(name);
    assertThat(students).size().isGreaterThan(0);
  }

  @Test
  public void getAllStudentReport(){
    List<Student> students = studentRepository.findAll();
    assertThat(students).size().isGreaterThan(0);
  }

  @Test
  public void getALlStudentReportByClass(){
    List<Student> students = studentRepository.findAll();
    List<Student> studentsByClass = studentRepository.findAll(Sort.by(Sort.Direction.ASC,"room"));
    assertThat(studentsByClass).size().isGreaterThan(0);
    assertThat(studentsByClass).size().isEqualTo(students.size());
  }

  @Test
  public void getALlStudentReportBySchoolarship(){
    Double gpa = 3.5;
    List<Student> students = studentRepository.findAll();
    List<Student> studentsByClass = studentRepository.findByGpaGreaterThan(gpa);
    assertThat(studentsByClass).size().isGreaterThan(0);
    assertThat(studentsByClass).size().isLessThan(students.size());
    assertThat(studentsByClass.stream().findAny().get().getGpa()).isGreaterThan(gpa);
  }

  @Test
  public void getALlStudentReportByFailure(){
    Double gpa = 2.0;
    List<Student> students = studentRepository.findAll();
    List<Student> studentsByClass = studentRepository.findByGpaLessThan(gpa);
    assertThat(studentsByClass).size().isGreaterThan(0);
    assertThat(studentsByClass).size().isLessThan(students.size());
    assertThat(studentsByClass.stream().findAny().get().getGpa()).isLessThan(gpa);
  }
}