package com.fdmgroup.HappyNews.controller;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.fdmgroup.HappyNews.model.HappyUser;
import com.fdmgroup.HappyNews.repository.HappyUserRepository;
import com.fdmgroup.HappyNews.security.HappyUserDetails;
import com.fdmgroup.HappyNews.service.HappyUserDetailsService;
import com.fdmgroup.HappyNews.service.RegistrationService;
import com.fdmgroup.HappyNews.util.HappyUserValidator;

@Controller
public class AuthController {
			private final HappyUserRepository userRepository;
			private final HappyUserValidator happyUserValidator;
			private final  RegistrationService registrationService;
			private final HappyUserDetailsService userDetailsService;
			private final MainController mainController;
			
			
		@Autowired	
	public AuthController(HappyUserValidator happyUserValidator, HappyUserRepository userRepository,RegistrationService registrationService,HappyUserDetailsService userDetailsService,MainController mainController) {
				super();
				this.userRepository = userRepository;;
				this.happyUserValidator = happyUserValidator;
				this.registrationService = registrationService;
				this.userDetailsService = userDetailsService;
				this.mainController = mainController;
			}


	@GetMapping("/login")
	public String loginPage() {
		return "login";
	}
	
	@GetMapping("/login-error") 
	public String loginError(ModelMap model) {
		
		model.addAttribute("errorMessage", "Invalid username or password");
		
		return "login";
	}
	
	 
	@GetMapping("/registration")
	public String registrationPage(@ModelAttribute("happyUser") HappyUser happyUser) {
		return "registration";
	}
	
	@PostMapping("/registration")
	public String performRegistration(@ModelAttribute("happyUser") HappyUser happyUser,ModelMap model) {
	 
	 HappyUser userFromDatabase = userDetailsService.findUserByName(happyUser.getUsername());
	 if(userFromDatabase == null) {
		
	 }else {
		 if ((userFromDatabase.getUsername().equals(happyUser.getUsername()) || happyUser.getUsername().equals("anonymousUser"))) {
				model.addAttribute("errorMessage", "This user name already exists");
				return "registration";
			}
	 }
		 System.out.println(userFromDatabase.getUsername()+"==========================================");
		 HappyUser userFromDatabase1 = userDetailsService.findByUserEmail(happyUser.getEmail());
		 if(userFromDatabase1 == null) {
			 
		 }else {
			 if (userFromDatabase1.getEmail().equals(happyUser.getEmail()) ) {
				 
				 
					model.addAttribute("errorMessage", "User with this email already exists");
					return "registration";
				}  
		 } 
				

	 registrationService.register(happyUser);
	 
	 return "redirect:/login";
	 
	}
	
	@GetMapping("/admin")
	public String adminPage()
	{
		return "admin";
	}
	
	@GetMapping("/goChangePasswordPage")
	public String changePasswordPage(@ModelAttribute("happyUser") HappyUser happyUser, ModelMap model) {
			mainController.returnUserFromCurrentSession(model);
			return "changePassword";
		}
		
	@PostMapping("/change-password")
	public String changePassword(@RequestParam("currentPassword") String currentPassword, @RequestParam("newPassword") String newPassword,@RequestParam("confirmNewPassword") String confirmNewPassword,ModelMap model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		HappyUser user = userDetailsService.findUserByName(username);
		
		Integer hashedOldPassword = currentPassword.hashCode();
			String hashedOldPasswordString = hashedOldPassword.toString();
			UserDetails userDetails = userDetailsService.loadUserByEmailForPasswordChange(user.getEmail());
			HappyUserDetails happyUserDetails1 = (HappyUserDetails) userDetails;
			mainController.returnUserFromCurrentSession(model);
			
			if(!newPassword.equals(confirmNewPassword)) {
				model.addAttribute("errorMessage","New password and Cofirm password does not match");
				return "changePassword";
			}
			
			
			 if(currentPassword.equals(newPassword)) {
				 model.addAttribute("errorMessage","current password and new password are the same");
					return "changePassword";
			 }
			if(userDetails.getPassword().equals(hashedOldPasswordString)) {
			    happyUserDetails1.setPassword(newPassword);
			    userDetails = (UserDetails) happyUserDetails1;
			    userDetailsService.saveUserToDb(happyUserDetails1);
			    
			}else {
				model.addAttribute("errorMessage","Current password does not match");
				return "changePassword";
			}
			return "index";	
	}
	
	
	@GetMapping("/recover-password")
	public String recoverPasswordPage(/*@ModelAttribute("happyUser") HappyUser happyUser*/) {
			
			return "recoverPassword";
		}
		
	@PostMapping("/recover-password")
	public String recoverPassword(@RequestParam("email") String email,@RequestParam("petName") String petName,@RequestParam("newPassword") String newPassword, @RequestParam("confirmNewPassword") String confirmNewPassword, ModelMap model) {
			Optional<HappyUser> optional = userRepository.findByEmail(email);
			if(optional.isEmpty()) {
				model.addAttribute("errorMessage","User does'not exist");
				return "recoverPassword";
			}else {
				UserDetails userDetails = userDetailsService.loadUserByEmailForPasswordChange(email);
				HappyUserDetails happyUserDetails1 = (HappyUserDetails) userDetails;
				if(happyUserDetails1.getPetName().equals(petName)) {
					
					if(newPassword.equals(confirmNewPassword)) {
						happyUserDetails1.setPassword(newPassword);
					    userDetails = (UserDetails) happyUserDetails1;
					    userDetailsService.saveUserToDb(happyUserDetails1);
					}else {
						model.addAttribute("errorMessage", "Pssword doesn't match");
						return "recoverPassword";
					}
				    
				    
				}else {
					model.addAttribute("errorMessage","Pet Name does not match");
					return "recoverPassword";
				}
			}
			
			return "redirect:/login";	
	}
	
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
			HappyUser user = userDetailsService.findUserByName(username);
			model.addAttribute("user", user);
		}
	}
}
