package com.students.contractstudents.controller;

import com.students.contractstudents.entity.Student;
import com.students.contractstudents.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentThymeleafController {

    @Autowired
    private StudentRepository studentRepository;

    @GetMapping
    public String listStudents(Model model) {
        List<Student> students = studentRepository.findAll();
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/filter")
    public String filterStudents(@RequestParam Boolean isContract, Model model) {
        List<Student> students = studentRepository.findByIsContract(isContract);
        model.addAttribute("students", students);
        return "students";
    }

    @GetMapping("/add")
    public String addStudentForm() {
        return "add-student";
    }

    @PostMapping("/add")
    public String addStudent(@RequestParam String firstName, @RequestParam String lastName,
                             @RequestParam String groupName, @RequestParam Boolean isContract) {
        Student student = new Student();
        student.setFirstName(firstName);
        student.setLastName(lastName);
        student.setGroupName(groupName);
        student.setIsContract(isContract);
        studentRepository.save(student);
        return "redirect:/students";
    }
}