package com.btl.sqa.controller;

import com.btl.sqa.dto.LecturerDTO;
import com.btl.sqa.dto.StudentDTO;
import com.btl.sqa.dto.UserDTO;
import com.btl.sqa.model.Lecturer;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@Log4j2
@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@Rollback(value = true)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@PropertySource("classpath:application.properties")
public class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  private StudentDTO studentDTO = StudentDTO.builder()
      .username("studenttest003")
      .password("test003")
      .role("STUDENT")
      .email("test003@gmail.com")
      .name("test003")
      .gender("MALE")
      .address("Hà Nội")
      .facultyName("Công nghệ thông tin")
      .classId(1)
      .build();

  @Test
  @Order(1)
  public void loginTest1() throws Exception {
    UserDTO userDTO = new UserDTO();
    userDTO.setUsername("");
    userDTO.setPassword("");

    MvcResult result = mockMvc.perform(post("/api/v1/users/login")
        .content(asJsonString(userDTO))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError())
        .andReturn();
    String content = result.getResponse().getContentAsString();
    log.info(content);
  }

  @Test
  @Order(2)
  public void loginTest2() throws Exception {
    UserDTO userDTO = new UserDTO();
    userDTO.setUsername("admin");
    userDTO.setPassword("");

    MvcResult result = mockMvc.perform(post("/api/v1/users/login")
        .content(asJsonString(userDTO))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError())
        .andReturn();
    String content = result.getResponse().getContentAsString();
    log.info(content);
  }

  @Test
  @Order(3)
  public void loginTest3() throws Exception {
    UserDTO userDTO = new UserDTO();
    userDTO.setUsername("admin");
    userDTO.setPassword("1");

    MvcResult result = mockMvc.perform(post("/api/v1/users/login")
        .content(asJsonString(userDTO))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError())
        .andReturn();
    String content = result.getResponse().getContentAsString();
    log.info(content);
  }

  @Test
  @Order(4)
  public void loginTest4() throws Exception {
    UserDTO userDTO = new UserDTO();
    userDTO.setUsername("admin");
    userDTO.setPassword("admin");

    MvcResult result = mockMvc.perform(post("/api/v1/users/login")
        .content(asJsonString(userDTO))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andReturn();
    String content = result.getResponse().getContentAsString();
    log.info(content);
  }

  @Test
  public void createNewStudentCorrectData() throws Exception {
    studentDTO.setPhoneNo("091874982");
    studentDTO.setIdentifyCard("B17DCCN237");

    MvcResult result = mockMvc.perform(post("/api/v1/users/create")
        .content(asJsonString(studentDTO))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andReturn();
    String content = result.getResponse().getContentAsString();
    log.info(content);
  }

  @Test
  public void createNewLecturerCorrectData() throws Exception {
    LecturerDTO lecturerDTO = LecturerDTO.builder()
        .username("tranvanxyz")
        .password("tranvanxyz01")
        .role("LECTURER")
        .email("tranvanxyz@gmail.com")
        .name("Tran Van X")
        .phoneNo("0913123311")
        .gender("MALE")
        .address("Hà Nội")
        .facultyName("Công nghệ thông tin")
        .build();

    MvcResult result = mockMvc.perform(post("/api/v1/users/create")
        .content(asJsonString(lecturerDTO))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andReturn();
    String content = result.getResponse().getContentAsString();
    log.info(content);
  }

  @Test
  public void createNewStudentIncorrectData() throws Exception {
    studentDTO.setIdentifyCard("B17D321CCN");

    MvcResult result = mockMvc.perform(post("/api/v1/users/create")
        .content(asJsonString(studentDTO))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError())
        .andReturn();
    String content = result.getResponse().getContentAsString();
    log.info(content);
  }

  @Test
  public void updateStudent() throws Exception {
    studentDTO.setId(10);
    studentDTO.setPhoneNo("0914781748");
    studentDTO.setDob("10/10/1999");
    studentDTO.setIdentifyCard("B17DCDT457");
    studentDTO.setEmail("tranvanbb@gmail.com");

    MvcResult result = mockMvc.perform(put("/api/v1/users/updateStudent")
        .content(asJsonString(studentDTO))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andReturn();
    String content = result.getResponse().getContentAsString();
    log.info(content);
  }

  @Test
  public void updateLecturer() throws Exception {
    LecturerDTO lecturerDTO = LecturerDTO.builder()
        .dob("10/10/1995")
        .id(11)
        .email("tranvanabcda@gmail.com")
        .build();
    MvcResult result = mockMvc.perform(put("/api/v1/users/updateLecturer")
        .content(asJsonString(lecturerDTO))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andReturn();
    String content = result.getResponse().getContentAsString();
    log.info(content);
  }

  @Test
  public void deleteStudent() throws Exception {
    MvcResult result = mockMvc.perform(delete("/api/v1/users/deleteStudent/{id}", "28")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andReturn();
    String content = result.getResponse().getContentAsString();
    log.info(content);
  }

  @Test
  public void deleteLecturer() throws Exception {
    MvcResult result = mockMvc.perform(delete("/api/v1/users/deleteLecturer/{id}", "11")
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andReturn();
    String content = result.getResponse().getContentAsString();
    log.info(content);
  }

  @Test
  public void getAllStudent() throws Exception {
    MvcResult result = mockMvc.perform(get("/api/v1/users/getAllStudent"))
        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andReturn();
    Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
  }

  @Test
  public void getAllStudentByNameOrStudentCode() throws Exception {
    String name = "Van";
    String studentCode = "B17DCD";
    MvcResult result = mockMvc.perform(get("/api/v1/users/getAllStudent")
        .param("search", studentCode))
        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andReturn();
    Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
  }

  @Test
  public void getAllLecturer() throws Exception {
    MvcResult result = mockMvc.perform(get("/api/v1/users/getAllLecturer"))
        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andReturn();
    Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
  }

  @Test
  public void getAllLecturerByName() throws Exception {
    String name = "Van";
    MvcResult result = mockMvc.perform(get("/api/v1/users/getAllLecturer")
        .param("name", name))
        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andReturn();
    Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}