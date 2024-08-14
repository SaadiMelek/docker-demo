package com.mlk.demo;

import java.util.List;

public interface StudentService {

    Student save(Student s);
    List<Student> findAllStudents();
    Student findByEmail(String email);
    Student update(Student s);
    void delete(String email);

    StudentDto saveStudent(StudentDto dto);// just for unit Test
    List<StudentDto> findAllStudentsDtos();// just for unit Test
    StudentDto findStudentById(Integer id);// just for unit Test
    List<StudentDto> findAllByName(String name);// just for unit Test
}
