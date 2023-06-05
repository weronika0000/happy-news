package com.fdmgroup.HappyNews.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;


import com.fdmgroup.HappyNews.HappyNewsApplication;
import com.fdmgroup.HappyNews.controller.RequestController;
import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.model.Comment;
import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.model.Request;
import com.fdmgroup.HappyNews.repository.ArticleRepository;
import com.fdmgroup.HappyNews.service.ArticleService;
import com.fdmgroup.HappyNews.service.CommentService;
import com.fdmgroup.HappyNews.service.HappyUserDetailsService;

@SpringBootTest(classes = { ArticleService.class })
@ExtendWith(SpringExtension.class)
@ExtendWith(MockitoExtension.class)
@AutoConfigureMockMvc(addFilters = false)
@ContextConfiguration(classes = HappyNewsApplication.class)
public class ArticleServiceTest {

	
	@InjectMocks
	private ArticleService articleService;
	
	@MockBean
	ArticleRepository mockArticleRepository;

	@MockBean
	HappyUserDetailsService mockhappyUserService;

	@MockBean
	private RequestController mockRequestController;

	
	@MockBean
	private CommentService mockCommentService;

	@Mock
	Article mockArticle;
	
	@Mock
	Request mockRequest;
	
	@Mock
	HappyUser happyUser;
	
	@Test
	public void test_saveArticlemethod_callsSavemethodOfArticleRepository() {
		
		articleService.saveArticle(mockArticle);
		verify(mockArticleRepository, times(1)).save(mockArticle);
		
	}

	@Test
	public void test_deleteArticle_method_callsDeleteByIdArticleRepositoryMethod() {
		
		articleService.deleteArticle(mockArticle.getArticleId());
		verify(mockArticleRepository, times(1)).deleteById(mockArticle.getArticleId());
		
	}
	
	@Test
	public void test_findByArticleId_method_callsDeleteByIdArticleRepositoryMethod() {
		
		articleService.findByArticleId(mockArticle.getArticleId());
		verify(mockArticleRepository, times(1)).findById(mockArticle.getArticleId());
		
	}
	
	@Test
	public void testFindPostedArticles() {
	    List<Article> allArticles = Arrays.asList(new Article(), new Article(), new Article());
	    List<Article> expectedListOfPostedArticles = Arrays.asList(new Article(), new Article());
	    allArticles.get(0).setStatus(true);
	    allArticles.get(1).setStatus(true);
	    
	   // when(mockArticleService.findPostedarticles(allArticles)).thenReturn(expectedListOfPostedArticles);
	    
	    //List<Article> actualListOfPostedArticles = mockArticleService.findPostedarticles(allArticles);
	    
	    
	}
	@Test
	public void testListSixPublishedArticlesWithEmptyList() {
		List<Article> listOfArticles = Collections.emptyList();
		List<Article> result = articleService.listSixPublishedArticles(listOfArticles);
		assertEquals(0, result.size());
	}
	


	// ================================== new ========================
	
	@Test
	public void test_listSixPublishedArticles_withEightArticles_return6articles() {
	    Article article1 = new Article();
	    article1.setStatus(true);

	    Article article2 = new Article();
	    article2.setStatus(true);

	    Article article3 = new Article();
	    article3.setStatus(true);

	    Article article4 = new Article();
	    article4.setStatus(true);

	    Article article5 = new Article();
	    article5.setStatus(true);

	    Article article6 = new Article();
	    article6.setStatus(true);

	    Article article7 = new Article();
	    article7.setStatus(true);

	    Article article8 = new Article();
	    article8.setStatus(true);

	    List<Article> listOfArticles = Arrays.asList(article1, article2, article3, article4, article5, article6, article7, article8);
	    List<Article> expectedList = Arrays.asList(article1, article2, article3, article4, article5, article6);
	    List<Article> actualList = articleService.listSixPublishedArticles(listOfArticles);

	    assertEquals(expectedList, actualList);
	    assertEquals(6, actualList.size());
	}
	
	@Test
	public void test_listSixPublishedArticles_withThreeArticles_returns3articles() {
	    Article article1 = new Article();
	    article1.setStatus(true);

	    Article article2 = new Article();
	    article2.setStatus(true);

	    Article article3 = new Article();
	    article3.setStatus(true);

	    List<Article> listOfArticles = Arrays.asList(article1, article2, article3);

	    List<Article> expectedList = Arrays.asList(article1, article2, article3);

	    List<Article> actualList = articleService.listSixPublishedArticles(listOfArticles);

	    assertEquals(expectedList, actualList);
	    assertEquals(3, actualList.size());
	}
	@Test
	public void test_findAllArticles_returnsListOfArticles_and_callsFindAll_articleRepositoryMethod() {
	    Article article1 = new Article();
	    Article article2 = new Article();
	   

	    List<Article> expectedArticles = Arrays.asList(article1, article2);
	    when(mockArticleRepository.findAll()).thenReturn(expectedArticles);
	    List<Article> actualArticles = articleService.findAllArticles();
	    assertEquals(expectedArticles, actualArticles);
	}
	
