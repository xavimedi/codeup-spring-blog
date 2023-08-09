package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Ad;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.User;
import com.codeup.codeupspringblog.repositories.UserRepository;
import org.springframework.ui.Model;
import com.codeup.codeupspringblog.repositories.AdRepository;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdController {

	private final AdRepository adDao;

	private final UserRepository userDao;

	public AdController(AdRepository adDao, UserRepository userDao){
		this.adDao = adDao;
		this.userDao = userDao;
	}

	@GetMapping("/ads")
	public String index(Model model){
		model.addAttribute("ads", adDao.findAll());
		return "ads/index";
	}

	@GetMapping("ads/create")
	public String createAdForm(){
		return "ads/create";
	}

	@PostMapping("/ads/create")
	public String CreateAd(
			@RequestParam(name="title") String title,
			@RequestParam(name="description") String description){
		User user = userDao.findUserById(1L);
		Ad ad = new Ad(title, description, user);
		adDao.save(ad);
		return "redirect:/ads";
	}

	@GetMapping("/ads/{id}")
	public String showOneAd(@PathVariable long id, Model model) {
		Ad ad  = adDao.findAdById(id);
		if (ad != null){
			model.addAttribute(ad);
		}
		else{
			model.addAttribute("ad", new Ad("not found", "could not find ad"));
		}
		return "ads/show";
	}
}
