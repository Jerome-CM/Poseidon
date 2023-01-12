package com.nnk.springboot.services;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.RatingDTO;

import java.util.List;

public interface RatingService {

    public void saveRating(Rating rating);

    public RatingDTO updateRating(Rating rating, int id);

    public void deleteRatingById(int id);
    public List<RatingDTO> getAllRating();

    public RatingDTO getRatingById(int id);

}