	  @Test
	    public void testListLatestSixArticles_ReturnsSixArticles_andCallsFindLatestRepositoryMethod() {
	       
	        Article article1 = new Article();
	        article1.setStatus(true);
		    Article article2 = new Article();
		    article2.setStatus(true);
		    Article article3 = new Article();
		    article3.setStatus(true);
		    Article article4 = new Article();
		    article4.setStatus(true);
		    Article article5 = new Article();
		    article5.setStatus(true);
		    Article article6 = new Article();
		    article6.setStatus(true);

		    List<Article> mockArticlesFromDatabase = Arrays.asList(article1, article2, article3, article4, article5, article6);
	        
	        when(mockArticleRepository.findLatestArticles()).thenReturn(mockArticlesFromDatabase);

	        List<Article> result = articleService.listLatestSixArticles();

	       assertEquals(6, result.size());
	       verify(mockArticleRepository, times(1)).findLatestArticles();
	    }
	  
	  
	  @Test
	    public void testListTopSixArticles_ReturnsSixArticles_andCallsFindLatestRepositoryMethod() {
	       
	        Article article1 = new Article();
	        article1.setStatus(true);
		    Article article2 = new Article();
		    article2.setStatus(true);
		    Article article3 = new Article();
		    article3.setStatus(true);
		    Article article4 = new Article();
		    article4.setStatus(true);
		    Article article5 = new Article();
		    article5.setStatus(true);
		    Article article6 = new Article();
		    article6.setStatus(true);

		    List<Article> mockArticlesFromDatabase = Arrays.asList(article1, article2, article3, article4, article5, article6);
	        
	        when(mockArticleRepository.findTopSixByOrderByNumberOfCommentsDesc()).thenReturn(mockArticlesFromDatabase);

	        List<Article> result = articleService.listTopSixArticles();

	       assertEquals(6, result.size());
	       verify(mockArticleRepository, times(1)).findTopSixByOrderByNumberOfCommentsDesc();
	    }
	  
	  @Test
	    public void testFindLatestArticlesByCategory_returnLifestyleListIfWechooseIt() {
	        // Arrange
	        Article article1 = new Article();
	        article1.setStatus(true);
	        article1.setCategory("lifestyle");
	      
	        Article article2 = new Article();
	        article2.setStatus(true);
	        article2.setCategory("lifestyle");
	      
	        Article article3 = new Article();
	        article3.setStatus(true);
	        article3.setCategory("lifestyle");
	       
	        List<Article> mockArticlesFromDatabase = Arrays.asList(article1, article2, article3);

	        // Configure mock ArticleRepository
	        when(mockArticleRepository.findByCategoryIgnoreCase("lifestyle")).thenReturn(
	                Arrays.asList(article1, article2, article3));
	        when(mockArticleRepository.findLatestArticles()).thenReturn(mockArticlesFromDatabase);

	        // Act
	        List<Article> lifestyleArticles = articleService.findLatestArticlesByCategory("lifestyle");
	       
	        // Assert Verify the results
	        List<Article> expected1 = Arrays.asList(article1, article2, article3);
	      
	        assertEquals(expected1, lifestyleArticles);
	       
	    }
	  
	  
	  @Test
	    public void testFindLatestArticlesByCategory_returnListOfArticlesFromChosenCategory() {
	        // Arrange
	        Article article1 = new Article();
	        article1.setStatus(true);
	        article1.setCategory("lifestyle");
	      
	        Article article2 = new Article();
	        article2.setStatus(true);
	        article2.setCategory("lifestyle");
	       
	        Article article3 = new Article();
	        article3.setStatus(true);
	        article3.setCategory("science");
	     
	        Article article4 = new Article();
	        article4.setStatus(true);
	        article4.setCategory("culture");
	      
	        Article article5 = new Article();
	        article5.setStatus(false);
	        article5.setCategory("lifestyle");
	       
	        List<Article> mockArticlesFromDatabase = Arrays.asList(article1, article2, article3, article4, article5);

	        // Configure mock ArticleRepository
	        when(mockArticleRepository.findByCategoryIgnoreCase("lifestyle")).thenReturn(
	                Arrays.asList(article1, article2, article5));
	        when(mockArticleRepository.findByCategoryIgnoreCase("science")).thenReturn(
	                Arrays.asList(article3));
	        when(mockArticleRepository.findByCategoryIgnoreCase("culture")).thenReturn(
	                Arrays.asList(article4));
	        when(mockArticleRepository.findLatestArticles()).thenReturn(mockArticlesFromDatabase);

	        // Act
	        List<Article> result1 = articleService.findLatestArticlesByCategory("lifestyle");
	        List<Article> result2 = articleService.findLatestArticlesByCategory("science");
	        List<Article> result3 = articleService.findLatestArticlesByCategory("culture");

	        // Assert Verify the results
	        List<Article> expected1 = Arrays.asList(article2, article1);
	        List<Article> expected2 = Arrays.asList(article3);
	        List<Article> expected3 = Arrays.asList(article4);
	        assertEquals(expected1, result1);
	        assertEquals(expected2, result2);
	        assertEquals(expected3, result3);
	    }
	  
	    @Test
	    public void testFindOptionalByArticleId() {
	        // Arrange
	        Article article = new Article();
	        Integer articleId = 1;
	        article.setTitle("Test Article");
	        article.setStatus(true);

	        // Stub repository method to return optional of article
	        when(mockArticleRepository.findById(articleId)).thenReturn(Optional.of(article));

	        // Act, Call the service method to get optional of article
	        Optional<Article> articleOpt = articleService.findOptionalByArticleId(1);

	        // Assert that the articleOpt contains the expected article
	        assertNotNull(articleOpt);
	        assertTrue(articleOpt.isPresent());
	        assertEquals(article, articleOpt.get());
	    }
	}
	  
	  
	  
	
	  


