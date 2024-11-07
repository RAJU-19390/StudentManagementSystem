package com.example.jwth2SecurityProject.services;

import com.example.jwth2SecurityProject.models.DepartmentModel;
import com.example.jwth2SecurityProject.repositories.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class DepartmentWiseStudentsService {
    @Autowired
    public DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService departmentService;

    public List<DepartmentModel> getAllDepartmentwiseStudents(){
        try{
         return departmentRepository.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<DepartmentModel> getAllStudentsByDepartmentId(Integer id,String name){
       try {
           return (id != null) ? departmentRepository.findById(id)
                   : departmentRepository.findAll().stream().filter(e -> e.getName().equals(name)).findFirst();
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
    }
}
