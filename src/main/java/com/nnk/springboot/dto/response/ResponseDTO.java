package com.nnk.springboot.dto.response;

import lombok.Data;

@Data
public class ResponseDTO {
    public ResponseDTO(boolean status, String message) {
        this.status = status;
        this.message = message;
    }

    private boolean status;
    private String message;

}
