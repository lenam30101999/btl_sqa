package com.btl.sqa.controller;

import com.btl.sqa.dto.PointInputDTO;
import com.btl.sqa.dto.SubjectDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j2;
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
import org.springframework.http.MediaType;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

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
public class PointControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @Test
  public void getAllPointByStudentId() throws Exception {
    MvcResult result = mockMvc.perform(get("/api/v1/points")
        .param("studentId", "9"))
        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andReturn();
    Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
  }

  @Test
  public void getAllPointByStudentIdHaveNone() throws Exception {
    MvcResult result = mockMvc.perform(get("/api/v1/points")
        .param("studentId", "100"))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andReturn();
    Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
  }

  @Test
  public void getAllPointByStudentIdHaveNone2() throws Exception {
    MvcResult result = mockMvc.perform(get("/api/v1/points")
        .param("studentId", ""))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andReturn();
    Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
  }

  @Test
  public void getAllPointByStudentIdAndSubjectName1() throws Exception {
    MvcResult result = mockMvc.perform(get("/api/v1/points")
        .param("studentId", "2")
        .param("subjectName", "Java"))
        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andReturn();
    Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
  }

  @Test
  public void getAllPointByStudentIdAndSubjectName2() throws Exception {
    MvcResult result = mockMvc.perform(get("/api/v1/points")
        .param("studentId", "2")
        .param("subjectName", "C"))
        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andReturn();
    Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
  }

  @Test
  public void getAllPointByStudentIdAndSubjectName3() throws Exception {
    MvcResult result = mockMvc.perform(get("/api/v1/points")
        .param("studentId", "100")
        .param("subjectName", "C"))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andReturn();
    Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
  }

  @Test
  public void getAllPointByStudentIdAndSubjectName4() throws Exception {
    MvcResult result = mockMvc.perform(get("/api/v1/points")
        .param("studentId", "100")
        .param("subjectName", "C"))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andReturn();
    Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
  }

  @Test
  public void getAllPointByStudentIdAndSubjectName5() throws Exception {
    MvcResult result = mockMvc.perform(get("/api/v1/points")
        .param("studentId", "")
        .param("subjectName", "C"))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andReturn();
    Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
  }

  @Test
  public void getAllPointByStudentIdAndSubjectName6() throws Exception {
    MvcResult result = mockMvc.perform(get("/api/v1/points")
        .param("studentId", "2")
        .param("subjectName", ""))
        .andDo(print())
        .andExpect(status().is2xxSuccessful())
        .andReturn();
    Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
  }

  @Test
  public void getAllPointByStudentIdAndSubjectName7() throws Exception {
    MvcResult result = mockMvc.perform(get("/api/v1/points")
        .param("studentId", "")
        .param("subjectName", ""))
        .andDo(print())
        .andExpect(status().is4xxClientError())
        .andReturn();
    Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
  }

  @Test
  @Transactional
  public void inputCorrectPoint() throws Exception {
    PointInputDTO pointInputDTO = PointInputDTO.builder()
        .studentId(9)
        .diemCC(8.5f)
        .diemTH(7)
        .diemBTL(8)
        .diemKT(9)
        .diemCuoiKy(9)
        .subjectId(3)
        .managerId(5)
        .semesterId(2)
        .build();
    MvcResult result = mockMvc.perform(post("/api/v1/points")
        .content(asJsonString(pointInputDTO))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andReturn();
    Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
  }

  @Test
  @Transactional
  public void inputIncorrectPoint1() throws Exception {
    PointInputDTO pointInputDTO = PointInputDTO.builder()
        .studentId(9)
        .diemCC(-2f)
        .diemTH(7)
        .diemBTL(8000)
        .diemKT(9)
        .diemCuoiKy(9)
        .subjectId(3)
        .managerId(5)
        .semesterId(2)
        .build();
    MvcResult result = mockMvc.perform(post("/api/v1/points")
        .content(asJsonString(pointInputDTO))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError())
        .andReturn();
    Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
  }

  @Test
  @Transactional
  public void inputIncorrectPoint2() throws Exception {
    PointInputDTO pointInputDTO = PointInputDTO.builder()
        .studentId(9)
        .diemCC(-2)
        .diemTH(7)
        .diemBTL(8)
        .diemKT(900)
        .diemCuoiKy(9)
        .subjectId(3)
        .managerId(5)
        .semesterId(2)
        .build();
    MvcResult result = mockMvc.perform(post("/api/v1/points")
        .content(asJsonString(pointInputDTO))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError())
        .andReturn();
    Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
  }

  @Test
  @Transactional
  public void configPoint() throws Exception {
    SubjectDTO subjectDTO = SubjectDTO.builder()
        .id(2)
        .percentCC(10)
        .percentTH(10)
        .percentBTL(10)
        .percentKT(10)
        .percentCuoiKy(60)
        .build();
    MvcResult result = mockMvc.perform(put("/api/v1/points/configPoint")
        .content(asJsonString(subjectDTO))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is2xxSuccessful())
        .andReturn();
    Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
  }

  @Test
  @Transactional
  public void configPointError() throws Exception {
    SubjectDTO subjectDTO = SubjectDTO.builder()
        .id(2)
        .percentCC(10)
        .percentTH(-1)
        .percentBTL(20)
        .percentKT(20)
        .percentCuoiKy(40)
        .build();
    MvcResult result = mockMvc.perform(put("/api/v1/points/configPoint")
        .content(asJsonString(subjectDTO))
        .contentType(MediaType.APPLICATION_JSON)
        .accept(MediaType.APPLICATION_JSON))
        .andExpect(status().is4xxClientError())
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