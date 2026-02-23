package com.student.system.service.impl;

import com.student.system.dto.StudentDto;
import com.student.system.entity.Student;
import com.student.system.mapper.StudentMapper;
import com.student.system.repository.StudentRepository;
import com.student.system.service.StudentService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
@AllArgsConstructor
public class StudentServiceImpl implements StudentService {

    private StudentRepository studentRepository;

    @Override
    public List<StudentDto> getAllStudents() {
        List<Student> students = studentRepository.findAll();
        List<StudentDto> studentDtos = students.stream()
                .map((student)-> StudentMapper.mapToStudentDto(student))
                .collect(toList());
        return studentDtos;
    }

    @Override
    public void createStudent(StudentDto studentDto) {
        Student student = StudentMapper.mapToStudent(studentDto);
        studentRepository.save(student);
    }

    @Override
    public StudentDto getById(Long studentId) {
        Student student = studentRepository.findById(studentId).get();
        StudentDto studentDto = StudentMapper.mapToStudentDto(student);
        return studentDto;
    }

    @Override
    public void updateStudent(StudentDto studentDto) {
        Student student = studentRepository.findById(studentDto.getId()).get();
        student.setFirstName(studentDto.getFirstName());
        student.setLastName(studentDto.getLastName());
        student.setEmail(studentDto.getEmail());
        studentRepository.save(student);
    }

    @Override
    public void deleteStudent(long studentId) {
        Student student = studentRepository.findById(studentId).get();
        studentRepository.delete(student);
    }
}
