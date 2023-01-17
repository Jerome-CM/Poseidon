package com.nnk.springboot.services;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.dto.TradeDTO;
import com.nnk.springboot.dto.response.ResponseDTO;

import java.util.List;

public interface RuleNameService {

    public ResponseDTO saveRuleName(RuleName ruleName);

    public ResponseDTO updateRuleName(RuleName ruleName, int id);

    public ResponseDTO deleteRuleNameById(int id);
    public List<RuleNameDTO> getAllRuleName();

    public RuleNameDTO getRuleNameById(int id);





}
