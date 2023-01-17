package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RatingServiceImpl implements RatingService {

    private static final Logger logger = LogManager.getLogger(RatingServiceImpl.class);

    private final RatingRepository ratingRepository;

    private final ModelMapper modelMapper;

    public RatingServiceImpl(RatingRepository ratingRepository, ModelMapper modelMapper) {
        this.ratingRepository = ratingRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public void saveRating(Rating rating){
        logger.info("--- Method saveRating ---");
        try{
            ratingRepository.save(rating);
            logger.info("Rating saved : {}", rating);
        } catch (Exception e){
            logger.error("Impossible to save a rating : {}", e.getMessage());
        }
    }

    @Override
    public RatingDTO updateRating(Rating rating, int id){
        logger.info("--- Method updateRating ---");
        try {
            Rating ratingHandle = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
            rating.setId(ratingHandle.getId());
            if (rating.getId() != null) {
                ratingHandle = ratingRepository.save(rating);
                logger.info("Rating updated : {}", ratingHandle);
                return modelMapper.map(ratingHandle, RatingDTO.class);
            } else {
                logger.error("Rating id is null with this id : {}", rating);
                return null;
            }
        } catch (Exception e) {
            logger.error("Impossible to updated the rating : {}", e.getMessage());
            return null;
        }
    }

    @Override
    public void deleteRatingById(int id) {
        logger.info("--- Method deleteRatingById ---");
        try{
            Rating rating = ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id));
            ratingRepository.delete(rating);
            logger.info("Rating deleted");
        } catch (Exception e){
            logger.error("Impossible to delete the rating with this id({}) : {}",id, e.getMessage());
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

        if(id != 0) {
            Optional<Rating> ratingById = Optional.ofNullable(ratingRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid rating Id:" + id)));
            if (ratingById.isPresent()) {
                return modelMapper.map(ratingById.get(), RatingDTO.class);
            } else {
                logger.error("Rating not Found id : {})", id);
            }
        } else {
            throw new IllegalArgumentException("Invalid Id:" + id);
        }
        return null;
    }

}
