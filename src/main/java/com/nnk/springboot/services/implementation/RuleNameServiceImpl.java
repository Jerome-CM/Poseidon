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
            return new ResponseDTO(false, "Impossible to save a ruleName : " + e.getMessage());
        }
    }

    @Override
    public ResponseDTO updateRuleName(RuleName ruleName, int id){
        logger.info("--- Method updateRuleName ---");
        try {
            RuleName ruleNameHandle = ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
            ruleName.setId(ruleNameHandle.getId());
            if (ruleName.getId() != null) {
                ruleNameHandle = ruleNameRepository.save(ruleName);
                logger.info("RuleName updated : {}", ruleNameHandle);
                return new ResponseDTO(true, "RuleName updated with success");
            } else {
                logger.error("RuleName id is null with this id : {}", ruleName);
                return new ResponseDTO(false, "RuleName id is null with this id : " + id);
            }
        } catch (Exception e) {
            logger.error("Impossible to updated the ruleName : {}", e.getMessage());
            return new ResponseDTO(false, "Impossible to update this ruleName : " + e.getMessage());
        }
    }

    @Override
    public ResponseDTO deleteRuleNameById(int id) {
        logger.info("--- Method deleteRuleNameById ---");
        try{
            RuleName ruleName = ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id));
            ruleNameRepository.delete(ruleName);
            logger.info("RuleName deleted");
            return new ResponseDTO(true, "RuleName deleted with success");
        } catch (Exception e){
            logger.error("Impossible to delete the ruleName with this id({}) : {}",id, e.getMessage());
            return new ResponseDTO(false, "Impossible to delete this ruleName : " + e.getMessage());
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

        if(id != 0) {
            Optional<RuleName> ruleNameById = Optional.ofNullable(ruleNameRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid ruleName Id:" + id)));
            if (ruleNameById.isPresent()) {
                return modelMapper.map(ruleNameById.get(), RuleNameDTO.class);
            } else {
                logger.error("RuleName not Found id : {})", id);
            }
        } else {
            throw new IllegalArgumentException("Invalid Id:" + id);
        }
        return null;
    }

}
