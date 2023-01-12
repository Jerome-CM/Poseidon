package com.nnk.springboot.services;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.dto.CurvePointDTO;

import java.util.List;

public interface CurvePointService {

    public void saveCurvePoint(CurvePoint curvePoint);

    public CurvePointDTO updateCurvePoint(CurvePoint curvePoint, int id);

    public void deleteCurvePointById(int id);
    public List<CurvePointDTO> getAllCurvePoint();

    public CurvePointDTO getCurvePointById(int id);

}
