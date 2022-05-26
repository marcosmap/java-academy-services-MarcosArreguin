package com.ibm.java.academy.javaacademyservicesMarcosArreguin.repositories;

import com.ibm.java.academy.javaacademyservicesMarcosArreguin.entities.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {
    public Optional<Student> findStudentById(Integer studentId);
}
