package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.repositories.PostRepository;
import org.springframework.ui.Model;
import com.codeup.codeupspringblog.repositories.AdRepository;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PostController{

	private final PostRepository postRepository;

	public PostController(PostRepository postRepository) {
		this.postRepository = postRepository;
	}

	@GetMapping("/posts/index")
	public String index(Model model){
		model.addAttribute("posts", postRepository.findAll());
		return "posts/index";
	}

	@GetMapping("/posts/create")
	public String postsForm(){
		return "posts/create";
	}

	@PostMapping("/posts/create")
	public String create(@RequestParam String title, @RequestParam String body, Model model){
		Post newPost = new Post(title, body);
		postRepository.save(newPost);
		model.addAttribute("posts", postRepository.findAll());
		return "posts/index";
	}

	@GetMapping("/posts/{id}")
	public String postsHome(@PathVariable long id, Model model) {
		Post post = postRepository.findById(id).orElse(null);
		model.addAttribute("post", post);
		return "posts/show";
	}


}