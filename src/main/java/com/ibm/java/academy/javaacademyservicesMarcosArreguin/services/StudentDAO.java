package com.ibm.java.academy.javaacademyservicesMarcosArreguin.services;

import com.ibm.java.academy.javaacademyservicesMarcosArreguin.entities.Student;

import java.util.Optional;

public interface StudentDAO {
    public Student saveStudent (Student student);
    public Optional<Student> findStudentById(Integer studentId);
}
