package com.example.jwth2SecurityProject.repositories;

import com.example.jwth2SecurityProject.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> { }
