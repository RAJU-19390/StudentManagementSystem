package com.example.jwth2SecurityProject.services;

import com.example.jwth2SecurityProject.dtos.Department;
import com.example.jwth2SecurityProject.models.DepartmentModel;
import com.example.jwth2SecurityProject.repositories.DepartmentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.LoggerFactory;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {
    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper modelMapper;
    public List<Department> getAllDepartments() {
        try {
            return departmentRepository.findAll().stream()
                    .map(departmentModel -> modelMapper.map(departmentModel,Department.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Department> getDepartmentById(int id){
        try {
            return departmentRepository.findById(id)
                    .map(departmentModel -> modelMapper.map(departmentModel,Department.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Department> getDepartmentByName(String name){
        try {
            return getAllDepartments().stream()
                    .filter(e -> e.getName().equals(name)).findFirst()
                    .map(departmentmodel -> modelMapper.map(departmentmodel,Department.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addDepartment(Department department) {
        try {
            if (department != null) {
                DepartmentModel departmentModel=modelMapper.map(department,DepartmentModel.class);
                departmentRepository.save(departmentModel);
                return true;
                }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateDepartment(Department data){
        try{
            DepartmentModel departmentModel=modelMapper.map(data,DepartmentModel.class);
            Optional<DepartmentModel> existingDepartment=departmentRepository.findById(departmentModel.getId());
            if(existingDepartment.isPresent()) {
                DepartmentModel updatedata=existingDepartment.get();
                updatedata.setName(data.getName());
                departmentRepository.save(updatedata);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean deleteDepartment(int id) {
        try {
            if (departmentRepository.existsById(id)) {
                departmentRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
