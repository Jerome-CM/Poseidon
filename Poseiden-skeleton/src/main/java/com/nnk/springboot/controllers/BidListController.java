package com.nnk.springboot.controllers;

import com.nnk.springboot.domain.BidList;
import com.nnk.springboot.domain.User;
import com.nnk.springboot.repositories.BidListRepository;
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
public class BidListController {

    private static final Logger logger = LogManager.getLogger(BidListController.class);

    public final BidListRepository bidListRepository;

    public BidListController(BidListRepository bidListRepository) {
        this.bidListRepository = bidListRepository;
    }

    @RequestMapping("/bidList/list")
    public String home(Model model)
    {
        logger.info("--- Method home ---");
        model.addAttribute("bidLists", bidListRepository.findAll());
        return "bidList/list";
    }

    @GetMapping("/bidList/add")
    public String addBidForm(BidList bid) {
        logger.info("--- Method addBidForm ---");
        return "bidList/add";
    }

    @PostMapping("/bidList/validate")
    public String validate(@Valid BidList bid, BindingResult result, Model model) {
        logger.info("--- Method validate ---");
        if (!result.hasErrors()) {
            bidListRepository.save(bid);
            model.addAttribute("bidLists", bidListRepository.findAll());
            return "redirect:/user/list";
        }
        return "bidList/add";
    }

    @GetMapping("/bidList/update/{id}")
    public String showUpdateForm(@PathVariable("id") Integer id, Model model) {
        logger.info("--- Method showUpdateForm ---");
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        model.addAttribute("bidList", bidList);
        return "bidList/update";
    }

    @PostMapping("/bidList/update/{id}")
    public String updateBid(@PathVariable("id") Integer id, @Valid BidList bidList,
                             BindingResult result, Model model) {
        logger.info("--- Method updateBid ---");
        if (result.hasErrors()) {
            return "bidList/update";
        }
        bidList.setBidListId(id);
        bidListRepository.save(bidList);
        model.addAttribute("bidLists", bidListRepository.findAll());
       return "redirect:/bidList/list";
    }

    @GetMapping("/bidList/delete/{id}")
    public String deleteBid(@PathVariable("id") Integer id, Model model) {
        logger.info("--- Method deleteBid ---");
        BidList bidList = bidListRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Invalid bidList Id:" + id));
        bidListRepository.delete(bidList);
        model.addAttribute("bidLists", bidListRepository.findAll());
        return "redirect:/bidList/list";
    }
}
