package com.example.jwth2SecurityProject.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="students")
public class StudentModel{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "Name is required")
    @Pattern(regexp = "^[a-zA-Z\\s-.']*$", message = "Name must contain only letters, spaces, hyphens, dots, or apostrophes")
    private String name;

    @NotNull(message = "Standard is required")
    private int standard;

    @Email(message = "Email should be valid")
    @NotBlank(message = "Email is required")
    private String email;

    @ManyToOne
    @JoinColumn(name = "department_id", nullable = false)
    @NotNull(message = "Department is required")
    @JsonIgnore
    private DepartmentModel department;
}
