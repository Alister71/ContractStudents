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

    @Test
    void filterStudents_shouldAddFilteredStudentsToModelAndReturnView() {
        List<Student> students = Arrays.asList(new Student());
        when(studentRepository.findByIsContract(true)).thenReturn(students);

        String view = controller.filterStudents(true, model);

        verify(studentRepository).findByIsContract(true);
        verify(model).addAttribute("students", students);
        assertEquals("students", view);
    }

    @Test
    void addStudentForm_shouldReturnAddStudentView() {
        String view = controller.addStudentForm();
        assertEquals("add-student", view);
    }

    @Test
    void addStudent_shouldSaveStudentAndRedirect() {
        String firstName = "John";
        String lastName = "Doe";
        String groupName = "A1";
        Boolean isContract = true;

        String view = controller.addStudent(firstName, lastName, groupName, isContract);

        ArgumentCaptor<Student> captor = ArgumentCaptor.forClass(Student.class);
        verify(studentRepository).save(captor.capture());
        Student saved = captor.getValue();
        assertEquals(firstName, saved.getFirstName());
        assertEquals(lastName, saved.getLastName());
        assertEquals(groupName, saved.getGroupName());
        assertEquals(isContract, saved.getIsContract());
        assertEquals("redirect:/students", view);
    }
}
