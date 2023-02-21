package com.nnk.springboot.domainTests;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.services.BidListService;
import com.nnk.springboot.dto.response.ResponseDTO;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@Profile("test")
@SpringBootTest
@RunWith(SpringRunner.class)
public class BidTests {

	@Autowired
	private BidListService bidListService;

	@Test
	public void bidListTest() {
		BidList bid = new BidList("Account Test", "Type Test", 10d);

		// Save
		ResponseDTO responseSave = bidListService.saveBidList(bid);
		BidListDTO bidDTOSAve = bidListService.getAllBidList().get(0);
		assertNotNull(bidDTOSAve.getBidListId());
		assertEquals(10d, bidDTOSAve.getBidQuantity());
		assertEquals("bidList saved with success", responseSave.getMessage());

		// Save with error
		bid.setAccount(null);
		ResponseDTO responseSaveError = bidListService.saveBidList(bid);
		assertEquals("Impossible to save a bidList", responseSaveError.getMessage());


		// Update
		bid.setAccount("Account Test");
		bid.setBidQuantity(20d);
		bid.setBidListId(bidDTOSAve.getBidListId());
		ResponseDTO responseUpdate = bidListService.updateBidList(bid, bid.getBidListId());
		BidListDTO bidDTOUpdate = bidListService.getBidListById(bid.getBidListId());
		assertEquals(20d, bidDTOUpdate.getBidQuantity());
		assertEquals("bidList updated with success", responseUpdate.getMessage());

		// Update with error
		ResponseDTO responseUpdateError = bidListService.updateBidList(bid, 10);
		assertEquals("Impossible to find a bidList", responseUpdateError.getMessage());


		// Find
		List<BidListDTO> listResult = bidListService.getAllBidList();
		assertTrue(listResult.size() > 0);

		BidListDTO result = bidListService.getBidListById(bidDTOSAve.getBidListId());
		assertEquals("Account Test", result.getAccount());


		// Delete
		ResponseDTO responseDelete = bidListService.deleteBidListById(bid.getBidListId());
		List<BidListDTO> list = bidListService.getAllBidList();
		assertEquals(0, list.size());
		assertEquals("bidList deleted with success", responseDelete.getMessage());

		// Delete with error
		ResponseDTO responseDeleteError = bidListService.deleteBidListById(10);
		assertEquals("Impossible to find a bidList", responseDeleteError.getMessage());

		// Get Error
		assertThrows(IllegalArgumentException.class, () -> bidListService.getBidListById(10));

	}

}
