package com.fdmgroup.HappyNews.service;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.fdmgroup.HappyNews.HappyNewsApplication;
import com.fdmgroup.HappyNews.controller.RequestController;
import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.model.Comment;
import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.model.Request;
import com.fdmgroup.HappyNews.repository.ArticleRepository;
import com.fdmgroup.HappyNews.repository.CommentRepository;



	@SpringBootTest(classes = { CommentService.class })
	@ExtendWith(SpringExtension.class)
	@ExtendWith(MockitoExtension.class)
	@AutoConfigureMockMvc(addFilters = false)
	@ContextConfiguration(classes = HappyNewsApplication.class)
	public class CommentServiceTest {
		
		@InjectMocks
		private ArticleService mockArticleService;
		
		@InjectMocks
		private CommentService mockCommentService;
		
		@MockBean
		ArticleRepository mockArticleRepository;
		
		@MockBean
		CommentRepository mockCommentRepository;
		
		@MockBean
		HappyUserDetailsService mockhappyUserService;

		@MockBean
		private RequestController mockRequestController;

		@Mock
		Comment mockComment;
		
		@Mock
		Article mockArticle;
		
		@Mock
		Request mockRequest;
		
		@Mock
		HappyUser happyUser;
	
	
		@Test
		public void test_saveCommentmethod_callsSavemethodOfArticleRepository() {
			
			mockCommentService.saveComment(mockComment);
			verify(mockCommentRepository, times(1)).save(mockComment);
			
		}
		
		@Test
		public void test_deleteComment_method_callsDeleteByIdCommentRepositoryMethod() {
			
			mockCommentService.deleteComment(mockComment.getCommentId());
			verify(mockCommentRepository, times(1)).deleteById(mockComment.getCommentId());
			
		}
		
		@Test
		public void test_findCommentById_method_callsfindById_CommentRepositoryMethod() {
			
			mockCommentService.findCommentById(mockComment.getCommentId());
			verify(mockCommentRepository, times(1)).findById(mockComment.getCommentId());
			
		}
		
		
		@Test
		public void listOfCommentsForArticle_returns_lsit() {
			mockCommentService.listOfCommentsForArticle(mockArticle);
			verify(mockCommentRepository, times(1)).findByArticle(mockArticle);
		}
}
