package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.dto.response.ResponseDTO;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class RatingServiceImpl implements RatingService {
    private final RatingRepository ratingRepository;

    private final ModelMapper modelMapper;

    public RatingServiceImpl(RatingRepository ratingRepository, ModelMapper modelMapper) {
        this.ratingRepository = ratingRepository;
        this.modelMapper = modelMapper;
    }

    /**
     * Save a new Rating
     * @param rating
     * @return
     */
    @Override
    public ResponseDTO saveRating(Rating rating){
        log.info("--- Method saveRating ---");
        try{
            ratingRepository.save(rating);
            log.info("Rating saved : {}", rating);
            return new ResponseDTO(true, "Rating saved with success");
        } catch (Exception e){
            log.error("Impossible to save a rating : {}", e.getMessage());
            return new ResponseDTO(false, "Impossible to save a rating");
        }
    }

    /**
     * Update a rating
     * @param rating
     * @param id
     * @return
     */
    @Override
    public ResponseDTO updateRating(Rating rating, int id){
        log.info("--- Method updateRating ---");
        System.out.println("------ Rating reçu dans le service :" + rating);
        Optional<Rating> ratingHandle = ratingRepository.findById(id);
        if(ratingHandle.isPresent()) {
            Rating ratingHandleConfirm = ratingHandle.get();
            try {
                rating.setId(ratingHandleConfirm.getId());
                ratingHandleConfirm = ratingRepository.save(rating);
                log.info("Rating updated : {}", ratingHandleConfirm);
                return new ResponseDTO(true, "Rating updated with success");

            } catch (Exception e) {
                log.error("Impossible to updated the rating : {}", e.getMessage());
                return new ResponseDTO(false, "Impossible to update a rating");
            }
        } else {
            log.error("Impossible to find the rating");
            return new ResponseDTO(false, "Impossible to find a rating");
        }
    }

    /**
     * Delete a Rating
     * @param id
     * @return
     */
    @Override
    public ResponseDTO deleteRatingById(int id) {
        log.info("--- Method deleteRatingById ---");
        Optional<Rating> rating = ratingRepository.findById(id);
        if(rating.isPresent()) {
            Rating ratingPresent = rating.get();
            try {
                ratingRepository.delete(ratingPresent);
                log.info("Rating deleted");
                return new ResponseDTO(true, "Rating deleted with success");
            } catch (Exception e) {
                log.error("Impossible to delete the rating with this id({}) : {}", id, e.getMessage());
                return new ResponseDTO(false, "Impossible to delete a rating");
            }
        } else {
            log.error("Impossible to find the rating with this id({})", id);
            return new ResponseDTO(false, "Impossible to find a rating");
        }
    }


    /**
     * Get RatingDTO for all rating
     * @return
     */
    @Override
    public List<RatingDTO> getAllRating() {

        List<Rating> ratingList = ratingRepository.findAll();
        List<RatingDTO> ratingDTOList = new ArrayList<>();

        for(Rating rating : ratingList){
            RatingDTO ratingDTO = modelMapper.map(rating, RatingDTO.class);
            ratingDTOList.add(ratingDTO);
        }

        return ratingDTOList;

    }

    /**
     * Get RatingDTO by Id
     * @param id
     * @return
     */
    @Override
    public RatingDTO getRatingById(int id) {

        Optional<Rating> ratingById = ratingRepository.findById(id);
        if (ratingById.isPresent()) {
            return modelMapper.map(ratingById.get(), RatingDTO.class);
        } else {
            log.error("Rating not Found id : {})", id);
            throw new IllegalArgumentException("Invalid Id");
        }
    }

}
