package com.nnk.springboot;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.dto.response.ResponseDTO;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;
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
public class RuleTests {

	@Autowired
	private RuleNameService ruleNameService;

	@Test
	public void ruleTest() {
		RuleName rule = new RuleName("Rule Name", "Description", "Json", "Template", "SQL", "SQL Part");

		// Save
		ResponseDTO responseSave = ruleNameService.saveRuleName(rule);
		RuleNameDTO ruleNameDTOSAve = ruleNameService.getAllRuleName().get(0);

		assertNotNull(ruleNameDTOSAve.getId());
		assertEquals("Description", ruleNameDTOSAve.getDescription());
		assertEquals("RuleName saved with success", responseSave.getMessage());

		// Update
		rule.setDescription("Description new");
		rule.setId(ruleNameDTOSAve.getId());
		ResponseDTO responseUpdate = ruleNameService.updateRuleName(rule, ruleNameDTOSAve.getId());
		RuleNameDTO ruleNameDTOUpdate = ruleNameService.getAllRuleName().get(0);
		assertEquals("Description new", ruleNameDTOUpdate.getDescription());
		assertEquals("RuleName updated with success", responseUpdate.getMessage());

		// Find
		List<RuleNameDTO> listResult = ruleNameService.getAllRuleName();
		assertTrue(listResult.size() > 0);

		// Delete
		ResponseDTO responseDelete = ruleNameService.deleteRuleNameById(rule.getId());
		List<RuleNameDTO> list = ruleNameService.getAllRuleName();
		assertEquals(0, list.size());
		assertEquals("RuleName deleted with success", responseDelete.getMessage());
	}
}
