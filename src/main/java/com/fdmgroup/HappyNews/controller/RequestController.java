package com.fdmgroup.HappyNews.controller;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.HappyNews.model.Article;
import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.model.Message;
import com.fdmgroup.HappyNews.model.Request;
import com.fdmgroup.HappyNews.service.ArticleService;
import com.fdmgroup.HappyNews.service.CategoryService;
import com.fdmgroup.HappyNews.service.HappyUserDetailsService;
import com.fdmgroup.HappyNews.service.MessageService;
import com.fdmgroup.HappyNews.service.RequestService;

@Controller
@Component
public class RequestController {
	@Autowired
	private HappyUserDetailsService happyUserService;
	
	@Autowired
	private MainController mainController;
	@Autowired
	private RequestService requestService;
	@Autowired
	private ArticleService articleService;
	
	@Autowired
	private MessageService messageService;
	
	@Autowired
	private CategoryService categoryService;
	
	
	
	
		
	public RequestController(HappyUserDetailsService happyUserService, MainController mainController,
			RequestService requestService, ArticleService articleService) {
		super();
		this.happyUserService = happyUserService;
		this.mainController = mainController;
		this.requestService = requestService;
		this.articleService = articleService;
	}

	
	
	@GetMapping("/confirmRequest")
	public String confirmRequest(ModelMap model, @RequestParam int requestId) {
		Request requestFromDatabase = requestService.getRequestFromDatabase(requestId);
		
		Article articleFromDatabase = articleService.findByArticleId(requestFromDatabase.getArticle().getArticleId());
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"); 
		LocalDateTime currentTime = LocalDateTime.now(); 
		String publicationDate = currentTime.format(formatter);
		
		articleFromDatabase.setStatus(true);
		articleFromDatabase.setPublicationDate(publicationDate);
		
		String text = "Your article:  " + articleFromDatabase.getTitle() + " has been confirmed.";
		
		//Message message = new Message(bookingFromDatabase.getProduct().getOwner(), bookingFromDatabase.getBorrower(), text, false, messageController.formattedCurrentDateTime());
        //messageService.saveMessage(message);
	    // There shoud be notifications to user that the article is posted
        Message message = new Message(articleFromDatabase.getAuthor(), text, false,formattedCurrentDateTime());
		
		messageService.saveMessage(message);
		
		articleService.saveArticle(articleFromDatabase);
		requestService.deleteRequest(requestFromDatabase.getRequestId());
		categoryService.sentMessageAterAddingTheArticle(articleFromDatabase);
		
		populateInbox(model);
		model.addAttribute("message", "Request Approved");
		mainController.returnUserFromCurrentSession(model);
		
		//return "inbox";
		return"redirect:/goToInbox";
	}
	
	@GetMapping("/rejectRequest")
	public String rejectRequest(ModelMap model, @RequestParam int requestId) {
		
		Request requestFromDatabase = requestService.getRequestFromDatabase(requestId);
		
		Article articleFromDatabase = articleService.findByArticleId(requestFromDatabase.getArticle().getArticleId());
		
		String text = "Your request for article: " + articleFromDatabase.getTitle() + " has been rejected.";
		
		//Message message = new Message(bookingFromDatabase.getProduct().getOwner(), bookingFromDatabase.getBorrower(), text, false, messageController.formattedCurrentDateTime());
		//messageService.saveMessage(message);
		// There shoud be notifications to user that the article is rejected
		
         Message message = new Message(articleFromDatabase.getAuthor(), text, false,formattedCurrentDateTime());
		
		messageService.saveMessage(message);
		requestService.deleteRequest(requestFromDatabase.getRequestId());		
		articleService.deleteArticle(articleFromDatabase.getArticleId());
		mainController.returnUserFromCurrentSession(model);
		populateInbox(model);		
		model.addAttribute("message", "Request Rejected");
		
		
		return"redirect:/goToInbox";
	}
	
	public void populateInbox(ModelMap model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		System.out.println("--------------------"+ SecurityContextHolder.getContext().getAuthentication().getName()+" -----------------");
		
		HappyUser userFromDatabase = happyUserService.findUserByName(username);

List<Message> allReceivedMessages = messageService.listAllReceivedMessages(userFromDatabase);
		
		List<Message> allUserMessages = new ArrayList<>(); 
		
		for(Message message: allReceivedMessages) {
			if(message.getReceiver().getId()== userFromDatabase.getId()) {
				allUserMessages.add(message);
			}
		}
		List<Request> allRequests = requestService.listRequests();
		
		mainController.returnUserFromCurrentSession(model);
		model.addAttribute("allReceivedMessages", allUserMessages); 
		model.addAttribute("allRequests", allRequests);
	}
	
	public LocalDateTime formattedCurrentDateTime() {
		LocalDateTime currentTime = LocalDateTime.now();
		DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		String formattedCurrentTimeString = currentTime.format(dateTimeFormatter);
		
		return LocalDateTime.parse(formattedCurrentTimeString, dateTimeFormatter);
	}
	
	@GetMapping("/goToInbox")
	public String goToInbox(ModelMap model) {
		System.out.println("---------------------Go to inbox working------------------------------- ");
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		HappyUser userFromDatabase = happyUserService.findUserByName(username);

		List<Request> allRequests = requestService.listRequests();
		List<Message> allReceivedMessages = messageService.listAllReceivedMessages(userFromDatabase);
		
		List<Message> allUserMessages = new ArrayList<>(); 
		
		for(Message message: allReceivedMessages) {
			if(message.getReceiver().getId()== userFromDatabase.getId()) {
				allUserMessages.add(message);
			}
		}
		
		
		if(userFromDatabase.getRole().equals("ROLE_ADMIN")) {
			model.addAttribute("role",true);
		}else {
			model.addAttribute("role",false);
		}
		mainController.returnUserFromCurrentSession(model);
		model.addAttribute("allRequests", allRequests);
		model.addAttribute("allReceivedMessages", allUserMessages);
		
		
		return "inbox";
	}
	
	
	@GetMapping("/deleteMessage")
	public String deleteMessage(ModelMap model, @RequestParam int messageId) {
		messageService.deleteMessage(messageId);
		mainController.returnUserFromCurrentSession(model);
		populateInbox(model);
		model.addAttribute("message", "Request Rejected");
		
		return "inbox";
	}
			
	
	public void sendArticleForApproval(HappyUser user, Article article) {
		System.out.println("----------------------------------------Sent article for approval working--------------------- ");
		
		String text = "User: " + user.getUsername() + " want to post his article:  " + article.getTitle() ;
		
		Request request = new Request(user, text, false, formattedCurrentDateTime(), article);
		
		requestService.saveRequest(request);
		
	}
	
	
	
	/*@GetMapping("/goToInbox")
	public String goToInbox() {
		System.out.println("---------------------Go to inbox working------------------------------- ");
		//String username = SecurityContextHolder.getContext().getAuthentication().getName();
		
		//HappyUser userFromDatabase = happyUserService.findByUsername(username);

		//List<Request> allRequests = requestService.listRequests();
		
		//mainController.returnUserFromCurrentSession(model);
		
		//model.addAttribute("allRequests", allRequests);
		
		return "inbox";
	
}*/
}
