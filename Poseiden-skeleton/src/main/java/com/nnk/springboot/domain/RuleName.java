package com.nnk.springboot.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;

@Data
@Entity
@NoArgsConstructor
@Table(name = "rulename")
public class RuleName {

    public RuleName(String name, String description, String json, String template, String sqlStr, String sqlPart) {
        this.name = name;
        this.description = description;
        this.json = json;
        this.template = template;
        this.sqlStr = sqlStr;
        this.sqlPart = sqlPart;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer id;
    private String name = "name";
    private String description = "description";
    private String json = "json";
    private String template = "template";
    private String sqlStr = "sqlStr";
    private String sqlPart = "sqlPart";

}
