package com.example.Learning_System.controller;

import com.example.Learning_System.entity.Assignment;
import com.example.Learning_System.entity.Course;
import com.example.Learning_System.repository.AssignmentRepository;
import com.example.Learning_System.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
public class AssignmentController {

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private CourseRepository courseRepository;

    @PostMapping("/course/{courseId}")
    public Assignment addAssignment(@PathVariable Long courseId, @RequestBody Assignment assignment) {
        Course course = courseRepository.findById(courseId)
                .orElseThrow(() -> new RuntimeException("Course not found"));
        assignment.setCourse(course);
        return assignmentRepository.save(assignment);
    }

    @GetMapping("/course/{courseId}")
    public List<Assignment> getAssignments(@PathVariable Long courseId) {
        return assignmentRepository.findAll()
                .stream()
                .filter(a -> a.getCourse().getId().equals(courseId))
                .toList();
    }
}



