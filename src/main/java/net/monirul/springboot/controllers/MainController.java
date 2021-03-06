package net.monirul.springboot.controllers;

import net.monirul.springboot.models.User;
import net.monirul.springboot.services.MovieService;
import net.monirul.springboot.services.SeriesService;
import net.monirul.springboot.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class MainController {


	@Autowired
	private UserService userService;

	@Autowired
	private MovieService movieService;

	@Autowired
	private SeriesService seriesService;


	@GetMapping("/dashboard")
	public String dashboard(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = userService.getUserByEmail(username);
		model.addAttribute("userFirstName",user.getFirstName());
		model.addAttribute("userLastName",user.getLastName());
		model.addAttribute("userFullName", user.getFirstName() + " " + user.getLastName());
		model.addAttribute("userEmail", user.getEmail());
		long movieCount = user.getMovies().size();
		long seriesCount = user.getSeries().size();
		model.addAttribute("movieCount", movieCount);
		model.addAttribute("seriesCount",seriesCount);
		return "dashboard";
	}
	@GetMapping("/dashboard/edit")
	public String dashboardEdit(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		Object details = auth.getDetails();
		Object principal = auth.getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = userService.getUserByEmail(username);
		model.addAttribute("userFirstName",user.getFirstName());
		model.addAttribute("userLastName",user.getLastName());
		model.addAttribute("userEmail", user.getEmail());
		model.addAttribute("user", user);
		return "dashboardedit";
	}

	@PostMapping("/dashboard/edit")
	public String dashboardEditSave(@ModelAttribute("user") User user) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
//		Object details = auth.getDetails();
		Object principal = auth.getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User loggedUser = userService.getUserByEmail(username);
		loggedUser.setFirstName(user.getFirstName());
		loggedUser.setLastName(user.getLastName());
		loggedUser.setEmail(user.getEmail());
		userService.updateUser(loggedUser);
		return "redirect:/dashboard?success";
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
	@RequestMapping("/mymovielist/delete/{id}")
	public String deleteMovie(@PathVariable String id, Model model) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = userService.getUserByEmail(username);
		userService.deleteMovie(user, movieService.findMovieByApiId(id));
		return "redirect:/mymovielist?deleted";
	}

	@GetMapping("/myserieslist")
	public String serieslist(Model model) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = userService.getUserByEmail(username);
		model.addAttribute("seriesList",user.getSeries());
		return "myserieslist";
	}
	@RequestMapping("/myserieslist/delete/{id}")
	public String deleteSeries(@PathVariable String id, Model model) throws Exception {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		Object principal = auth.getPrincipal();
		String username = ((UserDetails)principal).getUsername();
		User user = userService.getUserByEmail(username);
		userService.deleteSeries(user, seriesService.findSeriesByApiId(id));
		return "redirect:/myserieslist?deleted";
	}

}