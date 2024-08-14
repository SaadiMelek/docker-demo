package com.mlk.demo;

import org.springframework.stereotype.Service;

@Service
public class StudentMapper {

    public Student toStudent(StudentDto dto) {
        if (dto == null) {
            throw new NullPointerException("The student Dto should not be NULL");
        }
        var student = new Student();
        student.setFirstname(dto.getFirstname()/*.toUpperCase()*/);
        student.setLastname(dto.getLastname());
        student.setEmail(dto.getEmail());
        return student;
    }

    public StudentDto toStudentDto(Student student) {
        StudentDto dto = new StudentDto();
        dto.setFirstname(student.getFirstname());
        dto.setLastname(student.getLastname());
        dto.setEmail(student.getEmail());
        return dto;
    }
}