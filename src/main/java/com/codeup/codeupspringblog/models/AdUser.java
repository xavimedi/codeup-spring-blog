package com.codeup.codeupspringblog.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "users")
public class AdUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)

	private long id;

	@Column(nullable = false, unique = true)
	private String username;

	@Column(nullable = false, unique = true)
	private String email;

	@Column(nullable = false)
	private String password;

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
	private List<Ad> ads;

	@OneToMany(cascade = CascadeType.PERSIST, mappedBy = "user")
	private List<Post> posts;

	public AdUser(){
	}

	public List<Ad> getAds() {
		return ads;
	}

	public List<Post> getPosts(){
		return posts;
	}

	public void setAds(List<Ad> ads) {
		this.ads = ads;
	}

	public void setPosts(List<Post> posts){
		this.posts = posts;
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
