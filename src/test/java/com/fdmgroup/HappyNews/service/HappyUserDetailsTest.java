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
import com.fdmgroup.HappyNews.repository.HappyUserRepository;




@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class HappyUserDetailsTest {
	
	@InjectMocks
	HappyUserDetailsService service;
	
	@MockBean
	HappyUserRepository mockRepository;
	
	@Mock
	HappyUser mockUser;
	
	@Test
	public void test_findByUsername_callsFindByUsernameMethodOfHappyUserRepository() {
		service.findByUsername("testUser");
		
		verify(mockRepository, times(1)).findByUsername("vlad").get();
		
	}
	
	@Test
	public void test_findByEmail_callsFindByEmailMethodOfHappyUserRepository() {
		service.findByUserEmail("testUser");
		
		verify(mockRepository, times(1)).findByEmail("vlad@email.com").get();
		
	}
	
	@Test
	public void test_saveUserToDb_callsSaveMethodOfUserRepository() {
		service.saveUser(mockUser);
		
		verify(mockRepository, times(1)).save(mockUser);
		
	}

}
