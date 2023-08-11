package com.codeup.codeupspringblog.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "postuser")
public class PostUser {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String username;

	@Column(nullable = false)
	private String email;

	@Column(nullable = false)
	private String password;

	@OneToMany(mappedBy = "postUser", cascade = CascadeType.ALL)
	private List<Post> userPosts;

	public PostUser(PostUser copy) {
		id = copy.id;
		email = copy.email;
		username = copy.username;
		password = copy.password;
	}

	public PostUser() {
	}

	public PostUser(String title, String body, PostUser user) {
	}

	public List<Post> getPostUserPosts() {
		return userPosts;
	}

	public void setPostUserPosts(List<Post> userPosts) {
		this.userPosts = userPosts;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
