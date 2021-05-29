package com.btl.sqa.controller;

import org.junit.Assert;
import org.junit.Test;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@RunWith(SpringRunner.class)
@Rollback(value = true)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@PropertySource("classpath:application.properties")
public class SubjectControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void getSubjectsByStudent() throws Exception {
    MvcResult result = mockMvc.perform(get("/api/v1/subjects")
        .param("studentId", "9"))
        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andReturn();
    Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
  }

  @Test
  public void getSubjectsByStudentHaveNone() throws Exception {
    MvcResult result = mockMvc.perform(get("/api/v1/subjects")
        .param("studentId", "100"))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andReturn();
    Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
  }
}