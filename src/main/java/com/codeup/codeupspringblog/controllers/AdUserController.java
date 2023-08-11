//package com.codeup.codeupspringblog.controllers;
//
//import com.codeup.codeupspringblog.models.AdUser;
//import com.codeup.codeupspringblog.repositories.AdUserRepository;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class AdUserController {
//	private AdUserRepository adUserDao;
//	private PasswordEncoder passwordEncoder;
//
//	public AdUserController(AdUserRepository AdUserDao, PasswordEncoder passwordEncoder) {
//		this.adUserDao = adUserDao;
//		this.passwordEncoder = passwordEncoder;
//	}
//
//	@GetMapping("/sign-up")
//	public String showSignupForm(Model model){
//		model.addAttribute("user", new AdUser());
//		return "users/sign-up";
//	}
//
//	@PostMapping("/sign-up")
//	public String saveUser(@ModelAttribute AdUser adUser){
//		String hash = passwordEncoder.encode(adUser.getPassword());
//		adUser.getPassword();
//		adUserDao.save(adUser);
//		return "redirect:/login";
//	}
//}
