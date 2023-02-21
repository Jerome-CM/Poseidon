package com.nnk.springboot.services.implementation;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.dto.response.ResponseDTO;
import com.nnk.springboot.repositories.CurvePointRepository;
import com.nnk.springboot.services.CurvePointService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CurvePointServiceImpl implements CurvePointService {

    private static final Logger logger = LogManager.getLogger(CurvePointServiceImpl.class);

    private final CurvePointRepository curvePointRepository;

    private final ModelMapper modelMapper;

    public CurvePointServiceImpl(CurvePointRepository curvePointRepository, ModelMapper modelMapper) {
        this.curvePointRepository = curvePointRepository;
        this.modelMapper = modelMapper;
    }

    @Override
    public ResponseDTO saveCurvePoint(CurvePoint curvePoint){
        logger.info("--- Method saveCurvePoint ---");
        try{
            curvePointRepository.save(curvePoint);
            logger.info("CurvePoint saved : {}", curvePoint);
            return new ResponseDTO(true, "CurvePoint saved with success");
        } catch (Exception e){
            logger.error("Impossible to save a curvePoint : {}", e.getMessage());
            return new ResponseDTO(false, "Impossible to save a curvePoint");
        }
    }

    @Override
    public ResponseDTO updateCurvePoint(CurvePoint curvePoint, int id){
        logger.info("--- Method updateCurvePoint ---");
        Optional<CurvePoint> curvePointHandle = curvePointRepository.findById(id);
        if(curvePointHandle.isPresent()) {
            CurvePoint curvePointHandleConfirm = curvePointHandle.get();
            try {
                curvePoint.setId(curvePointHandleConfirm.getId());
                curvePointHandleConfirm = curvePointRepository.save(curvePoint);
                logger.info("CurvePoint updated : {}", curvePointHandleConfirm);
                return new ResponseDTO(true, "CurvePoint updated with success");
            } catch (Exception e) {
                logger.error("Impossible to updated the curvePoint : {}", e.getMessage());
                return new ResponseDTO(false, "Impossible to save a curvePoint");
            }
        } else {
            logger.error("Impossible to find the curvePoint");
            return new ResponseDTO(false, "Impossible to find a curvePoint");
        }
    }

    @Override
    public ResponseDTO deleteCurvePointById(int id) {
        logger.info("--- Method deleteCurvePointById ---");
        Optional<CurvePoint> curvePoint = curvePointRepository.findById(id);
        if(curvePoint.isPresent()){
            CurvePoint curvePointConfirm = curvePoint.get();
            try{
                curvePointRepository.delete(curvePointConfirm);
                logger.info("CurvePoint deleted");
                return new ResponseDTO(true, "CurvePoint deleted with success");
            } catch (Exception e){
                logger.error("Impossible to delete the curvePoint with this id({}) : {}",id, e.getMessage());
                return new ResponseDTO(false, "Impossible to delete a curvePoint");
            }
        } else {
            logger.error("Impossible to find the curvePoint with this id({})",id);
            return new ResponseDTO(false, "Impossible to find a curvePoint");
        }
    }


    /**
     * Get CurvePointDTO for all curvePoint
     * @return
     */
    @Override
    public List<CurvePointDTO> getAllCurvePoint() {

        List<CurvePoint> curvePointList = curvePointRepository.findAll();
        List<CurvePointDTO> curvePointDTOList = new ArrayList<>();

        for(CurvePoint curvePoint : curvePointList){
            CurvePointDTO curvePointDTO = modelMapper.map(curvePoint, CurvePointDTO.class);
            curvePointDTOList.add(curvePointDTO);
        }

        return curvePointDTOList;

    }

    /**
     * Get CurvePointDTO by Id
     * @param id
     * @return
     */
    @Override
    public CurvePointDTO getCurvePointById(int id) {

        Optional<CurvePoint> curvePointById = Optional.ofNullable(curvePointRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid curvePoint Id:" + id)));
        if (curvePointById.isPresent()) {
            return modelMapper.map(curvePointById.get(), CurvePointDTO.class);
        } else {
            logger.error("CurvePoint not Found id : {})", id);
            throw new IllegalArgumentException("Invalid Id");
        }
    }

}
