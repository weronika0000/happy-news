package com.fdmgroup.HappyNews.util;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.service.HappyUserDetailsService;


@Component
public class HappyUserValidator implements Validator{
	
	
	private final HappyUserDetailsService userDetailsService;
	
	@Autowired
	public HappyUserValidator(HappyUserDetailsService userDetailsService) {
		super();
		this.userDetailsService = userDetailsService;
	}

	@Override
	public boolean supports(Class<?> clazz) {
		return HappyUser.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		HappyUser happyUser= (HappyUser) target;
		
		try {
			userDetailsService.loadUserByUsername(happyUser.getUsername());
		}catch (UsernameNotFoundException ignored) {
			return;
		}
		
		errors.rejectValue("username","" , "User with this name exists");
		
	}

}
