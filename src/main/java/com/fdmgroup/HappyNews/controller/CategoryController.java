package com.fdmgroup.HappyNews.controller;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.model.Category;

import com.fdmgroup.HappyNews.service.ArticleService;
import com.fdmgroup.HappyNews.service.CategoryService;



@Controller
public class CategoryController {
	@Autowired
	private final MainController mainController;
	
	@Autowired
	private final CategoryService categoryService;
	
	@Autowired
	private final ArticleService articleService;
	
	
	
	public CategoryController(MainController mainController, CategoryService categoryService,ArticleService articleService) {
		super();
		this.mainController = mainController;
		this.categoryService = categoryService;
		this.articleService = articleService;
	}



	@GetMapping("/{category}")
	public String goToCategory(ModelMap model,@PathVariable String category) {
		mainController.returnUserFromCurrentSession(model);
		
		 List<Article> listOfArticles = articleService.findPostedarticles(categoryService.findArticleByCategory(category));
		
		model.addAttribute("listOfArticles", listOfArticles);
		model.addAttribute("category", category);
	
		
		return "showArticleFromCategory";
	}
	
	//===================================================================================
	
	@GetMapping("/recomendation") 
	public String recommendation (ModelMap model) {
		mainController.returnUserFromCurrentSession(model);
		Set<Article> articlesByuserCategories = new HashSet<>();
		List<Category> allUserCategories = categoryService.findAllCategoriesByUser(mainController.currentUserObject(model));
		List<Article> allArticleFromDB = articleService.findAllArticles();
		for(Article article: allArticleFromDB) {
			for(Category category:allUserCategories) {
				if(category.getName().equals(article.getCategory())) {
					articlesByuserCategories.add(article);
				}
			}
		}
		
		
		
		// shoud return index page with top articles and recommendation;
		// shoud return last 6 reccomendation
		
		return "index";
	}
	
	
	  @GetMapping("/subscription") public String userCategories( ModelMap model)
	  { 
		  mainController.returnUserFromCurrentSession(model);
          List<Category> categoriesByUser = categoryService.findAllCategoriesByUser(mainController.currentUserObject(model));
		  
		  model.addAttribute("categoriesByUser", categoriesByUser);
	  return "subscription"; }
	 
	  
	  @PostMapping("/addCategory") 
	  public String addCategory(@RequestParam("category") String category, ModelMap model)
	 {     
		  System.out.println("============ category " + category);
		 System.out.println("---------------category name "+ category);
		  mainController.returnUserFromCurrentSession(model);
		  // set category from option in jsp
		  categoryService.createAndSaveNewCategory(category, mainController.currentUserObject(model), model);
		  return "redirect:/subscription";	 
	 }
	  
	  @GetMapping("/deleteCategory") 
	  public String deleteCategory(@RequestParam("categoryName") String categoryName, ModelMap model)
	 { 
		  mainController.returnUserFromCurrentSession(model);
		  //delete categoory from jsp button delete
		  categoryService.deleteCategory(categoryName, mainController.currentUserObject(model),model);	 
		  
		 return  "redirect:/subscription";
	 }
	  
	  
	  
}
