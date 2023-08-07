package com.codeup.codeupspringblog.controllers;

import ch.qos.logback.core.model.Model;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping("/")
	@ResponseBody
	public String returnWelcomePage(){
		return "Welcome!";
	}

	@GetMapping("/home")
	public String welcome(){
		return "home";
	}



}