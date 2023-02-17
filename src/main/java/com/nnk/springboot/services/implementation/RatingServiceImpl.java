package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.dto.response.ResponseDTO;
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
    public ResponseDTO saveRating(Rating rating){
        logger.info("--- Method saveRating ---");
        try{
            ratingRepository.save(rating);
            logger.info("Rating saved : {}", rating);
            return new ResponseDTO(true, "Rating saved with success");
        } catch (Exception e){
            logger.error("Impossible to save a rating : {}", e.getMessage());
            return new ResponseDTO(false, "Impossible to save a rating");
        }
    }

    @Override
    public ResponseDTO updateRating(Rating rating, int id){
        logger.info("--- Method updateRating ---");
        System.out.println("------ Rating reçu dans le service :" + rating);
        Optional<Rating> ratingHandle = ratingRepository.findById(id);
        if(ratingHandle.isPresent()) {
            Rating ratingHandleConfirm = ratingHandle.get();
            try {
                rating.setId(ratingHandleConfirm.getId());
                if (rating.getId() != null) {
                    ratingHandleConfirm = ratingRepository.save(rating);
                    logger.info("Rating updated : {}", ratingHandleConfirm);
                    return new ResponseDTO(true, "Rating updated with success");
                } else {
                    logger.error("Rating id is null with this id : {}", ratingHandleConfirm);
                    return new ResponseDTO(false, "Rating id is null with this id : " + id);
                }
            } catch (Exception e) {
                logger.error("Impossible to updated the rating : {}", e.getMessage());
                return new ResponseDTO(false, "Impossible to update a rating");
            }
        } else {
            logger.error("Impossible to find the rating");
            return new ResponseDTO(false, "Impossible to find a rating");
        }
    }

    @Override
    public ResponseDTO deleteRatingById(int id) {
        logger.info("--- Method deleteRatingById ---");
        Optional<Rating> rating = ratingRepository.findById(id);
        if(rating.isPresent()) {
            Rating ratingPresent = rating.get();
            try {
                ratingRepository.delete(ratingPresent);
                logger.info("Rating deleted");
                return new ResponseDTO(true, "Rating deleted with success");
            } catch (Exception e) {
                logger.error("Impossible to delete the rating with this id({}) : {}", id, e.getMessage());
                return new ResponseDTO(false, "Impossible to delete a rating");
            }
        } else {
            logger.error("Impossible to find the rating with this id({})", id);
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

        if(id != 0) {
            Optional<Rating> ratingById = ratingRepository.findById(id);
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
