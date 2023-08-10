package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Ad;
import com.codeup.codeupspringblog.models.AdUser;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.AdUserRepository;
import com.codeup.codeupspringblog.repositories.AdRepository;
import jakarta.transaction.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class AdController {

	private final AdRepository adDao;

	private final AdUserRepository userDao;

	public AdController(AdRepository adDao, AdUserRepository userDao){
		this.adDao = adDao;
		this.userDao = userDao;
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
		AdUser user = userDao.findUserById(1L);
		Ad ad = new Ad(title, description, user);
		adDao.save(ad);
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