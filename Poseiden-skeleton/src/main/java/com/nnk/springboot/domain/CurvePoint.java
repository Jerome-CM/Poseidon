package com.nnk.springboot.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@Data
@Entity
@NoArgsConstructor
@Table(name = "curvepoint")
public class CurvePoint {

    public CurvePoint(Integer curveId, Double term, Double value) {
        this.curveId = curveId;
        this.term = term;
        this.value = value;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Pattern(regexp = "^([1-9]|[1-9][0-9]{0,9})$")
    @NotBlank(message = "must not be null")
    private Integer curveId;
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Timestamp asOfDate;
    @Pattern(regexp = "^[0-9]{1,}[.][0-9]{2}$")
    private Double term = 0.00;
    @Pattern(regexp = "^[0-9]{1,}[.][0-9]{2}$")
    private Double value = 0.00;
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private Timestamp creationDate;

}
