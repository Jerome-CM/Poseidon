package com.nnk.springboot.domainTests;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.dto.response.ResponseDTO;
import com.nnk.springboot.repositories.RatingRepository;
import com.nnk.springboot.services.RatingService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RatingTests {

	@Autowired
	private RatingService ratingService;

	@Test
	public void ratingTest() {
		Rating rating = new Rating("Moodys Rating", "Sand PRating", "Fitch Rating", 10);

		// Save
		ResponseDTO responseSave = ratingService.saveRating(rating);
		RatingDTO ratingDTOSAve = ratingService.getAllRating().get(0);

		assertNotNull(ratingDTOSAve.getId());
		assertEquals(10, ratingDTOSAve.getOrderNumber());
		assertEquals("Rating saved with success", responseSave.getMessage());

		// Update
		rating.setOrderNumber(20);
		rating.setId(ratingDTOSAve.getId());
		ResponseDTO responseUpdate = ratingService.updateRating(rating, ratingDTOSAve.getId());
		RatingDTO ratingDTOUpdate = ratingService.getAllRating().get(0);
		assertEquals(20, ratingDTOUpdate.getOrderNumber());
		assertEquals("Rating updated with success", responseUpdate.getMessage());

		// Find
		List<RatingDTO> listResult = ratingService.getAllRating();
		assertTrue(listResult.size() > 0);

		// Delete
		ResponseDTO responseDelete = ratingService.deleteRatingById(rating.getId());
		List<RatingDTO> list = ratingService.getAllRating();
		assertEquals(0, list.size());
		assertEquals("Rating deleted with success", responseDelete.getMessage());
	}
}
