package com.nnk.springboot.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Integer id;
    @NotBlank(message = "Username is mandatory")
    @Column(unique = true)
    private String username;
    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.[A-Z])(?=.\\d)(?=.[@$!%#?&])[A-Za-z\\d@$!%#?&]{8,}$", message = "At least one capital letter, 8 characters minimum, at least one number and one symbol (@$!%#?&)")
    private String password;
    @NotBlank(message = "FullName is mandatory")
    private String fullname;
    @NotBlank(message = "Role is mandatory")
    private String role;
}
