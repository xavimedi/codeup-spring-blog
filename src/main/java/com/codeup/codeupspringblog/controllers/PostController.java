package com.codeup.codeupspringblog.controllers;

import com.codeup.codeupspringblog.models.Post;
import com.codeup.codeupspringblog.models.PostUser;
import com.codeup.codeupspringblog.models.PostUserWithRoles;
import com.codeup.codeupspringblog.repositories.PostRepository;
import com.codeup.codeupspringblog.repositories.PostUserRepository;
import com.codeup.codeupspringblog.services.EmailService;
import jakarta.transaction.Transactional;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.beans.Transient;

@Controller
public class PostController{

	private EmailService emailService;

	private final PostRepository postDao;

	private final PostUserRepository postUserDao;

	public PostController(PostRepository postDao, PostUserRepository postUserDao, EmailService emailService){
		this.emailService = emailService;
		this.postDao = postDao;
		this.postUserDao = postUserDao;
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
		PostUser postUser = (PostUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		Post post = new Post(title, body, postUser);
		postDao.save(post);

		emailService.sendAPostEmail(post, "Here's your post", "Post body");
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
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

		if (authentication.getPrincipal() instanceof PostUserWithRoles) {
			PostUserWithRoles authenticatedUserWithRoles = (PostUserWithRoles) authentication.getPrincipal();
			PostUser authenticatedUser = authenticatedUserWithRoles;

			if (post.getPostUser().getId() == authenticatedUser.getId()) {
				model.addAttribute("post", post);
				return "posts/edit";
			}else{
				return "redirect:/post/" + id;
			}
		}

		return "redirect:/posts/" + id;
	}

	@PostMapping("/posts/{id}/edit")
	public String submitOnePost(@PathVariable long id, @ModelAttribute Post post) {
		Post postToUpdate = postDao.findPostById(id);
		PostUser authenticatedUser = (PostUser) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (postToUpdate.getPostUser().getId() == authenticatedUser.getId()){
			postToUpdate.setTitle(post.getTitle());
			postToUpdate.setBody(post.getBody());
			postDao.save(postToUpdate);
		}else{
			System.out.println(postToUpdate.getPostUser());
			System.out.println(authenticatedUser);
		}
		return "redirect:/posts/" + id;
	}
}