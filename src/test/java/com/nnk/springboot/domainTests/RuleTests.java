package com.nnk.springboot.domainTests;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.dto.response.ResponseDTO;
import com.nnk.springboot.services.RuleNameService;
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
		rule.setName("Rule Name");
		rule.setDescription("Description new");
		rule.setId(ruleNameDTOSAve.getId());
		ResponseDTO responseUpdate = ruleNameService.updateRuleName(rule, rule.getId());
		RuleNameDTO ruleNameDTOUpdate = ruleNameService.getRuleNameById(rule.getId());
		assertEquals("Description new", ruleNameDTOUpdate.getDescription());
		assertEquals("RuleName updated with success", responseUpdate.getMessage());

		// Update with error
		ResponseDTO responseUpdateError = ruleNameService.updateRuleName(rule, 10);
		assertEquals("Impossible to find this ruleName", responseUpdateError.getMessage());


		// Find
		List<RuleNameDTO> listResult = ruleNameService.getAllRuleName();
		assertTrue(listResult.size() > 0);

		RuleNameDTO result = ruleNameService.getRuleNameById(ruleNameDTOUpdate.getId());
		assertEquals("Rule Name", result.getName());


		// Delete
		ResponseDTO responseDelete = ruleNameService.deleteRuleNameById(rule.getId());
		List<RuleNameDTO> list = ruleNameService.getAllRuleName();
		assertEquals(0, list.size());
		assertEquals("RuleName deleted with success", responseDelete.getMessage());

		// Delete with error
		ResponseDTO responseDeleteError = ruleNameService.deleteRuleNameById(10);
		assertEquals("Impossible to find this ruleName", responseDeleteError.getMessage());
	}
}
