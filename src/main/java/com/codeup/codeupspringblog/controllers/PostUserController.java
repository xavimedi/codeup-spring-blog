//package com.codeup.codeupspringblog.controllers;
//
//import com.codeup.codeupspringblog.models.PostUser;
//import com.codeup.codeupspringblog.repositories.PostUserRepository;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.ModelAttribute;
//import org.springframework.web.bind.annotation.PostMapping;
//
//@Controller
//public class PostUserController {
//	private PostUserRepository postUserDao;
//	private PasswordEncoder passwordEncoder;
//
//	public PostUserController(PostUserRepository postUserDao, PasswordEncoder passwordEncoder) {
//		this.postUserDao = postUserDao;
//		this.passwordEncoder = passwordEncoder;
//	}
//
//	@GetMapping("/sign-up")
//	public String showSignupForm(Model model){
//		model.addAttribute("user", new PostUser());
//		return "users/sign-up";
//	}
//
//	@PostMapping("/sign-up")
//	public String saveUser(@ModelAttribute PostUser postUser){
//		String hash = passwordEncoder.encode(postUser.getPassword());
//		postUser.setPassword(hash.toString());
//		postUserDao.save(postUser);
//		return "redirect:/posts";
//	}
//}