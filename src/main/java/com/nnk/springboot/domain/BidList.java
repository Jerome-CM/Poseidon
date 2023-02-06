package com.nnk.springboot.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Timestamp;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name = "bidlist")
public class BidList {

    public BidList(String account, String type, double bidQuantity) {
        this.account = account;
        this.type = type;
        this.bidQuantity = bidQuantity;
    }

    @Id
    @GeneratedValue(strategy= GenerationType.AUTO)
    private Integer bidListId;
    @NotBlank(message = "Account is mandatory")
    private String account;
    @NotBlank(message = "Type is mandatory")
    private String type;
    private Double bidQuantity = 0.00;
    private Double askQuantity = 0.00;
    private Double bid = 0.00;
    private Double ask = 0.00;
    private String benchmark = "benchmark";
    @Column(columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private Timestamp bidListDate = new Timestamp(new Date().getTime());
    private String commentary = "commentary";
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
