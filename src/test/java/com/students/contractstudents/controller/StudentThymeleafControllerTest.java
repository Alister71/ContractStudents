package com.students.contractstudents.controller;

import com.students.contractstudents.entity.Student;
import com.students.contractstudents.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.ui.Model;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StudentThymeleafControllerTest {

    @InjectMocks
    private StudentThymeleafController controller;

    @Mock
    private StudentRepository studentRepository;

    @Mock
    private Model model;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void listStudents_shouldAddStudentsToModelAndReturnView() {
        List<Student> students = Arrays.asList(new Student(), new Student());
        when(studentRepository.findAll()).thenReturn(students);

        String view = controller.listStudents(model);

        verify(studentRepository).findAll();
        verify(model).addAttribute("students", students);
        assertEquals("students", view);
    }


}
