package com.fdmgroup.HappyNews.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.eclipse.jdt.internal.compiler.batch.Main;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.model.Comment;
import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.repository.ArticleRepository;
import com.fdmgroup.HappyNews.repository.HappyUserRepository;
import com.fdmgroup.HappyNews.security.HappyUserDetails;
import com.fdmgroup.HappyNews.service.ArticleService;
import com.fdmgroup.HappyNews.service.CategoryService;
import com.fdmgroup.HappyNews.service.CommentService;
import com.fdmgroup.HappyNews.service.HappyUserDetailsService;
import com.fdmgroup.HappyNews.util.Filters;

@Controller
public class ArticleController {

	@Autowired
	ArticleRepository articleRepository;

	@Autowired
	ArticleService articleService;

	@Autowired
	MainController maincontroller;
	
	@Autowired
	RequestController requestController;

	@Autowired
	HappyUserDetailsService happyUserService;
	
	@Autowired
	CategoryService categoryService;
	
	@Autowired 
	CommentService commentService;
	
	@GetMapping(value = "/goToArticlePage/{articleId}")
	public String goToArticlePage(ModelMap model, @PathVariable int articleId) {
		maincontroller.returnUserFromCurrentSession(model);
          
		Article article = articleService.findByArticleId(articleId);
		List<Comment> listOfCommentsOfArticle = commentService.listOfCommentsForArticle(article);
		Optional<Article> optional = articleService.findOptionalByArticleId(articleId);
		if(optional.isEmpty()) {
			return "index";
		}
		if(!article.isStatus()) {
			return "index";
		}
		model.addAttribute("article", article);
		model.addAttribute("listOfCommentsOfArticle", listOfCommentsOfArticle);
		
		List<Article> latestArticlesinCategory = articleService.findLatestArticlesByCategory(article.getCategory());
		model.addAttribute("latestArticlesinCategory", latestArticlesinCategory);
		return "article";

	}

	@GetMapping(value = "/goToAddArticle")
	public String goToAddArticle(ModelMap model) {
		maincontroller.returnUserFromCurrentSession(model);
		return "addArticle";
	}

	
	@PostMapping("/addArticle")
	public String addArticle(ModelMap model, @RequestParam String title, @RequestParam String articleText,
			@RequestParam String location, @RequestParam String author, @RequestParam String category) {
		maincontroller.returnUserFromCurrentSession(model);
			HappyUser user = happyUserService.findUserByName(author);

			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
			LocalDateTime currentTime = LocalDateTime.now(); 
			String publicationDate = currentTime.format(formatter);
			
		 
		 if(maincontroller.currentUserObject(model).getRole().equals("ROLE_ADMIN")) {
				model.addAttribute("role",true);
			}else {
				model.addAttribute("role",false);
			}
		 
		 
			
			if(maincontroller.currentUserObject(model).getRole().equals("ROLE_ADMIN")) {
				Article newArticle = new Article(title, articleText, publicationDate, location, user, category);
				newArticle.setStatus(true);
				articleService.saveArticle(newArticle);
				categoryService.sentMessageAterAddingTheArticle(newArticle);
				requestController.populateInbox(model);
				return "article-confirmation";
				
			}else {
				
				System.out.println("--------------------Else for user -------------------------------------------------------------");
				String publicationDate1 = null;
				Article newArticle = new Article(title, articleText, publicationDate1, location, user, category);
				newArticle.setStatus(false);
				articleService.saveArticle(newArticle);
				requestController.sendArticleForApproval( maincontroller.currentUserObject(model),newArticle);
				//there shoud be notification to Admin
				return "article-confirmation";
			}
	

	
		
		
		//return "article-confirmation";
		
	}

	@GetMapping(value = "/goToEditArticle/{articleId}")
	public String goToEditArticle(ModelMap model, @PathVariable int articleId) {
		maincontroller.returnUserFromCurrentSession(model);
		Article article = articleService.findByArticleId(articleId);
		model.addAttribute("article", article);

		return "editArticle";

	}

	@PostMapping("/editArticle")
	public String editProduct(ModelMap model, @RequestParam String title, @RequestParam String articleText,
			@RequestParam String location, @RequestParam String author, @RequestParam String category, @RequestParam Integer articleId) {

		maincontroller.returnUserFromCurrentSession(model);
		Article article = articleService.findByArticleId(articleId);
		
		article.setTitle(title);
		article.setArticleText(articleText);
		article.setLocation(location);
		article.setCategory(category);
		
		articleService.saveArticle(article);
		model.addAttribute("article", article);
		
		return "article";

	}

