package com.nnk.springboot.controllers;

import com.nnk.springboot.services.BidListService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController
{
	private static final Logger logger = LogManager.getLogger(HomeController.class);

	@Autowired
	private final BidListService bidListService;

	public HomeController(BidListService bidListService) {
		this.bidListService = bidListService;
	}

	@RequestMapping("/")
	public String home()
	{
		logger.info("--- Method home (index) ---");
		return "home";
	}

	@RequestMapping("/admin/home")
	public String adminHome(Model model) {
		logger.info("--- Method home Admin ---");
		model.addAttribute("bidLists", bidListService.getAllBidList());
		return "/bidList/list";
	}


}
