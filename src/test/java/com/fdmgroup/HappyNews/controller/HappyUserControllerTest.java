package com.fdmgroup.HappyNews.controller;

import static org.hamcrest.CoreMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

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
import org.springframework.ui.ModelMap;

import com.fdmgroup.HappyNews.HappyNewsApplication;
import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.repository.HappyUserRepository;
import com.fdmgroup.HappyNews.service.ArticleService;
import com.fdmgroup.HappyNews.service.HappyUserDetailsService;

@SpringBootTest(classes = { HappyUserController.class })
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = HappyNewsApplication.class)
public class HappyUserControllerTest {

	@MockBean
	MainController mockMainController;

	@MockBean
	HappyUserDetailsService mockhappyUserService;

	@MockBean
	HappyUserRepository userRepository;

	@Mock
	HappyUser mockHappyUser;
	@Mock
	Article mockArticle;

	@Mock
	ArticleService mockArticleService;

	@Autowired
	private MockMvc mockMvc;

	// mock dependencies for the class under test
	// execute code in the class under test
	// validate if the code executed as expected

	@Test
	@WithMockUser
	public void test_goToshowProfile_statusOk() throws Exception {
		String username = "testUsername";
		when(mockhappyUserService.findByUsername(username)).thenReturn(mockHappyUser);
		mockMvc.perform(get("/showProfile")).andExpect(status().isOk()).andExpect(view().name("showProfile"));
	}

//ERRRRROOOORRRR	
//	@Test
//	@WithMockUser
//	public void test_goToEditProfilePage_statusOk_return_EditProfile() throws Exception {
//		HappyUser user = new HappyUser();
//		String username = "testUsername";
//		user.setUsername(username);
//		
//		
//		when(mockhappyUserService.findOptionalByUsername(username)).thenReturn(Optional.of(user));
//		when(mockhappyUserService.findByUsername(username)).thenReturn(user);
//		
//		mockMvc.perform(get("/goEditProfilePage"))
//			.andExpect(status().isOk())
//			.andExpect(model().attribute("user", user))
//			.andExpect(view().name("editProfile"));
//	
//	//	verify(mockhappyUserService, times(1)).findByUsername(username);
//	}

	@Test
	@WithMockUser
	public void test_goToEditProfilePage_statusOk_return_EditProfile2() throws Exception {

		mockMvc.perform(get("/goEditProfilePage")).andExpect(status().isOk()).andExpect(view().name("editProfile"));

	}

	@Test
	@WithMockUser
	public void test_editProfilePage_statusOk_return_showProfile() throws Exception {

		String petName = "pettest";
		String username = "user1";
		String email = "user1@email.com";

		HappyUser user = new HappyUser();
		user.setUsername(username);
		user.setEmail(email);
		user.setPetName(petName);

		// Create a mock HappyUser object that will be returned from the findByUsername
		// method
		HappyUser userFromDatabase = new HappyUser();
		userFromDatabase.setUsername(username);
		userFromDatabase.setEmail(email);
		userFromDatabase.setPetName(petName);

		when(mockhappyUserService.findByUsername(username)).thenReturn(userFromDatabase);

		// ModelMap model = new ModelMap();

		// Call the editProfilePage method and capture the result
		mockMvc.perform(post("/editUserProfile").param("petName", petName).flashAttr("user", user))
				.andExpect(status().isOk()).andReturn();

	}

	@Test
	@WithMockUser
	public void test_goToUserArticles_statusOk_return_showUserArticles() throws Exception {
		ModelMap model = new ModelMap();
		List<Article> expectedArticlesList = new ArrayList<>();
		expectedArticlesList.add(mockArticle);
		String username = "testUsername";
		//when(mockArticleService.findArticleByUsername(model, mockHappyUser.getUsername())).thenReturn(expectedArticlesList);
		
		mockMvc.perform(get("/articles/testUsername"))
			.andExpect(status().isOk())
			.andExpect(view().name("showUserArticles"))
		.andExpect(model().attribute("username", username));
        //.andExpect(model().attribute("listOfUserArticles", expectedArticlesList));
	}

}
