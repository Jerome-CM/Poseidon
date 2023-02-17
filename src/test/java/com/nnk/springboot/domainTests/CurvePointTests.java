package com.nnk.springboot.domainTests;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.dto.response.ResponseDTO;
import com.nnk.springboot.services.CurvePointService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.junit4.SpringRunner;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Profile("test")
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

		// Save with error
		curvePoint.setCurveId(null);
		ResponseDTO responseSaveError = curvePointService.saveCurvePoint(curvePoint);
		assertEquals("Impossible to save a curvePoint", responseSaveError.getMessage());


		// Update
		curvePoint.setCurveId(10);
		curvePoint.setTerm(20d);
		curvePoint.setId(curvePointDTOSAve.getId());
		ResponseDTO responseUpdate = curvePointService.updateCurvePoint(curvePoint, curvePointDTOSAve.getId());
		CurvePointDTO curvePointDTOUpdate = curvePointService.getAllCurvePoint().get(0);
		assertEquals(20d, curvePointDTOUpdate.getTerm());
		assertEquals("CurvePoint updated with success", responseUpdate.getMessage());

		// Update with error
		ResponseDTO responseUpdateError = curvePointService.updateCurvePoint(curvePoint, 10);
		assertEquals("Impossible to find a curvePoint", responseUpdateError.getMessage());


		// Find
		List<CurvePointDTO> listResult = curvePointService.getAllCurvePoint();
		assertTrue(listResult.size() > 0);

		CurvePointDTO result = curvePointService.getCurvePointById(curvePointDTOUpdate.getId());
		assertEquals( 20d, result.getTerm());


		// Delete
		ResponseDTO responseDelete = curvePointService.deleteCurvePointById(curvePoint.getId());
		List<CurvePointDTO> list = curvePointService.getAllCurvePoint();
		assertEquals(0, list.size());
		assertEquals("CurvePoint deleted with success", responseDelete.getMessage());

		// Delete with error
		ResponseDTO responseDeleteError = curvePointService.deleteCurvePointById(10);
		assertEquals( "Impossible to find a curvePoint", responseDeleteError.getMessage());
	}

}
