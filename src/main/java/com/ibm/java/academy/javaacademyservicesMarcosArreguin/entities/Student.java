package com.ibm.java.academy.javaacademyservicesMarcosArreguin.entities;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "students")
public class Student implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @NotNull(message = "can not be null")
    @NotEmpty(message = "can not be empty")
    @Size(max = 30)
    @Column(name = "FIRST_NAME")
    private String firstName;

    @NotNull(message = "can not be null")
    @NotEmpty(message = "can not be empty")
    @Size(max = 30)
    @Column(name = "LAST_NAME")
    private String lastName;

    @NotNull(message = "can not be null")
    @Column(name = "BIRTH_DATE")
    private Date birthDate;

    @NotNull(message = "can not be null")
    @NotEmpty(message = "can not be empty")
    @Column(name = "GENDER")
    private String gender;

    private static final long serialVersionUID = -6613180515758015343L;
}