	@GetMapping("/deleteArticle")
	public String deleteArticle (ModelMap model, @RequestParam Integer articleId) {
		maincontroller.returnUserFromCurrentSession(model);
		Article article = articleService.findByArticleId(articleId);
		articleService.deleteArticle(article.getArticleId());
		
		return "index";
	}
	
	
	

	  
	  
	  
	  
	  @PostMapping("/filtered")
		public String filterSearch(ModelMap model, @RequestParam String searchedPhrase,@RequestParam String category, @RequestParam String location, @RequestParam String author) {
		  		
		  System.out.println("--------------author " +   author);
		  
		  if(author.isEmpty() || author == null || author == "" || author.equals("no filter")) {
		  			
		  			
		  			
		  			author="No filter";
		  		}
			// check if there are mi nand max values typed, if yes then check if min <max

			if (searchedPhrase.isEmpty() || searchedPhrase == null || searchedPhrase =="") {
				model.addAttribute("errorMessage", "you should write the title");
				model.addAttribute("filters", new Filters());
				//model.addAttribute("searchedPhrase", searchedPhrase);
				maincontroller.returnUserFromCurrentSession(model);
				return "searching-page";
			}
					System.out.println("from jsp we have " + author);
			Filters filters = new Filters(category, location, author);
			System.out.println("from filter.author we have " + filters.getAuthor());
		
			List<Article> filteredArticlesOld = articleService.findPostedarticles(articleService.findArticlesByTitle(searchedPhrase));
			Set<Article> setArticles = new HashSet<>();
			setArticles.addAll(filteredArticlesOld);
			List<Article>filteredArticles = new ArrayList<>();
			filteredArticles.addAll(setArticles);
			System.out.println("set------------- should be 2" + setArticles);
			
					System.out.println("filtered articles ------------------------ should be 2 " + filteredArticles);
				
			filteredArticles = articleService.filterResults(filteredArticles, filters, model);
			
			System.out.println("filtered articles ------------------------ should be 4 " + filteredArticles);

			model.addAttribute("foundArticles", filteredArticles);
			model.addAttribute("searchedPhrase", searchedPhrase);
			model.addAttribute("filters", filters);
			maincontroller.returnUserFromCurrentSession(model);

			return "searching-page";
		}
	  
	  
	  
	  @GetMapping("/goToSearchingPage")
		public String toSearchingPage(ModelMap model, @RequestParam String articleTitle) {

			
			//error checks
			if (articleTitle.isEmpty()) {
				model.addAttribute("errorNothingToSearch", "Please type something to search");
				//List<Article> listOfTopProducts = showProductService.listTopSixProducts();
				//model.addAttribute("topProducts", listOfTopProducts.stream().limit(6).toList());
				maincontroller.returnUserFromCurrentSession(model);
				return "redirect:/";
			}

			// get products by name and subtract from them already booked on this termin
		  
			List<Article> foundArticlesOld = articleService.findPostedarticles(articleService.findArticlesByTitle(articleTitle));
			Set<Article> foundArticles = new HashSet<>();
			//List<Article> foundArticles = articleService.findArticlesByName(articleTitle);
				for(Article article: foundArticlesOld) {
					foundArticles.add(article);
					System.out.println(article.getTitle());
				}
				
				
				System.out.println("-------------------- set ");
				
				for(Article article: foundArticles) {
					System.out.println(article.getTitle());
				}
			model.addAttribute("foundArticles", foundArticles);
			model.addAttribute("searchedPhrase", articleTitle);
			model.addAttribute("filters", new Filters());
			maincontroller.returnUserFromCurrentSession(model);

			return "searching-page";
		}
	  
	  
	  
	  
	
	
	
	/*public void messageByFavouriteCategories(Article article) {
	   List<HappyUser> listOfUser = happyUserRepository.findAll();
	   
	   for(HappyUser user: listOfUser) {
		   List<Categories> categories = user.getCategories();
		   for(String category: categories) {
			   if(category.equals(article.getCategory())){
				   Message newMassage = newMessage();
				   messagerfepository.save();
			   }
		   }
	   }
	   
	}*/
	
	
	
	
	
}