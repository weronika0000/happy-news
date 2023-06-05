package com.fdmgroup.HappyNews.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import com.fdmgroup.HappyNews.HappyNewsApplication;
import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.model.Request;
import com.fdmgroup.HappyNews.service.HappyUserDetailsService;
import com.fdmgroup.HappyNews.service.MessageService;
import com.fdmgroup.HappyNews.service.RequestService;



@SpringBootTest(classes = {RequestController.class})
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = HappyNewsApplication.class)
public class RequestControllerTest {

	@InjectMocks
	RequestController mockRequestController;
	
	
	@MockBean
	MainController mockMainController;
	
	@MockBean
	HappyUserDetailsService mockDefaultUserService;
	
	@MockBean
	MessageService mockMessageService;
	
	@MockBean
	RequestService mockRequestService;
	
	
	
	@Autowired
	MockMvc mockMvc;
	
	@Mock
	Request mockRequest;
	
	
	@Mock
	HappyUser mockUser;
	
	@Mock
	Article mockArticle;
	
	@Test
	@WithMockUser
	public void test_confirmRequest_returnInbox_AddAttributeMessageRequestAccepted() throws Exception {
		mockUser.setRole("ROLE_ADMIN");
		HappyUser sender = new HappyUser("receiver","test@email.com",  "password");
		Article article = new Article("name", "test","19:00", "black", mockUser, "vehicle");
		Request request = new Request(sender, "test", false, LocalDateTime.now(), article);
		article.setStatus(true);
		article.setPublicationDate("10:00");
		when(mockRequestService.getRequestFromDatabase(0)).thenReturn(request);
//		System.out.println("TEST: " + mockRequestService.getRequestFromDatabase(0));
		
		
		mockMvc.perform(get("/confirmRequest").param("requestId", "0")).andExpect(status().isOk()).andExpect(model().attribute("message", "Request Accepted")).andExpect(view().name("inbox"));
		
	}
	
	@Test
	@WithMockUser
	public void test_rejectRequest_returnInbox_AddAttributeMessageRequestRejected() throws Exception {
		mockUser.setRole("ROLE_ADMIN");
		HappyUser sender = new HappyUser("receiver","test@email.com",  "password");
		Article article = new Article("name", "test","19:00", "black", mockUser, "vehicle");
		Request request = new Request(sender, "test", false, LocalDateTime.now(), article);
		
		when(mockRequestService.getRequestFromDatabase(0)).thenReturn(request);
//		System.out.println("TEST: " + mockRequestService.getRequestFromDatabase(0));

		
		mockMvc.perform(get("/rejectRequest").param("requestId", "0")).andExpect(status().isOk()).andExpect(model().attribute("message", "Request Rejected")).andExpect(view().name("inbox"));
		
	}	
	
}