package com.example.jwth2SecurityProject.controllers;

import com.example.jwth2SecurityProject.dtos.Department;
import com.example.jwth2SecurityProject.services.DepartmentService;
import com.example.jwth2SecurityProject.utils.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/departments")
public class DepartmentController {

    @Autowired
    private DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<Department>> getAllDepartments() {
        try {
            List<Department> departmentList = departmentService.getAllDepartments();
            if (departmentList != null && !departmentList.isEmpty()) {
                LoggerUtil.getLogger().info("Fetched all departments successfully.");
                return new ResponseEntity<>(departmentList, HttpStatus.OK);
            }
                LoggerUtil.getLogger().warn("No departments found.");
                return new ResponseEntity<>(null, HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Error fetching departments: ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/ByIdOrName")
    public ResponseEntity<Optional<Department>> getDepartmentByIdOrName(
            @RequestParam(required = false) Integer id,
            @RequestParam(required = false) String name) {
        try {
            Optional<Department> department;
            if (id != null) {
                department = departmentService.getDepartmentById(id);
            } else if (name != null) {
                department = departmentService.getDepartmentByName(name);
            } else {
                LoggerUtil.getLogger().warn("Invalid request: Neither ID nor Name provided.");
                return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
            }

            if (department.isPresent()) {
                LoggerUtil.getLogger().info("Fetched department by ID/Name successfully.");
                return new ResponseEntity<>(department, HttpStatus.OK);
            }
            LoggerUtil.getLogger().warn("Department not found.");
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        } catch (Exception e) {
            LoggerUtil.getLogger().error("Error fetching department by ID or name: ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<Department> addDepartmentData(@RequestBody Department data) {
        try {
            if (departmentService.addDepartment(data)) {
                LoggerUtil.getLogger().info("Department added successfully.");
                return new ResponseEntity<>(data, HttpStatus.CREATED);
            }
            LoggerUtil.getLogger().warn("Department could not be added.");
            return new ResponseEntity<>(null, HttpStatus.NOT_ACCEPTABLE);

        } catch (Exception e) {
            LoggerUtil.getLogger().error("Error adding department: ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping
    public ResponseEntity<String> updateDepartmentData(@RequestBody Department data) {
        try {
            if (departmentService.updateDepartment(data)) {
                LoggerUtil.getLogger().info("Department updated successfully.");
                return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
            }
            LoggerUtil.getLogger().warn("Department ID not found for update.");
            return new ResponseEntity<>("Department ID not found", HttpStatus.NOT_MODIFIED);
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Error updating department: ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/id")
    public ResponseEntity<String> deleteDepartmentData(@RequestParam int id) {
        try {
            if (departmentService.deleteDepartment(id)) {
                LoggerUtil.getLogger().info("Department deleted successfully.");
                return new ResponseEntity<>("Deleted Successfully", HttpStatus.OK);
            }
            LoggerUtil.getLogger().warn("Department ID not found for deletion.");
            return new ResponseEntity<>("Department ID not found", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Error deleting department: ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

