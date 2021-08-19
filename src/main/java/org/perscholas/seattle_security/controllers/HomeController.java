package org.perscholas.seattle_security.controllers;

import java.util.Collection;

import javax.validation.Valid;

import org.perscholas.seattle_security.models.User;
import org.perscholas.seattle_security.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
/**
 * 
 * @author Charlie de la Rosa
 *
 */
@Controller
public class HomeController {
	
	private UserService userService;
	
	@Autowired
	public HomeController(UserService userService) {
		this.userService = userService;
	}
	
	@GetMapping("/")
	public String showHomePage(Model model) {
		// Get current user
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		// Print current user granted authorities
		Collection<? extends GrantedAuthority> authorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();
		System.out.println(authorities);
		
		model.addAttribute("currentUser", ((UserDetails)principal).getUsername());
		return "home_page";
	}
	
	@GetMapping("/login")
	public String showLoginPage() {
		return "login";
	}
	
	@GetMapping("/about")
	public String showAboutPage() {
		return "about";
	}
	
	@GetMapping("/accessDenied")
	public String showAccessDeniedPage() {
		return "access_denied";
	}
	
	@GetMapping("/admin")
	public String showAdminPage() {
		return "admin";
	}
	
	@GetMapping("/logoutSuccess")
	public String showLogoutPage() {
		return "logout";
	}
	
	@GetMapping("/register")
	public String showRegisterPage(Model model) {
		model.addAttribute("newUser", new User());
		return "register";
	}
	
	@PostMapping("/registerNewUser")
	public String registerNewUser(@Valid @ModelAttribute("newUser") User newUser, 
			BindingResult result) {
		if (result.hasErrors()) {
			return "register";
		}
		User user = userService.save(newUser);
		System.out.println(user.getUserId());
		return "redirect:/login";
	}
}