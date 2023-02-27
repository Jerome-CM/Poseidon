package com.nnk.springboot.controllers;

import com.nnk.springboot.services.BidListService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Slf4j
public class HomeController {

	private final BidListService bidListService;

	public HomeController(BidListService bidListService) {
		this.bidListService = bidListService;
	}

	@RequestMapping("/")
	public String home()
	{
		log.info("--- View Home ---");
		return "home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model) {
		log.info("--- View list Admin ---");
		model.addAttribute("bidLists", bidListService.getAllBidList());
		return "/bidList/list";
	}


}
