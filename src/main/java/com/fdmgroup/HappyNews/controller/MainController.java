package com.fdmgroup.HappyNews.controller;

import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;

import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.security.HappyUserDetails;
import com.fdmgroup.HappyNews.service.ArticleService;
import com.fdmgroup.HappyNews.service.CategoryService;
import com.fdmgroup.HappyNews.service.HappyUserDetailsService;

@Controller
public class MainController {

	@Autowired
	private final HappyUserDetailsService happyUserDetailsService;
	
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private CategoryService categoryService ;
	
	
	
	
	public MainController(HappyUserDetailsService happyUserDetailsService) {
		super();
		this.happyUserDetailsService = happyUserDetailsService;
	}

	/*
	 * @GetMapping("/login") public String loginPage() { return "login"; }
	 */
	
	
	

	@GetMapping("/showUserInfo")
	public String showUserInfo(ModelMap model) {

		returnUserFromCurrentSession(model);

		return "login";

	}

	@GetMapping(value= "/")
	public String getIndex(ModelMap model) {
		returnUserFromCurrentSession(model);
		
		List<Article> latestArticles = articleService.listLatestSixArticles();
	    model.addAttribute("latestArticles", latestArticles);
		
	    List<Article> topArticles = articleService.listTopSixArticles();
	    model.addAttribute("topArticles", topArticles);
	    String username = SecurityContextHolder.getContext().getAuthentication().getName();	
		System.out.println("Username: " + username);	
		if(username.equals("anonymousUser")) {
			model.addAttribute("loggedIn", false);
		} else {
			model.addAttribute("loggedIn", true);
			HappyUser user = happyUserDetailsService.findUserByName(username);
			model.addAttribute("user", user);
			Set<Article> recommendedArticles = categoryService.recommendedArticles(model);
		    model.addAttribute("articlesByUserCategories", recommendedArticles);
		}
	    
	   
		return "index";
	}
	
	//--------------- below: from footer -----------------
	
	@GetMapping("/toAboutHappyNews")
	public String toAboutShazar(ModelMap model) {
		returnUserFromCurrentSession(model);
		
		return "aboutHappyNews";
	}
	
	
	@GetMapping("/goToFAQ")
	public String toFAQ(ModelMap model) {
		returnUserFromCurrentSession(model);
		
		return "FAQ";
	}
	
	@GetMapping("/toContact")
	public String toContactUs(ModelMap model) {
		returnUserFromCurrentSession(model);
		
		return "contact";
	}
	
	@GetMapping("/toPrivacyPolicy")
	public String toPolicy(ModelMap model) {
		returnUserFromCurrentSession(model);
		
		return "privacy";
	}
	
	@GetMapping("/toTermsAndConditions")
	public String toTermsAndConditions(ModelMap model) {
		returnUserFromCurrentSession(model);
		
		return "termsCondition";
	}
	//--------------- end of footer -----------------
	
	public void returnUserFromCurrentSession(ModelMap model) {
		//Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		//HappyUserDetails happyUserDetails = (HappyUserDetails) authentication.getPrincipal();
		//return happyUserDetails.getHappyUser();
        String username = SecurityContextHolder.getContext().getAuthentication().getName();	
		System.out.println("Username: " + username);	
		if(username.equals("anonymousUser")) {
			model.addAttribute("loggedIn", false);
		} else {
			model.addAttribute("loggedIn", true);
			HappyUser user = happyUserDetailsService.findUserByName(username);
			model.addAttribute("user", user);
		}
	}
	
	public HappyUser currentUserObject(ModelMap model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();	
			HappyUser user = happyUserDetailsService.findUserByName(username);
			return user;
		
		
	}
	
}
