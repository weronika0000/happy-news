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
import org.springframework.ui.ModelMap;

import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.model.Category;
import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.model.Message;
import com.fdmgroup.HappyNews.repository.ArticleRepository;
import com.fdmgroup.HappyNews.repository.CategoryRepository;
import com.fdmgroup.HappyNews.repository.MessageRepository;

@ExtendWith(MockitoExtension.class)
@ExtendWith(SpringExtension.class)
public class CategoryServiceTest {

	
	
	@InjectMocks
	CategoryService service;

	@MockBean
	CategoryRepository mockRepository;
	@MockBean
	ArticleRepository mockArticleRepository;
	
	@Mock 
	Category mockCategory;
	
	@Mock 
	HappyUser mockUser;
	
	@Mock
	Article article;
	
	@Mock
	ModelMap model;
	
	
	
	@Test 
	public void test_findArticleByCategoryMethodOfCategoryService_callsfindByCategoryIgnoreCaseofArticleRepository() {
		
		mockArticleRepository.findByCategoryIgnoreCase("category");
		
		verify(mockArticleRepository, times(1)).findByCategoryIgnoreCase("category");
	}
	
	@Test 
	public void test_findAllCategoriesByUserMethodOfCategoryService_callsfindAllCategoriesByUserCategoryRepository() {
		
		mockRepository.findAllCategoriesByUser(mockUser);
		
		verify(mockRepository, times(1)).findAllCategoriesByUser(mockUser);
	}
	
	@Test 
	public void test_createAndSaveNewCategoryMethodOfCategoryService_callsfindAllCategoriesByUserCategoryRepository() {
		
		mockRepository.findAllCategoriesByUser(mockUser);
		
		verify(mockRepository, times(1)).findAllCategoriesByUser(mockUser);
	}
	
	
	
	@Test 
	public void test_deleteCategoryMethodOfCategoryService_callsDeleteCategoryRepository() {
		
		service.deleteCategory("String", mockUser, model);
		
		verify(mockRepository, times(1)).deleteById(mockCategory.getCategoryId());
	}
	
	
}
