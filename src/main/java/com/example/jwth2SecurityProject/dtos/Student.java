package com.example.jwth2SecurityProject.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student{
    private int id;
    private String name;
    private int standard;
    private String email;
    private int departmentId;
}
