package com.nnk.springboot.domain;

import lombok.Data;
import javax.persistence.*;
import javax.validation.constraints.Pattern;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "curvepoint")
public class CurvePoint {

    public CurvePoint(Integer curveId, Timestamp asOfDate, Double term) {
        this.curveId = curveId;
        this.asOfDate = asOfDate;
        this.term = term;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;

    @Pattern(regexp = "^([1-9]|[1-9][0-9]{0,9})$")
    private Integer curveId = 1;
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Timestamp asOfDate;
    @Pattern(regexp = "^[0-9]{1,}[.][0-9]{2}$")
    private Double term = 0.00;
    @Pattern(regexp = "^[0-9]{1,}[.][0-9]{2}$")
    private Double value = 0.00;
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Timestamp creationDate;

}
