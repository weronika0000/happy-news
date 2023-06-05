package com.fdmgroup.HappyNews.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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
import com.fdmgroup.HappyNews.model.Comment;
import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.model.Request;
import com.fdmgroup.HappyNews.service.ArticleService;
import com.fdmgroup.HappyNews.service.HappyUserDetailsService;

@SpringBootTest(classes = { MainController.class })
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = HappyNewsApplication.class)
public class MainControllerTest {

	
	
	@MockBean
	private ArticleService mockArticleService;
	
	@MockBean
	HappyUserDetailsService mockhappyUserService;

	

	

	@Autowired
	private MockMvc mockMvc;
	
	//: java.lang.AssertionError: View name expected:<index> but was:<> , what should i do?
	@Test
	@WithMockUser
	public void test_getIndex_returns_index() throws Exception{
		
		  List<Article> latestArticles = new ArrayList<>();
	        List<Article> topArticles = new ArrayList<>();

	        when(mockArticleService.listLatestSixArticles()).thenReturn(latestArticles);
	        when(mockArticleService.listTopSixArticles()).thenReturn(topArticles);

	        mockMvc.perform(get("/"))
	               .andExpect(status().isOk())
	               .andExpect(view().name("index"))
	               .andExpect(model().attribute("latestArticles", latestArticles))
	               .andExpect(model().attribute("topArticles", topArticles));

	        verify(mockArticleService).listLatestSixArticles();
	        verify(mockArticleService).listTopSixArticles();
	    }
	
	@Test
	@WithMockUser
	public void test_goshowUserInfo_statusOk_returnLoginview() throws Exception {

		mockMvc.perform(get("/showUserInfo")).andExpect(status().isOk()).andExpect(view().name("login"));
	}
	
	
	
	@Test
	@WithMockUser
	public void test_goToFAQ_statusOk() throws Exception {

		mockMvc.perform(get("/goToFAQ")).andExpect(status().isOk()).andExpect(view().name("FAQ"));
	}
	
	@Test
	@WithMockUser
	public void test_toPolicy_statusOk() throws Exception {

		mockMvc.perform(get("/toPrivacyPolicy")).andExpect(status().isOk()).andExpect(view().name("privacy"));
	}
	
	@Test
	@WithMockUser
	public void test_toTermsAndConditions() throws Exception {

		mockMvc.perform(get("/toTermsAndConditions")).andExpect(status().isOk()).andExpect(view().name("termsCondition"));
	}
	
	@Test
	@WithMockUser
	public void test_toContact_returnContact() throws Exception {

		mockMvc.perform(get("/toPrivacyPolicy")).andExpect(status().isOk()).andExpect(view().name("privacy"));
	}
	
	@Test
	@WithMockUser
	public void test_toAboutHappyNews() throws Exception {

		mockMvc.perform(get("/toAboutHappyNews")).andExpect(status().isOk()).andExpect(view().name("aboutHappyNews"));
	}
	
	@Test
	@WithMockUser
	public void test_toContact() throws Exception {

		mockMvc.perform(get("/toContact")).andExpect(status().isOk()).andExpect(view().name("contact"));
	}
}



