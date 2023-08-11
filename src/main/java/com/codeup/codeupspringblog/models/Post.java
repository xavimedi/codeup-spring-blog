package com.codeup.codeupspringblog.models;

import jakarta.persistence.*;

@Entity
@Table(name = "posts")
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String title;

	@Column(nullable = false)
	private String body;

	@ManyToOne
	@JoinColumn(name = "postuser_id")
	private PostUser postUser;

	public Post(String title, String body, PostUser postUser) {
		this.title = title;
		this.body = body;
		this.postUser = postUser;
	}

	public Post(){
	}

	public PostUser getPostUser() {
		return postUser;
	}

	public void setPostUser(PostUser postUser) {
		this.postUser = postUser;
	}



	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}
}
