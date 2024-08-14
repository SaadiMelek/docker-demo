package com.mlk.demo;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class StudentServiceImplTest {

    // which service we want to test ?
    @InjectMocks
    private StudentServiceImpl studentService;

    // declare the dependencies
    @Mock
    StudentRepository studentRepository;
    @Mock
    StudentMapper studentMapper;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);// role of this ??
    }

    @Test
    public void should_successfully_save_student() {
        // Given
        StudentDto dto = new StudentDto(
                "firstname",
                "lastname",
                "email@email.com"
        );

        Student studentToSave = new Student();
        studentToSave.setFirstname("firstname");
        studentToSave.setLastname("lastname");
        studentToSave.setEmail("email@email.com");

        Student savedStudent = new Student();
        savedStudent.setFirstname("firstname");
        savedStudent.setLastname("lastname");
        savedStudent.setEmail("email@email.com");
        savedStudent.setId(1);

        // Mock the calls
        Mockito.when(studentMapper.toStudent(dto))
                .thenReturn(studentToSave);

        Mockito.when(studentRepository.save(studentToSave))
                .thenReturn(savedStudent);

        Mockito.when(studentMapper.toStudentDto(savedStudent))
                .thenReturn(new StudentDto(
                        "firstname",
                        "lastname",
                        "email@email.com"
                ));

        // When
        StudentDto responseDto = studentService.saveStudent(dto);

        // Then
        Assertions.assertEquals(dto.getFirstname(), responseDto.getFirstname());
        Assertions.assertEquals(dto.getLastname(), responseDto.getLastname());
        Assertions.assertEquals(dto.getEmail(), responseDto.getEmail());

        Mockito.verify(studentMapper, Mockito.times(1))
                .toStudent(dto);
        Mockito.verify(studentRepository, Mockito.times(1))
                .save(studentToSave);
        Mockito.verify(studentMapper, Mockito.times(1))
                .toStudentDto(savedStudent);
    }

    @Test
    public void should_return_all_students() {
        // Given
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                "firstname",
                "lastname",
                   "email@email.com"
                )
        );

        // Mock the calls
        Mockito.when(studentRepository.findAll())
                .thenReturn(students);
        Mockito.when(studentMapper.toStudentDto(Mockito.any(Student.class)))
                .thenReturn(new StudentDto(
                        "firstname",
                        "lastname",
                        "email@email.com"
                        )
                );

        // When
        List<StudentDto> returnedStudentsDto = studentService.findAllStudentsDtos();

        // Then
        Assertions.assertEquals(students.size(), returnedStudentsDto.size());
        Mockito.verify(studentRepository, Mockito.times(1))
                .findAll();
    }

    @Test
    public void should_return_student_by_id() {
        Integer studentId = 1;
        Student student = new Student(
                "firstname",
                "lastname",
                "email@email.com"
        );

        // Mock the calls
        Mockito.when(studentRepository.findById(studentId))
                .thenReturn(Optional.of(student));
        Mockito.when(studentMapper.toStudentDto(Mockito.any(Student.class)))
                .thenReturn(new StudentDto(
                                "firstname",
                                "lastname",
                                "email@email.com"
                        )
                );

        // When
        StudentDto dto = studentService.findStudentById(studentId);

        // Then
        Assertions.assertEquals(dto.getFirstname(), student.getFirstname());
        Assertions.assertEquals(dto.getLastname(), student.getLastname());
        Assertions.assertEquals(dto.getEmail(), student.getEmail());

        Mockito.verify(studentRepository, Mockito.times(1))
                .findById(studentId);
    }

    @Test
    public void should_find_students_by_name() {

        // Given
        List<Student> students = new ArrayList<>();
        students.add(new Student(
                        "firstname",
                        "lastname",
                        "email@email.com"
                )
        );

        String studentName = "firstname";

        // Mock the calls
        Mockito.when(studentRepository.findAllByFirstnameContaining(studentName))
                .thenReturn(students);
        Mockito.when(studentMapper.toStudentDto(Mockito.any(Student.class)))
                .thenReturn(new StudentDto(
                                "firstname",
                                "lastname",
                                "email@email.com"
                        )
                );
        // When
        List<StudentDto> returnedStudentsDto = studentService.findAllByName(studentName);

        // Then
        Assertions.assertEquals(students.size(), returnedStudentsDto.size());

        Mockito.verify(studentRepository, Mockito.times(1))
                .findAllByFirstnameContaining(studentName);
    }
}