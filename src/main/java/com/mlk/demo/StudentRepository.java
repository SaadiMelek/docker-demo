package com.mlk.demo;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer> {

    Student findByEmail(String email);

    void deleteByEmail(String email);

    List<Student> findAllByFirstnameContaining(String name);
}