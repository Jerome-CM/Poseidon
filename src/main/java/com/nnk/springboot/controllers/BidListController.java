package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.services.BidListService;
import lombok.extern.slf4j.Slf4j;
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
public class BidListController {

    private final BidListService bidListService;

    public BidListController(BidListService bidListService) {
        this.bidListService = bidListService;
    }

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        log.info("--- View list ---");
        model.addAttribute("bidLists", bidListService.getAllBidList());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid, Model model) {
        log.info("--- Method addBidForm ---");
        model.addAttribute("bidList", bid);
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        log.info("--- Method validate ---");
        if (!result.hasErrors()) {
            model.addAttribute("response", bidListService.saveBidList(bid));
            model.addAttribute("bidLists", bidListService.getAllBidList());
            return "/bidList/list";
        }
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        log.info("--- Method showUpdateForm ---");
        model.addAttribute("bidList", bidListService.getBidListById(id));
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        log.info("--- Method updateBid ---");
        if (result.hasErrors()) {
            return "bidList/update";
        }
        model.addAttribute("response", bidListService.updateBidList(bidList, id));
        model.addAttribute("bidLists", bidListService.getAllBidList());
       return "/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        log.info("--- Method deleteBid ---");
        model.addAttribute("response", bidListService.deleteBidListById(id));
        model.addAttribute("bidLists", bidListService.getAllBidList());
        return "/bidList/list";
    }
}
