package com.example.jwth2SecurityProject.models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="departments")
public class DepartmentModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[a-zA-Z\\s-.']*$", message = "Name must contain only letters, spaces, hyphens, dots, or apostrophes")
    private String name;

    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference
    private List<StudentModel> student;

}
