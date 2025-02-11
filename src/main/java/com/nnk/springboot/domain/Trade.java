package com.nnk.springboot.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import java.sql.Timestamp;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@Table(name = "trade")
public class Trade {

    public Trade(String account, String type) {
        this.account = account;
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer tradeId;
    @NotBlank(message = "Account is mandatory")
    private String account;
    @NotBlank(message = "Type is mandatory")
    private String type;
    private Double buyQuantity = 0.00;
    private Double sellQuantity = 0.00;
    private Double buyPrice = 0.00;
    private Double sellPrice = 0.00;
    private String benchmark = "benchmark";
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Timestamp tradeDate = new Timestamp(new Date().getTime());
    private String security = "security";
    private String status = "status";
    private String trader = "trader";
    private String book = "book";
    private String creationName = "creationName";
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP", updatable = false)
    private Timestamp creationDate = new Timestamp(new Date().getTime());
    private String revisionName = "revisionName";
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Timestamp revisionDate = new Timestamp(new Date().getTime());
    private String dealName = "dealName";
    private String dealType = "dealType";
    private String sourceListId = "sourceListId";
    private String side = "side";
}
