package com.nnk.springboot.dto;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class CurvePointDTO {

    private Integer id;
    private Integer curveId;
    private Timestamp asOfDate;
    private Double term;
    private Double value;
    private Timestamp creationDate;

}
