package com.fdmgroup.HappyNews.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.repository.HappyUserRepository;
import com.fdmgroup.HappyNews.service.ArticleService;
import com.fdmgroup.HappyNews.service.HappyUserDetailsService;

@Controller
public class HappyUserController {
	
	
	@Autowired
	private HappyUserRepository userRepository;
	@Autowired
	private HappyUserDetailsService userDetailsService;
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private MainController mainController;
	
	
	
	@GetMapping("/showProfile")
	public String goToShowProfile(ModelMap model) {

		mainController.returnUserFromCurrentSession(model);

		return "showProfile";
	}
		
	@GetMapping("/goEditProfilePage")
	public String goToEditProfilePage(ModelMap model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		HappyUser user = userDetailsService.findByUsername(username);
		model.addAttribute("user", user);
		
		mainController.returnUserFromCurrentSession(model);
		
		return "editProfile";
	}
	
	
	
	@PostMapping("/editUserProfile")
	public String editProfilePage(ModelMap model, @ModelAttribute("user") HappyUser user, @RequestParam String petName) {
		mainController.returnUserFromCurrentSession(model);
		HappyUser userFromDatabase = userDetailsService.findByUsername(user.getUsername());
		userFromDatabase.setUsername(user.getUsername());
		userFromDatabase.setEmail(user.getEmail());
		userFromDatabase.setPetName(user.getPetName());
		
		System.out.println(user.getPetName());
		
		userDetailsService.saveUser(userFromDatabase);
		return "showProfile";
	}
	
	@GetMapping(value ="/articles/{username}")
	public String goToUserArticles(ModelMap model, @PathVariable String username) {
		mainController.returnUserFromCurrentSession(model);
		List<Article> listOfUserArticles = articleService.findArticleByUsername(model, username);
		//skopiuuuujjj
		model.addAttribute("username", username);
		model.addAttribute("listOfUserArticles", listOfUserArticles);
		System.out.println(listOfUserArticles );
		return "showUserArticles";
	}
	
	
	
}
