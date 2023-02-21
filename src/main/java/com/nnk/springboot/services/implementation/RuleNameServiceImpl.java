package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.RuleName;
import com.nnk.springboot.dto.RuleNameDTO;
import com.nnk.springboot.dto.response.ResponseDTO;
import com.nnk.springboot.repositories.RuleNameRepository;
import com.nnk.springboot.services.RuleNameService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RuleNameServiceImpl implements RuleNameService {

    private static final Logger logger = LogManager.getLogger(RuleNameServiceImpl.class);

    private final RuleNameRepository ruleNameRepository;

    private final ModelMapper modelMapper;

    public RuleNameServiceImpl(RuleNameRepository ruleNameRepository, ModelMapper modelMapper) {
        this.ruleNameRepository = ruleNameRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseDTO saveRuleName(RuleName ruleName){
        logger.info("--- Method saveRuleName ---");
        try{
            ruleNameRepository.save(ruleName);
            logger.info("RuleName saved : {}", ruleName);
            return new ResponseDTO(true, "RuleName saved with success");
        } catch (Exception e){
            logger.error("Impossible to save a ruleName : {}", e.getMessage());
            return new ResponseDTO(false, "Impossible to save a ruleName");
        }
    }

    @Override
    public ResponseDTO updateRuleName(RuleName ruleName, int id){
        logger.info("--- Method updateRuleName ---");
        Optional<RuleName> ruleNameHandle = ruleNameRepository.findById(id);
        if(ruleNameHandle.isPresent()) {
            RuleName ruleNameHandleConfirm = ruleNameHandle.get();
            try {
                ruleName.setId(ruleNameHandleConfirm.getId());
                ruleNameHandleConfirm = ruleNameRepository.save(ruleName);
                logger.info("RuleName updated : {}", ruleNameHandleConfirm);
                return new ResponseDTO(true, "RuleName updated with success");

            } catch (Exception e) {
                logger.error("Impossible to updated the ruleName : {}", e.getMessage());
                return new ResponseDTO(false, "Impossible to update this ruleName : " + e.getMessage());
            }
        } else {
            logger.error("Impossible to find the ruleName");
            return new ResponseDTO(false, "Impossible to find this ruleName");
        }
    }

    @Override
    public ResponseDTO deleteRuleNameById(int id) {
        logger.info("--- Method deleteRuleNameById ---");
        Optional<RuleName> ruleName = ruleNameRepository.findById(id);
        if(ruleName.isPresent()) {
            RuleName ruleNameConfirm = ruleName.get();
            try {
                ruleNameRepository.delete(ruleNameConfirm);
                logger.info("RuleName deleted");
                return new ResponseDTO(true, "RuleName deleted with success");
            } catch (Exception e) {
                logger.error("Impossible to delete the ruleName with this id({}) : {}", id, e.getMessage());
                return new ResponseDTO(false, "Impossible to delete this ruleName : " + e.getMessage());
            }
        } else {
            logger.error("Impossible to find the ruleName with this id({})", id);
            return new ResponseDTO(false, "Impossible to find this ruleName");
        }
    }


    /**
     * Get RuleNameDTO for all ruleName
     * @return
     */
    @Override
    public List<RuleNameDTO> getAllRuleName() {

        List<RuleName> ruleNameList = ruleNameRepository.findAll();
        List<RuleNameDTO> ruleNameDTOList = new ArrayList<>();

        for(RuleName ruleName : ruleNameList){
            RuleNameDTO ruleNameDTO = modelMapper.map(ruleName, RuleNameDTO.class);
            ruleNameDTOList.add(ruleNameDTO);
        }

        return ruleNameDTOList;

    }

    /**
     * Get RuleNameDTO by Id
     * @param id
     * @return RuleNameDTO
     */
    @Override
    public RuleNameDTO getRuleNameById(int id) {

        Optional<RuleName> ruleNameById = ruleNameRepository.findById(id);
        if (ruleNameById.isPresent()) {
            return modelMapper.map(ruleNameById.get(), RuleNameDTO.class);
        } else {
            logger.error("RuleName not Found id : {})", id);
            throw new IllegalArgumentException("Invalid Id");
        }
    }

}
