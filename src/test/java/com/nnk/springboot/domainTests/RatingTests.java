package com.nnk.springboot.domainTests;

import com.nnk.springboot.domain.Rating;
import com.nnk.springboot.dto.RatingDTO;
import com.nnk.springboot.dto.response.ResponseDTO;
import com.nnk.springboot.services.RatingService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@Profile("test")
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
		System.out.println("------- Rating before update TEST" + rating);
		ResponseDTO responseUpdate = ratingService.updateRating(rating, rating.getId());
		RatingDTO ratingDTOUpdate = ratingService.getRatingById(rating.getId());
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

		// Get Error
		assertThrows(IllegalArgumentException.class, () -> ratingService.getRatingById(10));

	}
}
