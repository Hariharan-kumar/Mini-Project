package com.docker_practice.student.ControllerTest;

import com.docker_practice.student.controler.StudentControler;
import com.docker_practice.student.domain.Students;
import com.docker_practice.student.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import tools.jackson.databind.ObjectMapper;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentControler.class)
public class StudentControllerTest {


    @Autowired
    MockMvc mockMvc;

    @MockitoBean
    private StudentService service;

    @Test
    public void addStudentTest() throws Exception {

        Students student = new Students();
        student.setId("1");
        student.setName("John Doe");
        student.setAge(20);
        student.setDepartment("Computer Science");

        when(service.addStudent(any(Students.class))).thenReturn(student);

        ObjectMapper mapper = new ObjectMapper();
        String jsonString = mapper.writeValueAsString(student);
        mockMvc.perform(post("/student/add")
                .contentType("application/json")
                .content(jsonString))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value("1"))
                .andExpect(jsonPath("$.name").value("John Doe"));


    }

    @Test
    public void getStudentByIdTest() throws Exception {
        Students student = new Students();
        student.setId("1");
        student.setName("John Doe");
        student.setAge(20);
        student.setDepartment("Computer Science");

       when(service.getStudentById("1")).thenReturn(student);
       mockMvc.perform(get("/student/get/{id}", "1")
               .contentType("application/json"))
               .andExpect(status().isOk())
               .andExpect(jsonPath("$.id").value("1"))
               .andExpect(jsonPath("$.name").value("John Doe"));
    }
}
