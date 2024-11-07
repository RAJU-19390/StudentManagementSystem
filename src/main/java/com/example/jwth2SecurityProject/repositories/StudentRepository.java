package com.example.jwth2SecurityProject.repositories;

import com.example.jwth2SecurityProject.models.StudentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<StudentModel,Integer> {}

