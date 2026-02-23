package com.student.system.service;

import com.student.system.dto.StudentDto;
import jakarta.validation.Valid;

import java.util.List;

public interface StudentService {
    List<StudentDto> getAllStudents();
    void createStudent(StudentDto studentDto);

    StudentDto getById(Long studentId);

    void updateStudent(@Valid StudentDto studentDto);

    void deleteStudent(long studentId);
}
