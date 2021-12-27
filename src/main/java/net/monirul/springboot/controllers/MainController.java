package net.monirul.springboot.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {
	
	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object details = auth.getDetails();
		Object principal = auth.getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		model.addAttribute("userdetails",username);
		return "dashboard";
	}
}