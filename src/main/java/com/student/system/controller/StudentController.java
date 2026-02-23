package com.student.system.controller;

import com.student.system.dto.StudentDto;
import com.student.system.service.StudentService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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

    @GetMapping("/students/new")
    public String newStudent(Model model){
        StudentDto studentDto = new StudentDto();
        model.addAttribute("student", studentDto);
        return "create_student";
    }

    // Binding Result to ensure that if validation has errors then they can be passed on to frontend
    @PostMapping("/students")
    public String createStudent(@Valid @ModelAttribute("student") StudentDto studentDto,
                                BindingResult result,
                                Model model){
        if(result.hasErrors()){
            model.addAttribute("student", studentDto);
            return "create_student";
        }
        studentService.createStudent(studentDto);
        return "redirect:/students";
    }

    @GetMapping("/students/{studentId}/edit")
    public String editStudents(@PathVariable("studentId")Long studentId,
                               Model model)
    {
        StudentDto studentDto = studentService.getById(studentId);
        model.addAttribute("student", studentDto);
        return "edit_student";
    }

    @PostMapping("/students/{studentId}")
    public String editStudents(@Valid @ModelAttribute("student") StudentDto studentDto,
    @PathVariable("studentId") Long studentId,
                               BindingResult result,
                               Model model)
    {
        if(result.hasErrors()){
            model.addAttribute("student", studentDto);
            return "edit_student";
        }
        studentDto.setId(studentId);
        studentService.updateStudent(studentDto);
        return "redirect:/students";
    }
}
