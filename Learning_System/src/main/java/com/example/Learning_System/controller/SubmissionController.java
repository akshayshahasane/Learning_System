package com.example.Learning_System.controller;

import com.example.Learning_System.entity.Assignment;
import com.example.Learning_System.entity.Submission;
import com.example.Learning_System.entity.User;
import com.example.Learning_System.repository.AssignmentRepository;
import com.example.Learning_System.repository.SubmissionRepository;
import com.example.Learning_System.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/submissions")
public class SubmissionController {

    @Autowired
    private SubmissionRepository submissionRepository;

    @Autowired
    private AssignmentRepository assignmentRepository;

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/assignment/{assignmentId}/student/{studentId}")
    public Submission submitAssignment(@PathVariable Long assignmentId,
                                       @PathVariable Long studentId,
                                       @RequestBody Submission submission) {

        Assignment assignment = assignmentRepository.findById(assignmentId)
                .orElseThrow(() -> new RuntimeException("Assignment not found"));

        User student = userRepository.findById(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found"));

        submission.setAssignment(assignment);
        submission.setStudent(student);
        submission.setSubmittedAt(LocalDate.now());

        return submissionRepository.save(submission);
    }

    @GetMapping("/assignment/{assignmentId}")
    public List<Submission> getSubmissions(@PathVariable Long assignmentId) {
        return submissionRepository.findAll()
                .stream()
                .filter(s -> s.getAssignment().getId().equals(assignmentId))
                .toList();
    }
}

