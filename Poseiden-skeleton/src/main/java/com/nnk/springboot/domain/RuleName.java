package com.nnk.springboot.domain;

import lombok.Data;
import javax.persistence.*;

@Data
@Entity
@Table(name = "rulename")
public class RuleName {

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name;
    private String description;
    private String json;
    private String template;
    private String sqlStr;
    private String sqlPart;

}
