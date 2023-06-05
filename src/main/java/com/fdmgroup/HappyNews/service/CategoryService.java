package com.fdmgroup.HappyNews.service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.ModelMap;

import com.fdmgroup.HappyNews.controller.MainController;
import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.model.Category;
import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.model.Message;
import com.fdmgroup.HappyNews.repository.ArticleRepository;
import com.fdmgroup.HappyNews.repository.CategoryRepository;

@Service
public class CategoryService {
	
	
	@Autowired
	private final ArticleRepository articleRepository;
	
	@Autowired
	private final ArticleService articleService;
	
	
	@Autowired
	private final CategoryRepository categoryRepo;
	
	@Autowired 
	private final HappyUserDetailsService happyUserDetailsService;
	
	@Autowired 
	private  MessageService messageService;
	
	

	public CategoryService(ArticleRepository articleRepository,CategoryRepository categoryRepo,HappyUserDetailsService happyUserDetailsService,ArticleService articleService,MessageService messageService) {
		super();
		this.articleRepository = articleRepository;
		this.categoryRepo = categoryRepo;
		this.happyUserDetailsService = happyUserDetailsService;
		this.articleService = articleService;
	    this.messageService= messageService;
	}


	public List<Article> findArticleByCategory(String category) {
			List<Article> findByCategory = articleRepository.findByCategoryIgnoreCase(category);
		return findByCategory;
	}  
	
	 
	
	
	public List<Category> findAllCategoriesByUser(HappyUser user ){
		return categoryRepo.findAllCategoriesByUser(user);
	}
	
	public void createAndSaveNewCategory(String name, HappyUser user,ModelMap model) {
		if(checkingIsTheCategoryWithThisNameForThisUserExists(name, user)) {
			model.addAttribute("errorMessage", "you have this category added");
			System.out.println("=-------------------you have this category added");
		}else{
			Category category = new Category(name, user);
			categoryRepo.save(category);	
		}
		
	}
	
	public void deleteCategory(String categoryName, HappyUser user, ModelMap model) {
		if(!checkingIsTheCategoryWithThisNameForThisUserExists(categoryName, user)) {
			model.addAttribute("errorMessage", "this category not exist in your account");
		}
		List<Category> userCategories = findAllCategoriesByUser(user);
		for(Category category: userCategories) {
			if(category.getName().equals(categoryName)) {
				
				categoryRepo.deleteById(category.getCategoryId());
			}
		}
		}
	
		
		public boolean checkingIsTheCategoryWithThisNameForThisUserExists(String name, HappyUser user) {
			List<Category> userCategories = categoryRepo.findAllCategoriesByUser(user);
			System.out.println(" --------------------list " + userCategories);
			for(Category category:userCategories) {
				System.out.println("===============================================");
				System.out.println(category.getName());
				System.out.println("===============================================");
				if(category.getName().toLowerCase().equals(name.toLowerCase())) {
					return true;
				}
			}
			
			return false;
		}
		 
		
		public Set<Article> recommendedArticles(ModelMap model){
			List<Article> allArticles= articleService.findPostedarticles(articleRepository.findAll());
			List<Category> currentUserCategories = findAllCategoriesByUser(currentUserObject(model));
			Set<Article> articlesByUserCategories = new HashSet<>();
			
			for(Article article: allArticles) {
				for(Category category: currentUserCategories) {
					if(article.getCategory().toLowerCase().equals(category.getName().toLowerCase()) && articlesByUserCategories.size()< 6){
						articlesByUserCategories.add(article);
				}
			}
			
			}
			
			return articlesByUserCategories;
		}
		
		public HappyUser currentUserObject(ModelMap model) {
			String username = SecurityContextHolder.getContext().getAuthentication().getName();	
				HappyUser user = happyUserDetailsService.findUserByName(username);
				return user;
			
			
		}
		
		public List<Category> allCategporiesFromDB(){ 
			return categoryRepo.findAll();
		}
		
		
		public void sentMessageAterAddingTheArticle(Article article) {
			for(Category category: allCategporiesFromDB()) {
				if(category.getName().toLowerCase().equals(article.getCategory().toLowerCase())) {
					if(!(article.getAuthor().getId() == category.getUser().getId())) {
						String text = "New article from " + article.getCategory() + " is added! You can read it now !";
						Message message = new Message(category.getUser(), text, false, formattedCurrentDateTime());
						messageService.saveMessage(message);
					}
					
				}
			}
		}
		public LocalDateTime formattedCurrentDateTime() {
			LocalDateTime currentTime = LocalDateTime.now();
			DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
			String formattedCurrentTimeString = currentTime.format(dateTimeFormatter);
			
			return LocalDateTime.parse(formattedCurrentTimeString, dateTimeFormatter);
		}
		
		
	}
	

