package com.mlk.demo;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;
    private final StudentMapper studentMapper;

    public StudentServiceImpl(StudentRepository repository, StudentMapper studentMapper) {
        this.repository = repository;
        this.studentMapper = studentMapper;
    }

    @Override
    public Student save(Student s) {
        return repository.save(s);
    }

    @Override
    public List<Student> findAllStudents() {
        return repository.findAll();
    }

    @Override
    public List<StudentDto> findAllStudentsDtos() {
        return repository.findAll().stream()
                .map(studentMapper::toStudentDto)
                .collect(Collectors.toList());
    }

    @Override
    public Student findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public Student update(Student s) {
        return repository.save(s);
    }

    @Override
    public void delete(String email) {
        repository.deleteByEmail(email);
    }

    @Override
    public StudentDto saveStudent(StudentDto dto) {
        var student = studentMapper.toStudent(dto);
        var savedStudent = repository.save(student);
        // repository.save(student); // called 2 times , to check Mockito.verify(studentRepository, Mockito.times(1)).save(studentToSave);
        return studentMapper.toStudentDto(savedStudent);
    }

    @Override
    public StudentDto findStudentById(Integer id) {
        return repository.findById(id)
                .map(studentMapper::toStudentDto)
                .orElse(null);
    }

    @Override
    public List<StudentDto> findAllByName(String name) {
        return repository.findAllByFirstnameContaining(name)
                .stream()
                .map(studentMapper::toStudentDto)
                .collect(Collectors.toList());
    }
}