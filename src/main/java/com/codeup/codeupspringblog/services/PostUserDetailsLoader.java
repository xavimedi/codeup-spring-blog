package com.codeup.codeupspringblog.services;

import com.codeup.codeupspringblog.models.PostUser;
import com.codeup.codeupspringblog.models.PostUserWithRoles;
import com.codeup.codeupspringblog.repositories.PostUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class PostUserDetailsLoader implements UserDetailsService {
	private final PostUserRepository postUsers;

	public PostUserDetailsLoader(PostUserRepository postUsers) {
		this.postUsers = postUsers;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		PostUser postUser = postUsers.findUserByUsername(username);
		if (postUser == null) {
			throw new UsernameNotFoundException("No user found for " + username);
		}

		return new PostUserWithRoles(postUser);
	}
}
