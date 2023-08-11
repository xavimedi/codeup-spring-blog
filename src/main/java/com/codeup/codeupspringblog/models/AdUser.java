//package com.codeup.codeupspringblog.models;
//
//import jakarta.persistence.*;
//
//import java.util.List;
//
//@Entity
//@Table(name = "aduser")
//public class AdUser {
//	@Id
//	@GeneratedValue(strategy = GenerationType.IDENTITY)
//	private long id;
//
//	@Column(nullable = false, unique = true)
//	private String username;
//
//	@Column(nullable = false, unique = true)
//	private String email;
//
//	@Column(nullable = false)
//	private String password;
//
//	@OneToMany(mappedBy = "adUser", cascade = CascadeType.ALL)
//	private List<Ad> userAds;
//
//	public AdUser(AdUser copy) {
//		id = copy.id;
//		email = copy.email;
//		username = copy.username;
//		password = copy.password;
//	}
//
//	public AdUser() {
//	}
//
//	public List<Ad> getUserAds() {
//		return userAds;
//	}
//
//	public void setUserAds(List<Ad> userAds) {
//		this.userAds = userAds;
//	}
//
//	public long getId() {
//		return id;
//	}
//
//	public void setId(long id) {
//		this.id = id;
//	}
//
//	public String getUsername() {
//		return username;
//	}
//
//	public void setUsername(String username) {
//		this.username = username;
//	}
//
//	public String getEmail() {
//		return email;
//	}
//
//	public void setEmail(String email) {
//		this.email = email;
//	}
//
//	public String getPassword() {
//		return password;
//	}
//
//	public void setPassword(String password) {
//		this.password = password;
//	}
//}
