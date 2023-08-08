package com.codeup.codeupspringblog.controllers;

import org.springframework.ui.Model;
import com.codeup.codeupspringblog.repositories.AdRepository;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdController {

	private final AdRepository adDao;

	public AdController(AdRepository adDao){
		this.adDao = adDao;
	}

	@GetMapping("/ads")
	public String index(Model model){
		model.addAttribute("ads", adDao.findAll());
		return "ads/index";
	}
}
