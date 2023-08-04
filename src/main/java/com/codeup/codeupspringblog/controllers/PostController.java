package com.codeup.codeupspringblog.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class PostController {

	@GetMapping("/posts")
	@ResponseBody
	public String postIndex(){
		StringBuilder posts = new StringBuilder();

		for(int i = 1; i < 100; i++){
			posts.append("<br>post #" + i + " <a href=\"https://www.youtube.com/watch?v=dQw4w9WgXcQ&ab\">(read more)</a>");
		}

		return "These are my posts: " + posts;
	}

	@GetMapping("/posts/{id}")
	@ResponseBody
	public String post(@PathVariable int id){
		return "This is post #" + id;
	}

	@GetMapping("/posts/create")
	@ResponseBody
	public String postCreate(){
		return "Fill out the post information";
	}

	@PostMapping("/posts/create")
	@ResponseBody
	public String postCreateSubmit(){
		return "You posted a post!";
	}

}