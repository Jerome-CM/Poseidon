package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.dto.TradeDTO;

import java.util.List;

public interface RuleNameService {

    public void saveRuleName(RuleName ruleName);

    public RuleNameDTO updateRuleName(RuleName ruleName, int id);

    public void deleteRuleNameById(int id);
    public List<RuleNameDTO> getAllRuleName();

    public RuleNameDTO getRuleNameById(int id);





}
