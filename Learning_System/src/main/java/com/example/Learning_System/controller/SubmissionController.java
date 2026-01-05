package com.example.Learning_System.controller;

import com.example.Learning_System.dto.GradeRequestDto;
import com.example.Learning_System.entity.Assignment;
import com.example.Learning_System.entity.Submission;
import com.example.Learning_System.entity.User;
import com.example.Learning_System.repository.AssignmentRepository;
import com.example.Learning_System.repository.SubmissionRepository;
import com.example.Learning_System.repository.UserRepository;
import com.example.Learning_System.serviceImpl.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


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
    @Autowired
    private FileStorageService fileStorageService;

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

    @PostMapping("/{submissionId}/upload")
    public Submission uploadSubmissionFile(@PathVariable Long submissionId,
                                           @RequestParam("file") MultipartFile file) throws Exception {

        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        String uploadedFile = fileStorageService.saveFile(file);

        submission.setSubmissionFile(uploadedFile);

        return submissionRepository.save(submission);
    }

    @PutMapping("/{submissionId}/grade")
    @PreAuthorize("hasRole('INSTRUCTOR') or hasRole('ADMIN')")
    public Submission gradeSubmission(
            @PathVariable Long submissionId,
            @RequestBody GradeRequestDto gradeRequest
    ) {
        Submission submission = submissionRepository.findById(submissionId)
                .orElseThrow(() -> new RuntimeException("Submission not found"));

        submission.setGrade(gradeRequest.getGrade());
        submission.setFeedback(gradeRequest.getFeedback());

        return submissionRepository.save(submission);
    }

    @GetMapping("/student/{studentId}")
    @PreAuthorize("hasRole('STUDENT')")
    public List<Submission> getStudentSubmissions(@PathVariable Long studentId) {
        return submissionRepository.findAll()
                .stream()
                .filter(s -> s.getStudent().getId().equals(studentId))
                .toList();
    }



}

