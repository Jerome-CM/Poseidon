package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.CurvePoint;
import com.nnk.springboot.services.CurvePointService;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@Slf4j
public class CurveController {

    private final CurvePointService curvePointService;

    public CurveController(CurvePointService curvePointService) {
        this.curvePointService = curvePointService;
    }

    @RequestMapping("/curvePoint/list")
    public String home(Model model)
    {
        log.info("--- View list ---");
        model.addAttribute("curvePoints", curvePointService.getAllCurvePoint());
        return "curvePoint/list";
    }

    @GetMapping("/curvePoint/add")
    public String addBidForm(CurvePoint curve, Model model) {
        log.info("--- Method addBidForm ---");
        model.addAttribute("curvePoint", curve);
        return "curvePoint/add";
    }

    @PostMapping("/curvePoint/validate")
    public String validate(@Valid CurvePoint curvePoint, BindingResult result, Model model) {
        log.info("--- Method validate ---");
        if (!result.hasErrors()) {
            model.addAttribute("response", curvePointService.saveCurvePoint(curvePoint));
            model.addAttribute("curvePoints", curvePointService.getAllCurvePoint());
            return "/curvePoint/list";
        }
        return "curvePoint/add";
    }

    @GetMapping("/curvePoint/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("--- Method showUpdateForm ---");
        model.addAttribute("curvePoint", curvePointService.getCurvePointById(id));
        return "curvePoint/update";
    }

    @PostMapping("/curvePoint/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid CurvePoint curvePoint,
                             BindingResult result, Model model) {
        log.info("--- Method updateBid ---");
        if (result.hasErrors()) {
            return "curvePoint/update";
        }
        model.addAttribute("response", curvePointService.updateCurvePoint(curvePoint, id));
        model.addAttribute("curvePoints", curvePointService.getAllCurvePoint());
        return "/curvePoint/list";
    }

    @GetMapping("/curvePoint/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        log.info("--- Method deleteBid ---");
        model.addAttribute("response", curvePointService.deleteCurvePointById(id));
        model.addAttribute("curvePoints", curvePointService.getAllCurvePoint());
        return "/curvePoint/list";
    }
}
