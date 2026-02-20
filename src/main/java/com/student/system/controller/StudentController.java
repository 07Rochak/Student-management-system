package com.student.system.controller;

import com.student.system.dto.StudentDto;
import com.student.system.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
@AllArgsConstructor
public class StudentController {

    private StudentService studentService;

    @GetMapping("/students")
    public String getAllStudents(Model model){
        List<StudentDto> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "students";
    }
}
