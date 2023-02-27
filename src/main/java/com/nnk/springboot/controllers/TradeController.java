package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.Trade;
import com.nnk.springboot.services.TradeService;
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
public class TradeController {

    private final TradeService tradeService;
    public TradeController(TradeService tradeService) {
        this.tradeService = tradeService;
    }

    @RequestMapping("/trade/list")
    public String home(Model model)
    {
        log.info("--- View list ---");
        model.addAttribute("trades", tradeService.getAllTrade());
        return "trade/list";
    }

    @GetMapping("/trade/add")
    public String addTrade(Trade bid, Model model) {
        log.info("--- Method addTrade ---");
        model.addAttribute("trade", bid);
        return "trade/add";
    }

    @PostMapping("/trade/validate")
    public String validate(@Valid Trade trade, BindingResult result, Model model) {
        log.info("--- Method validate ---");
        if (!result.hasErrors()) {
            model.addAttribute("response", tradeService.saveTrade(trade));
            model.addAttribute("trades", tradeService.getAllTrade());
            return "/trade/list";
        }
        return "trade/add";
    }

    @GetMapping("/trade/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("--- Method showUpdateForm ---");
        model.addAttribute("trade", tradeService.getTradeById(id));
        return "trade/update";
    }

    @PostMapping("/trade/update/{id}")
    public String updateTrade(@PathVariable("id") Integer id, @Valid Trade trade,
                             BindingResult result, Model model) {

        log.info("--- Method updateTrade ---");
        if (result.hasErrors()) {
            return "trade/update";
        }
        model.addAttribute("response", tradeService.updateTrade(trade, id));
        model.addAttribute("trades", tradeService.getAllTrade());
        return "/trade/list";
    }

    @GetMapping("/trade/delete/{id}")
    public String deleteTrade(@PathVariable("id") Integer id, Model model) {
        log.info("--- Method deleteTrade ---");
        model.addAttribute("response", tradeService.deleteTradeById(id));
        model.addAttribute("trades", tradeService.getAllTrade());
        return "/trade/list";
    }
}
