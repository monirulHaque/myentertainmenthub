package net.monirul.springboot.controllers;

import net.monirul.springboot.models.User;
import net.monirul.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

	@Autowired
	private UserService userService;
	
	@GetMapping("/")
	public String home() {
		return "index";
	}

	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		Object details = auth.getDetails();
		Object principal = auth.getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		model.addAttribute("userdetails",username);
		return "dashboard";
	}
	@GetMapping("/mymovielist")
	public String movielist(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = userService.getUserByEmail(username);
		model.addAttribute("movies",user.getMovies());
		return "mymovielist";
	}
}