package com.example.prueba.entity;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Data
@Entity
@Table(name = "users") // Porque a veces no hace falta poner esta etiqueta?
public class User implements Serializable {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email", nullable = false,unique = true)
    private String email;

    @Column(name = "enabled")
    private Boolean enabled;

}
