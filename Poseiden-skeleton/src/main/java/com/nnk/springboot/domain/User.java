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
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    @NotBlank(message = "Username is mandatory")

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
