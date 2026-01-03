package com.example.Learning_System.controller;

import com.example.Learning_System.entity.Course;
import com.example.Learning_System.service.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/enrollments")
public class EnrollmentController {

    @Autowired
    private EnrollmentService enrollmentService;

    @PostMapping("/{studentId}/{courseId}")
    public String enroll(
            @PathVariable Long studentId,
            @PathVariable Long courseId) {

        return enrollmentService.enrollStudent(studentId, courseId);
    }

    @GetMapping("/student/{studentId}")
    public List<Course> getStudentCourses(@PathVariable Long studentId) {
        return enrollmentService.getCoursesOfStudent(studentId);
    }

}



