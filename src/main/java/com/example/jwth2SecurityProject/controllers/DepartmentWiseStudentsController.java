package com.example.jwth2SecurityProject.controllers;

import com.example.jwth2SecurityProject.models.DepartmentModel;
import com.example.jwth2SecurityProject.services.DepartmentWiseStudentsService;
import com.example.jwth2SecurityProject.utils.LoggerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

import java.util.Optional;

@RestController
@RequestMapping("/api/v1/departmentwisestudents")
public class DepartmentWiseStudentsController {
    @Autowired
    private DepartmentWiseStudentsService departmentWiseStudentsService;

    @GetMapping
    public ResponseEntity<List<DepartmentModel>> getAllDepartmentwiseStudents() {
        try {
            List<DepartmentModel> departmentList = departmentWiseStudentsService.getAllDepartmentwiseStudents();
            if(departmentList!=null) {
                LoggerUtil.getLogger().info("Fetched all data successfully.");
                return new ResponseEntity<>(departmentList, HttpStatus.OK);
            }
            LoggerUtil.getLogger().warn("No data found.");
            return new ResponseEntity<>(null,HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Error fetching data: ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("ByIdOrName")
    public ResponseEntity<DepartmentModel> getAllStudentsByDepartmentIdOrName(@RequestParam(required = false) Integer id, @RequestParam(required = false) String name) {
        try {
            Optional<DepartmentModel> departmentModel = departmentWiseStudentsService.getAllStudentsByDepartmentId(id, name);
            if (departmentModel.isPresent()) {
                LoggerUtil.getLogger().info("Fetched all data successfully.");
                return new ResponseEntity<>(departmentModel.get(), HttpStatus.OK);
            }
            LoggerUtil.getLogger().warn("No data found.");
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            LoggerUtil.getLogger().error("Error fetching data: ", e);
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
