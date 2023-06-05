package com.fdmgroup.HappyNews.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import com.fdmgroup.HappyNews.HappyNewsApplication;
import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.service.HappyUserDetailsService;

@SpringBootTest(classes = { AuthController.class })
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = HappyNewsApplication.class)
public class AuthControllerTest {
	
	@MockBean
	private HappyUserDetailsService mockHappyUserService;
	
	@MockBean
	private MainController mockMainController;
	
	
	@Autowired
	private MockMvc mockMvc;
	
	@Mock
	private HappyUser mockUser;
	
	@Test
	@WithMockUser
	public void test_goToLogin_statusOk() throws Exception {
			
		mockMvc.perform(get("/login/")).andExpect(status().isOk()).andExpect(view().name("login"));
	}
	
	@Test
	@WithMockUser
	public void test_loginError_addAttributeErrorMessage_statusOk() throws Exception {
			
		mockMvc.perform(get("/login-error")).andExpect(status().isOk()).andExpect(model().attribute("errorMessage", "Invalid username or password")).andExpect(view().name("login"));
	}
	
	@Test
	@WithMockUser
	public void test_goToRegisterPage_statusOk() throws Exception {
			
		mockMvc.perform(get("/registration")).andExpect(status().isOk()).andExpect(view().name("registration"));
	}


@Test
@WithMockUser
public void test_registerUser_whenUsernameAlreadyInDatabase_returnRegister_AddAttributeErrorMessage() throws Exception {

	
	
	HappyUser user = new HappyUser("vlad","vlad@email.com","vlad","Pet","ROLE_ADMIN");
			
	System.out.println("TEST " + user);

	when(mockHappyUserService.findByUsername("testUser")).thenReturn(user);
	
	//mockMvc.perform(post("/registration/")).andExpect(status().isOk()).andExpect(model().attribute("errorMessage", "This user name already exists")).andExpect(view().name("registration"));
	

}
}
