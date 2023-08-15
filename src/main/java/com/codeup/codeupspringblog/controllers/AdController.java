package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Ad;
import com.codeup.codeupspringblog.models.AdUser;
//import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.AdUserRepository;
import com.codeup.codeupspringblog.repositories.AdRepository;
import com.codeup.codeupspringblog.services.EmailService;
import jakarta.transaction.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdController {

	private EmailService emailService;

	private final AdRepository adDao;

	private final AdUserRepository adUserDao;

	public AdController(AdRepository adDao, AdUserRepository adUserDao, EmailService emailService){
		this.emailService = emailService;
		this.adDao = adDao;
		this.adUserDao = adUserDao;
	}

	@GetMapping("/ads")
	public String index(Model model){
		model.addAttribute("ads", adDao.findAll());
		return "ads/index";
	}

	@GetMapping("/ads/create")
	public String showAdCreateForm(Model model) {
		model.addAttribute("ad", new Ad());
		return "ads/create";
	}

	@PostMapping("/ads/create")
	public String CreateAd(
			@RequestParam(name="title") String title,
			@RequestParam(name="description") String description){
		AdUser user = adUserDao.findUserById(1L);
		Ad ad = new Ad(title, description, user);
		adDao.save(ad);

		//send email to creator of ad
		emailService.sendAnAdEmail(ad, "Here's your ad", "Ad body");
		return "redirect:/ads";
	}

	@GetMapping("/ads/{id}")
	public String adView(@PathVariable long id, Model model) {
		Ad ad = adDao.findAdById(id);
		model.addAttribute("ad", ad);
		return "ads/show";
	}

	@GetMapping("/ads/{id}/edit")
	public String editOneAd(@PathVariable long id, Model model) {
		Ad ad = adDao.findAdById(id);
		model.addAttribute("ad", ad);
		return "/ads/edit";
	}

	@PostMapping("/ads/{id}/edit")
	public String submitOneAd(@PathVariable long id, @ModelAttribute Ad ad) {
		Ad adToUpdate = adDao.findAdById(id);
		adToUpdate.setTitle(ad.getTitle());
		adToUpdate.setDescription(ad.getDescription());
		adDao.save(adToUpdate);
		return "redirect:/ads/" + id;
	}
}