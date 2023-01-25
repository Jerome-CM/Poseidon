package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.dto.response.ResponseDTO;

import java.util.List;

public interface RatingService {

    public ResponseDTO saveRating(Rating rating);

    public ResponseDTO updateRating(Rating rating, int id);

    public ResponseDTO deleteRatingById(int id);
    public List<RatingDTO> getAllRating();

    public RatingDTO getRatingById(int id);

}
