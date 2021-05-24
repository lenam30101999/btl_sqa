package com.btl.sqa.controller;

import com.btl.sqa.dto.UserDTO;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
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
  public void createUser() {
  }

  @Test
  public void updateStudent() {
  }

  @Test
  public void updateLecturer() {
  }

  @Test
  public void deleteStudent() {
  }

  @Test
  public void deleteLecturer() {
  }

  @Test
  public void getAllStudent() {
  }

  @Test
  public void getAllLecturer() {
  }

  public static String asJsonString(final Object obj) {
    try {
      return new ObjectMapper().writeValueAsString(obj);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}