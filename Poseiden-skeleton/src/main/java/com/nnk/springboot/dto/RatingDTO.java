package com.nnk.springboot.dto;

import lombok.Data;


@Data
public class RatingDTO {

    private Integer id;
    private String moodysRating;
    private String sandPRating;
    private String fitchRating;
    private Integer orderNumber;

}
