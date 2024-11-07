package com.example.jwth2SecurityProject.controllers;

import com.example.jwth2SecurityProject.dtos.Student;
import com.example.jwth2SecurityProject.services.StudentService;
import com.example.jwth2SecurityProject.utils.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping()
    public ResponseEntity<List<Student>> getAllStudents() {
        try {
            List<Student> studentsList = studentService.getAllStudents();
            if (studentsList == null || studentsList.isEmpty()) {
                LoggerUtil.getLogger().info("No students found.");
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
            }
            LoggerUtil.getLogger().info("Retrieved all students successfully.");
            return new ResponseEntity<>(studentsList, HttpStatus.OK);
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Error retrieving all students", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/id")
    public ResponseEntity<Optional<Student>> getStudentById(@RequestParam int id) {
        try {
            Optional<Student> student = studentService.getStudentById(id);
            if (student.isPresent()) {
                LoggerUtil.getLogger().info("Retrieved student with ID " + id + " successfully.");
                return new ResponseEntity<>(student, HttpStatus.OK);
            }
            LoggerUtil.getLogger().warn("Student with ID " + id + " not found.");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Error retrieving student with ID " + id, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping()
    public ResponseEntity<Student> addStudentData(@RequestBody Student student) {
        try {
            if (studentService.addStudent(student)) {
                LoggerUtil.getLogger().info("Added new student: " + student.getName());
                return new ResponseEntity<>(student, HttpStatus.CREATED);
            }
            LoggerUtil.getLogger().warn("Failed to add student: " + student.getName());
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Error adding student: " + student.getName(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping()
    public ResponseEntity<String> updateStudentData(@RequestBody Student student) {
        try {
            if (studentService.updateStudent(student)) {
                LoggerUtil.getLogger().info("Updated student with ID " + student.getId() + " successfully.");
                return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
            }
            LoggerUtil.getLogger().warn("Student with ID " + student.getId() + " not found for update.");
            return new ResponseEntity<>("Given ID of Student is not found", HttpStatus.NOT_MODIFIED);
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Error updating student with ID " + student.getId(), e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/id")
    public ResponseEntity<String> deleteStudentData(@RequestParam int id) {
        try {
            if (studentService.deleteStudent(id)) {
                LoggerUtil.getLogger().info("Deleted student with ID " + id + " successfully.");
                return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
            }
            LoggerUtil.getLogger().warn("Student with ID " + id + " not found for deletion.");
            return new ResponseEntity<>("Given Student ID is not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Error deleting student with ID " + id, e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
