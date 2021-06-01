package com.btl.sqa.controller;


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
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    public void getAllStudentReport() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/student/getAllStudentReport"))
                .andDo(print())
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
    }

    @Test
    public void getAllStudentReportByClass() throws Exception {
        MvcResult result = mockMvc.perform(get("/api/v1/student/getAllStudentReportByClass"))
            .andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andReturn();
        Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
    }

    @Test
    public void getAllStudentReportBySchoolarship() throws Exception {
        Double gpa = 3.5;
        MvcResult result = mockMvc.perform(get("/api/v1/student/getAllStudentReportBySchoolarship")
            .param("gpa", String.valueOf(gpa)))
            .andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andReturn();
        Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
    }

    @Test
    public void getAllStudentReportByFailure() throws Exception {
        Double gpa = 2.0;
        MvcResult result = mockMvc.perform(get("/api/v1/student/getAllStudentReportByFailure")
            .param("gpa", String.valueOf(gpa)))
            .andDo(print())
            .andExpect(status().is2xxSuccessful())
            .andReturn();
        Assert.assertEquals("application/json;charset=UTF-8", result.getResponse().getContentType());
    }
}
