package com.example.Learning_System.service;

import com.example.Learning_System.entity.Course;

import java.util.List;

public interface EnrollmentService {
    String enrollStudent(Long studentId, Long courseId);
    List<Course> getCoursesOfStudent(Long studentId);

}

