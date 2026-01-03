package com.example.Learning_System.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Assignment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    @Column(length = 2000)
    private String description;

    private LocalDate dueDate;

    @ManyToOne
    private Course course;
}

