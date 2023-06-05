package com.fdmgroup.HappyNews.security;

import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;

import com.fdmgroup.HappyNews.repository.HappyUserRepository;
import com.fdmgroup.HappyNews.service.HappyUserDetailsService;


@Component
public class AuthProviderImpl implements AuthenticationProvider{
	
	private final HappyUserDetailsService userService;
	
	
	
	@Autowired
	public AuthProviderImpl(HappyUserDetailsService userService) {
		super();
		this.userService = userService;
	}

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		String email = authentication.getName();
		UserDetails userDetails = userService.loadUserByUsername(email);
		String password = authentication.getCredentials().toString();
		
		if(!hashingPassword(password).equals(userDetails.getPassword())){
			throw new BadCredentialsException("incorrect password");
		}
		
		return new UsernamePasswordAuthenticationToken(userDetails, password,userDetails.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		// TODO Auto-generated method stub
		return true;
	}
    
	public String hashingPassword(String password) {
		Integer hashedPassword = password.hashCode();
		return hashedPassword.toString();
	}
}
