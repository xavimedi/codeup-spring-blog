package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.PostUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostUserRepository extends JpaRepository<PostUser, Long> {

	PostUser findUserById(long id);
	PostUser findUserByUsername(String username);
}