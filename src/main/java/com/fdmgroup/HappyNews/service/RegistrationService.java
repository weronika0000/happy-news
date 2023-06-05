package com.fdmgroup.HappyNews.service;

import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.repository.HappyUserRepository;


@Component
public class RegistrationService {
	
	private final HappyUserRepository userRepository;
		
	@Autowired
	public RegistrationService(HappyUserRepository userRepository) {
		super();
		this.userRepository = userRepository;
	} 
	
	@Transactional
	public void register(HappyUser happyUser) {
			happyUser.setRole("ROLE_USER");
			userRepository.save(happyUser);
	}
	
}
