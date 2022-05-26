package com.ibm.java.academy.javaacademyservicesMarcosArreguin.controllers;

import com.ibm.java.academy.javaacademyservicesMarcosArreguin.entities.Student;
import com.ibm.java.academy.javaacademyservicesMarcosArreguin.exceptions.NotFoundException;
import com.ibm.java.academy.javaacademyservicesMarcosArreguin.services.StudentDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {

    @Autowired
    private StudentDAO studentDAO;

    /**
     * This method has the function to find a Student object in the database
     * @param studentId is the parameter to find a Student
     * @return this method returns the student's full name if it exists
     * @author Marcos Manuel Arreguin Perez 25/05/2022
     */
    @GetMapping("/{studentId}")
    public ResponseEntity<?> findStudent (@PathVariable Integer studentId) {
        Optional<Student> student = studentDAO.findStudentById(studentId);
        if (!student.isPresent())
            throw new NotFoundException(String.format("Student with ID: %d not found", studentId));
        Map<String, Object> respuesta = new HashMap<String, Object>();
        respuesta.put("fullName", student.get().getFirstName() + " " + student.get().getLastName());
        return new ResponseEntity<>(respuesta, HttpStatus.OK);
    }

    /**
     * This method saves Student's objects in the database
     * @param student the parameter is a Student object, the fields that you must give are firstName, lastName, birthDate and gender
     * @param result this parameter catches the errors in the request body like some null parameter or empty
     * @return this method returns the ID of the student registered
     */
    @PostMapping
    public ResponseEntity<?> createStudent (@Valid @RequestBody Student student, BindingResult result) {
        Map<String, Object> validaciones = new HashMap<String, Object>();
        if (result.hasErrors()) {
            List<String> listaErrores = result.getFieldErrors()
                    .stream()
                    .map(errores -> "Field '" + errores.getField() + "' " + errores.getDefaultMessage())
                    .collect(Collectors.toList());
            validaciones.put("Error list", listaErrores);
            return new ResponseEntity<Map<String, Object>>(validaciones, HttpStatus.BAD_REQUEST);
        }

        Student studentCreated = studentDAO.saveStudent(student);
        Map<String, Object> respuesta = new HashMap<String, Object>();
        respuesta.put("id", studentCreated.getId());
        return new ResponseEntity<>(respuesta, HttpStatus.CREATED);
    }

}
