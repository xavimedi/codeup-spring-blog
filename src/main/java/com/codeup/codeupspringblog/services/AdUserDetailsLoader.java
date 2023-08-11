//package com.codeup.codeupspringblog.services;
//
//import com.codeup.codeupspringblog.models.AdUser;
//import com.codeup.codeupspringblog.models.AdUserWithRoles;
//import com.codeup.codeupspringblog.repositories.AdUserRepository;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//@Service
//public class AdUserDetailsLoader implements UserDetailsService {
//	private final AdUserRepository users;
//
//	public AdUserDetailsLoader(AdUserRepository users) {
//		this.users = users;
//	}
//
//	@Override
//	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//		AdUser adUser = users.findUserByUsername(username);
//		if (adUser == null) {
//			throw new UsernameNotFoundException("No user found for " + username);
//		}
//
//		return new AdUserWithRoles(adUser);
//	}
//}
