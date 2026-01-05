package com.example.Learning_System.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Submission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String fileUrl; // or just filename

    private LocalDate submittedAt;

    @ManyToOne
    private Assignment assignment;

    @ManyToOne
    private User student;

    private Integer grade;

    private String submissionFile;

    @Column(length = 2000)
    private String feedback;


}
