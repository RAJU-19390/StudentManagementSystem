package com.example.jwth2SecurityProject.services;


import com.example.jwth2SecurityProject.dtos.Student;
import com.example.jwth2SecurityProject.models.DepartmentModel;
import com.example.jwth2SecurityProject.models.StudentModel;
import com.example.jwth2SecurityProject.repositories.DepartmentRepository;
import com.example.jwth2SecurityProject.repositories.StudentRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.Deque;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private DepartmentRepository departmentRepository;

    @Autowired
    private ModelMapper mapper;


    public List<Student> getAllStudents() {
        try {
            return studentRepository.findAll().stream()
                    .map(studentModel -> mapper.map(studentModel, Student.class))
                    .collect(Collectors.toList());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Optional<Student> getStudentById(int id){
        try {
            return studentRepository.findById(id)
                    .map(studentModel -> mapper.map(studentModel,Student.class));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean addStudent(Student student) {
        try {
            if (student != null) {
                StudentModel studentModel=mapper.map(student,StudentModel.class);
                studentRepository.save(studentModel);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public boolean updateStudent(Student data) {
        try {
            StudentModel studentModel = mapper.map(data, StudentModel.class);
            Optional<StudentModel> existingData = studentRepository.findById(studentModel.getId());
            if (existingData.isPresent()) {
                StudentModel updatedData = existingData.get();
                updatedData.setName(data.getName());
                updatedData.setStandard(data.getStandard());
                updatedData.setEmail(data.getEmail());
                DepartmentModel department = departmentRepository.findById(data.getDepartmentId())
                            .orElseThrow(() -> new RuntimeException("Department not found"));
                updatedData.setDepartment(department);
                studentRepository.save(updatedData);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }


    public boolean deleteStudent(int id) {
        try {
            if (studentRepository.existsById(id)) {
                studentRepository.deleteById(id);
                return true;
            }
            return false;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
