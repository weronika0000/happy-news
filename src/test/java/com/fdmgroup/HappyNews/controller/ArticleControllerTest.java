package com.fdmgroup.HappyNews.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.time.LocalDate;
import java.util.ArrayList;
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

import com.fdmgroup.HappyNews.HappyNewsApplication;
import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.model.Comment;
import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.model.Request;
import com.fdmgroup.HappyNews.service.ArticleService;
import com.fdmgroup.HappyNews.service.CommentService;
import com.fdmgroup.HappyNews.service.HappyUserDetailsService;

@SpringBootTest(classes = { ArticleController.class })
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = HappyNewsApplication.class)
public class ArticleControllerTest {

	@MockBean
	MainController mockMainController;

	@MockBean
	HappyUserDetailsService mockhappyUserService;

	@MockBean
	private RequestController mockRequestController;

	@MockBean
	private ArticleService mockArticleService;

	@MockBean
	private CommentService mockCommentService;

	@Mock
	Article mockArticle;

	@Mock
	Request mockRequest;

	@Mock
	Comment mockComment;
	
	@Mock
	HappyUser happyUser;

	@Autowired
	private MockMvc mockMvc;

	@Test
	@WithMockUser
	public void test_goToArticlePage_return_index() throws Exception {

		List<Comment> mockListOfCommentsOfArticle = new ArrayList<>();
		// Article mockArticle = new Article();

		when(mockArticleService.findByArticleId(1)).thenReturn(mockArticle);
		when(mockCommentService.listOfCommentsForArticle(mockArticle)).thenReturn(mockListOfCommentsOfArticle);
		mockMvc.perform(get("/goToArticlePage/1")).andExpect(status().isOk()).andExpect(view().name("index"));


	}

	@Test
	@WithMockUser
	public void test_goToArticlePage_return_article() throws Exception {

		List<Comment> mockListOfCommentsOfArticle = new ArrayList<>();
		Article article = new Article();
		article.setStatus(true);
		
		when(mockArticleService.findOptionalByArticleId(5)).thenReturn(Optional.of(article));
		when(mockArticleService.findByArticleId(5)).thenReturn(article);
		when(mockCommentService.listOfCommentsForArticle(article)).thenReturn(mockListOfCommentsOfArticle);

		mockMvc.perform(get("/goToArticlePage/5")).andExpect(status().isOk())
				.andExpect(model().attribute("article", article))
				.andExpect(model().attribute("listOfCommentsOfArticle", mockListOfCommentsOfArticle));

	}
	
	
	
	@Test
	@WithMockUser
	public void test_goToAddArticle_returns_addArticle() throws Exception{
		
		when(mockArticleService.findByArticleId(1)).thenReturn(mockArticle);
		mockMvc.perform(get("/goToAddArticle")).andExpect(view().name("addArticle"));
		
	}

//	@Test
//	@WithMockUser
//	public void test_addArticleMethod() throws Exception {
//		
//		when(mockArticleService.findByArticleId(1)).thenReturn(mockArticle);
//		
//		
//	}

	@Test
	@WithMockUser
	public void test_goToEditArticle_returnEditArticle() throws Exception {
		
		when(mockArticleService.findByArticleId(2)).thenReturn(mockArticle);
		mockMvc.perform(get("/goToEditArticle/2")).andExpect(view().name("editArticle"));
		
	}

	@Test
	@WithMockUser
	public void test_EditArticleMethod_returnArticleView() throws Exception {
		HappyUser author = new HappyUser();
		author.setUsername("testauthor");

		Article article = new Article("testTitle", "testArticleText", "2023, 2, 9", "testLocation", author,
				"people");
		when(mockArticleService.findByArticleId(2)).thenReturn(mockArticle);
		mockMvc.perform(post("/editArticle/").param("title", "title").param("articleText", "articleText")
				.param("location", "location").param("author", "author").param("category", "people")
				.param("articleId", "2")).andExpect(status().isOk())
				.andExpect(model().attribute("article", mockArticle)).andExpect(view().name("article"));

	}

	@Test
	@WithMockUser
	public void test_deleteArticleMethod_returnArticleView() throws Exception {
		
		
		when(mockArticleService.findByArticleId(2)).thenReturn(mockArticle);
		mockMvc.perform(get("/deleteArticle/").param("articleId", "2")).andExpect(status().isOk()).andExpect(view().name("index"));
		
		
	}
// new tests
	@Test
	@WithMockUser
	public void test_goToArticlePage_return_index_when_article_status_false() throws Exception {
	    List<Comment> mockListOfCommentsOfArticle = new ArrayList<>();
	    Article article = new Article();
	    article.setStatus(false);

	    when(mockArticleService.findOptionalByArticleId(5)).thenReturn(Optional.of(article));
	    when(mockArticleService.findByArticleId(5)).thenReturn(article);
	    when(mockCommentService.listOfCommentsForArticle(article)).thenReturn(mockListOfCommentsOfArticle);

	    mockMvc.perform(get("/goToArticlePage/5"))
	            .andExpect(status().isOk())
	            .andExpect(view().name("index"));
	
	}	
	
}
