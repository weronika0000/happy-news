package com.fdmgroup.HappyNews.service;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.model.Message;
import com.fdmgroup.HappyNews.repository.HappyUserRepository;
import com.fdmgroup.HappyNews.repository.MessageRepository;


@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class RegistrationServiceTest {
	@InjectMocks
	RegistrationService service;
	
	@MockBean
	HappyUserRepository userRepository;
	
	
	@Mock 
	HappyUser mockUser;
	
	@Test
	public void test_registerMethodOfRegistrationService_callsSaveMethodOfHappyUserRepository() {
		mockUser.setRole("ROLE_USER");
		userRepository.save(mockUser);
		
		verify(userRepository, times(1)).save(mockUser);
	}
}
