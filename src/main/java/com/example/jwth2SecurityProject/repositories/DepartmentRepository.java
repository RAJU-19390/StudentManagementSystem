package com.example.jwth2SecurityProject.repositories;

import com.example.jwth2SecurityProject.models.DepartmentModel;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<DepartmentModel,Integer> {
}
