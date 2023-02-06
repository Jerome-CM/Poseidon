package com.nnk.springboot.domainTests;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.dto.response.ResponseDTO;
import com.nnk.springboot.services.BidListService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
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

		// Update
		bid.setBidQuantity(20d);
		bid.setBidListId(bidDTOSAve.getBidListId());
		ResponseDTO responseUpdate = bidListService.updateBidList(bid, bidDTOSAve.getBidListId());
		BidListDTO bidDTOUpdate = bidListService.getAllBidList().get(0);
		assertEquals(20d, bidDTOUpdate.getBidQuantity());
		assertEquals("bidList updated with success", responseUpdate.getMessage());

		// Find
		List<BidListDTO> listResult = bidListService.getAllBidList();
		assertTrue(listResult.size() > 0);

		// Delete
		ResponseDTO responseDelete = bidListService.deleteBidListById(bid.getBidListId());
		List<BidListDTO> list = bidListService.getAllBidList();
		assertEquals(0, list.size());
		assertEquals("bidList deleted with success", responseDelete.getMessage());
	}
}
