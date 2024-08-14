package com.mlk.demo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class StudentMapperTest {

    @BeforeAll
    static void beforeAll() {
        System.err.println("Inside the Before ALL method #####");
    }

    @AfterAll
    static void afterAll() {
        System.err.println("Inside the After ALL method #####");
    }

    @BeforeEach
    void setUp() {
        System.err.println("Inside the Before each method >>>>>");
        mapper = new StudentMapper();
    }

    @AfterEach
    void tearDown() {
        System.err.println("Inside the After each method <<<<<");
    }

    @Test
    public void testMethod1() {
        System.out.println("My first test method");
    }

    @Test
    public void testMethod2() {
        System.out.println("My second test method");
    }

    /* ************************* */

    private StudentMapper mapper;


    @Test
    public void shouldMapStudentDtoToStudentClass() {
        StudentDto dto = new StudentDto(
                "firstname",
                "lastname",
                "email@email.com"
        );

        Student student = mapper.toStudent(dto);

        Assertions.assertEquals(dto.getFirstname(), student.getFirstname());
        Assertions.assertEquals(dto.getLastname(), student.getLastname());
        Assertions.assertNotNull(student.getEmail());
        Assertions.assertEquals(dto.getEmail(), student.getEmail());

    }

    @Test
    public void shouldMapStudentToStudentDto() {
        Student student = new Student();
        student.setFirstname("firstname");
        student.setLastname("lastname");
        student.setEmail("email@email.com");

        StudentDto dto = mapper.toStudentDto(student);

        Assertions.assertEquals(student.getFirstname(), dto.getFirstname());
        Assertions.assertEquals(student.getLastname(), dto.getLastname());
        Assertions.assertNotNull(dto.getEmail());
        Assertions.assertEquals(student.getEmail(), dto.getEmail());

    }

    @Test
    public void should_throw_null_pointer_exception_when_studentDto_is_null() {
        var message = Assertions.assertThrows(NullPointerException.class, () -> mapper.toStudent(null));
        Assertions.assertEquals("The student Dto should not be NULL", message.getMessage());
    }
}