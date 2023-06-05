package com.fdmgroup.HappyNews.controller;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import java.util.ArrayList;
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
import com.fdmgroup.HappyNews.service.CommentService;
import com.fdmgroup.HappyNews.service.HappyUserDetailsService;

@SpringBootTest(classes = { ArticleController.class })
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = HappyNewsApplication.class)
public class CommentControllerTest {

	
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
	Comment mockComment;
	
	@Mock
	Request mockRequest;
	
	@Mock
	HappyUser happyUser;
	
	@Autowired
	private MockMvc mockMvc;
	
	
	@Test
	@WithMockUser
	public void test_addComment_return_article() throws Exception {
		HappyUser commentator = new HappyUser();
		List<Comment> mockListOfCommentsOfArticle = new ArrayList<>();
		
		//Article 
		//Comment comment = new Comment(commentator articleToComment, formattedDate, commentText);
		when(mockArticleService.findByArticleId(1)).thenReturn(mockArticle);
		when(mockCommentService.findCommentById(2)).thenReturn(mockComment);
		
		when(mockCommentService.listOfCommentsForArticle(mockArticle)).thenReturn(mockListOfCommentsOfArticle);
		
		mockMvc.perform(post("/addComment").param("articleId", "1").param("commentator", "commentator")
				.param("commentText", "commentText").param("parentCommentId", "1"))
				.andExpect(status().isOk()).andExpect(view().name("article"));
	
	
}
	
	@Test
	@WithMockUser
	public void test_addCommentReply_return_article() throws Exception {
		HappyUser commentator = new HappyUser();
		List<Comment> mockListOfCommentsOfArticle = new ArrayList<>();
		
		//Article 
		//Comment comment = new Comment(commentator articleToComment, formattedDate, commentText);
		when(mockArticleService.findByArticleId(1)).thenReturn(mockArticle);
		when(mockCommentService.findCommentById(2)).thenReturn(mockComment);
		when(mockCommentService.listOfCommentsForArticle(mockArticle)).thenReturn(mockListOfCommentsOfArticle);
		
		mockMvc.perform(post("/addCommentReply").param("articleId", "1").param("commentator", "commentator")
				.param("commentText", "commentText").param("parentCommentId", "1"))
				.andExpect(status().isOk()).andExpect(view().name("article"));
	
	
}
	
}