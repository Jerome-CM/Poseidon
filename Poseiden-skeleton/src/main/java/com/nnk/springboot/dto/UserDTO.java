package com.nnk.springboot.dto;

import lombok.Data;

@Data
public class UserDTO {

    private Integer id;
    private String fullname;
    private String username;
    private String role;

}
