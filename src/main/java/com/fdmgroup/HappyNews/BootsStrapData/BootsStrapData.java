package com.fdmgroup.HappyNews.BootsStrapData;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.repository.HappyUserRepository;

@Component
public class BootsStrapData implements CommandLineRunner {
	
	private final HappyUserRepository userRepository;
	

	public BootsStrapData(HappyUserRepository userRepository) {
		super();
		this.userRepository = userRepository;
		
	}

	
	@Override
	public void run(String... args) throws Exception {
		
		System.out.println("Started in Bootstrap");
		
//		HappyUser user1 = new HappyUser("vlad", "vlad@email.com", "vlad","ROLE_ADMIN","Pet");
//		HappyUser user2 = new HappyUser("Ewa", "ewa@nowak.com", "admin2","ROLE_USER", "Pet");
//		
//		userRepository.save(user1);
//		userRepository.save(user2);
		
		System.out.println("User count is " + userRepository.count());
	

	 
	}
	
	
}