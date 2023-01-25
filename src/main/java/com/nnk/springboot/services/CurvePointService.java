package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDTO;
import com.nnk.springboot.dto.response.ResponseDTO;

import java.util.List;

public interface CurvePointService {

    public ResponseDTO saveCurvePoint(CurvePoint curvePoint);

    public ResponseDTO updateCurvePoint(CurvePoint curvePoint, int id);

    public ResponseDTO deleteCurvePointById(int id);
    public List<CurvePointDTO> getAllCurvePoint();

    public CurvePointDTO getCurvePointById(int id);

}
