package com.codeup.codeupspringblog.repositories;

import com.codeup.codeupspringblog.models.AdUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<AdUser, Long> {

	AdUser findUserById(long id);
	AdUser findUserByUsername(String username);


}