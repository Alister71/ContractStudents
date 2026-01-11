package com.students.contractstudents.controller;

import com.students.contractstudents.entity.Student;
import com.students.contractstudents.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentRestController.class)
class StudentRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StudentRepository studentRepository;

    private Student student1;
    private Student student2;

    @BeforeEach
    void setUp() {
        student1 = new Student();
        student1.setStudentId(1);
        student1.setFirstName("John");
        student1.setLastName("Doe");
        student1.setGroupName("Group A");
        student1.setIsContract(true);

        student2 = new Student();
        student2.setStudentId(2);
        student2.setFirstName("Jane");
        student2.setLastName("Smith");
        student2.setGroupName("Group B");
        student2.setIsContract(false);
    }

    @Test
    @DisplayName("Add a new student successfully")
    void addStudentSuccessfully() throws Exception {
        Mockito.when(studentRepository.save(any(Student.class))).thenReturn(student1);

        mockMvc.perform(post("/api/students")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("""
                        {
                            "firstName": "John",
                            "lastName": "Doe",
                            "groupName": "Group A",
                            "isContract": true
                        }
                        """))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.studentId", is(1)))
                .andExpect(jsonPath("$.firstName", is("John")))
                .andExpect(jsonPath("$.lastName", is("Doe")))
                .andExpect(jsonPath("$.groupName", is("Group A")))
                .andExpect(jsonPath("$.isContract", is(true)));
    }

}
