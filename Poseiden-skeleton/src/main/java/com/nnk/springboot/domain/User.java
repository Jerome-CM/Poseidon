package com.nnk.springboot.domain;

import lombok.Data;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

@Data
@Entity
// @NoArgsConstructor
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Username is mandatory")
    @Column(unique = true)
    private String username;
    @NotBlank(message = "Password is mandatory")
    @Pattern(regexp = "^(?=.[A-Z])(?=.\\d)(?=.[@$!%#?&])[A-Za-z\\d@$!%#?&]{8,}$")
    private String password;
    @NotBlank(message = "FullName is mandatory")
    @Pattern(regexp = "^(?:\\w\\-\\w \\w\\-\\w|(?:(?:\\w\\-\\w|\\w) \\w|\\w))$")
    private String fullname;
    @NotBlank(message = "Role is mandatory")
    private String role;

}
