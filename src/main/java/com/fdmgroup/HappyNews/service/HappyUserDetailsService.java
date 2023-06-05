package com.fdmgroup.HappyNews.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.repository.HappyUserRepository;
import com.fdmgroup.HappyNews.security.HappyUserDetails;


@Service
public class HappyUserDetailsService implements UserDetailsService{
	
   private final HappyUserRepository userRepository;

   @Autowired
public HappyUserDetailsService(HappyUserRepository userRepository) {
	super();
	this.userRepository = userRepository;
}
 
@Override
public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	Optional<HappyUser> happyUserOptional = userRepository.findByEmail(email);
	
	if (happyUserOptional.isEmpty()) {
		throw new UsernameNotFoundException("User not found");
	}
	return new HappyUserDetails(happyUserOptional.get());
}
   
public UserDetails loadUserByEmailForPasswordChange(String email) throws UsernameNotFoundException {
	Optional<HappyUser> happyUser = userRepository.findByEmail(email);

	if (happyUser.isPresent()) {
		return new HappyUserDetails(happyUser.get());
	}
	throw new UsernameNotFoundException("User not found");
}

public HappyUser findByUserEmail(String email) {
	if(userRepository.findByEmail(email).isPresent()) {
		return userRepository.findByEmail(email).get();	
	}
	return new HappyUser("name","name","name");
	
}

public HappyUser findByUsername(String name) {
	if(userRepository.findByUsername(name).isPresent()) {
		return userRepository.findByUsername(name).get();	
	}
	return new HappyUser("name","name","name");
} 

 

public void saveUserToDb(HappyUserDetails happyUsDet) {
	userRepository.save(happyUsDet.getHappyUser());
}

     public HappyUser findUserByName(String name) {
    	 Optional<HappyUser> optional = userRepository.findByUsername(name);
    	 HappyUser user = optional.orElse(new HappyUser("default username"));
 		
 		return user;
    			 
     }
     
     public void saveUser(HappyUser happyUser) {
 		userRepository.save(happyUser);
 	}

	public Object findOptionalByUsername(String username) {
		Optional<HappyUser> optHappyUser = userRepository.findByUsername(username);
		return optHappyUser;
	}
}
