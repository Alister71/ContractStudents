package com.students.contractstudents.controller;

import com.students.contractstudents.entity.Student;
import com.students.contractstudents.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentRestController {

    @Autowired
    private StudentRepository studentRepository;

    // Add a new student
    @PostMapping
    public Student addStudent(@RequestBody Student student) {
        return studentRepository.save(student);
    }

    // Get all students
    @GetMapping
    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    // Filter students by isContract
    @GetMapping("/filter")
    public List<Student> getStudentsByIsContract(@RequestParam Boolean isContract) {
        return studentRepository.findByIsContract(isContract);
    }
}