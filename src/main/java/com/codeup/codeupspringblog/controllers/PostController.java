package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Ad;
import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.PostUser;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.PostUserRepository;
import jakarta.transaction.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.beans.Transient;

@Controller
public class PostController{

	private final PostRepository postDao;

	private final PostUserRepository userDao;

	public PostController(PostRepository postDao, PostUserRepository userDao){
		this.postDao = postDao;
		this.userDao = userDao;
	}

	@GetMapping("/posts")
	public String index(Model model){
		model.addAttribute("posts", postDao.findAll());
		return "posts/index";
	}

	@GetMapping("/posts/create")
	public String showPostCreateForm(Model model) {
		model.addAttribute("post", new Post());
		return "posts/create";
	}

	@PostMapping("/posts/create")
	public String CreatePost(
			@RequestParam(name="title") String title,
			@RequestParam(name="body") String body){
		PostUser user = userDao.findUserById(1L);
		Post post = new Post(title, body, user);
		postDao.save(post);
		return "redirect:/posts";
	}

	@GetMapping("/posts/{id}")
	public String postsView(@PathVariable long id, Model model) {
		Post post = postDao.findPostById(id);
		model.addAttribute("post", post);
		return "posts/show";
	}

	@GetMapping("/posts/{id}/edit")
	public String editOnePost(@PathVariable long id, Model model) {
		Post post = postDao.findPostById(id);
		model.addAttribute("post", post);
		return "/posts/edit";
	}

	@PostMapping("/posts/{id}/edit")
	public String submitOnePost(@PathVariable long id, @ModelAttribute Post post) {
		Post postToUpdate = postDao.findPostById(id);
		postToUpdate.setTitle(post.getTitle());
		postToUpdate.setBody(post.getBody());
		postDao.save(postToUpdate);
		return "redirect:/posts/" + id;
	}
}