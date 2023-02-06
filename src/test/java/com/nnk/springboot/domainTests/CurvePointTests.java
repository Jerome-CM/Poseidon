package com.nnk.springboot;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.BidListDTO;
import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.dto.response.ResponseDTO;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;
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
public class CurvePointTests {

	@Autowired
	private CurvePointService curvePointService;

	@Test
	public void curvePointTest() {
		CurvePoint curvePoint = new CurvePoint(10, 10d, 30d);

	
		// Save
		ResponseDTO responseSave = curvePointService.saveCurvePoint(curvePoint);
		CurvePointDTO curvePointDTOSAve = curvePointService.getAllCurvePoint().get(0);

		assertNotNull(curvePointDTOSAve.getCurveId());
		assertEquals(10d, curvePointDTOSAve.getTerm());
		assertEquals("CurvePoint saved with success", responseSave.getMessage());

		// Update
		curvePoint.setTerm(20d);
		curvePoint.setId(curvePointDTOSAve.getId());
		ResponseDTO responseUpdate = curvePointService.updateCurvePoint(curvePoint, curvePointDTOSAve.getId());
		CurvePointDTO curvePointDTOUpdate = curvePointService.getAllCurvePoint().get(0);
		assertEquals(20d, curvePointDTOUpdate.getTerm());
		assertEquals("CurvePoint updated with success", responseUpdate.getMessage());

		// Find
		List<CurvePointDTO> listResult = curvePointService.getAllCurvePoint();
		assertTrue(listResult.size() > 0);

		// Delete
		ResponseDTO responseDelete = curvePointService.deleteCurvePointById(curvePoint.getId());
		List<CurvePointDTO> list = curvePointService.getAllCurvePoint();
		assertEquals(0, list.size());
		assertEquals("CurvePoint deleted with success", responseDelete.getMessage());
	}

}
