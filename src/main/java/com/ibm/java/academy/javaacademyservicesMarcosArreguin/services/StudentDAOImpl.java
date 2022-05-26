package com.ibm.java.academy.javaacademyservicesMarcosArreguin.services;

import com.ibm.java.academy.javaacademyservicesMarcosArreguin.entities.Student;
import com.ibm.java.academy.javaacademyservicesMarcosArreguin.exceptions.BadRequestException;
import com.ibm.java.academy.javaacademyservicesMarcosArreguin.repositories.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class StudentDAOImpl implements StudentDAO{

    @Autowired
    private StudentRepository studentRepository;

    @Override
    @Transactional
    public Student saveStudent(Student student) {
        if (!student.getGender().equals("M") && !student.getGender().equals("F"))
            throw new BadRequestException("Valid values are 'F' and 'M' in the field 'gender'");
        return studentRepository.save(student);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Student> findStudentById(Integer studentId) {
        return studentRepository.findById(studentId);
    }

}
