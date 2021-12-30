package net.monirul.springboot.services;

import net.monirul.springboot.models.Movie;
import net.monirul.springboot.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import net.monirul.springboot.controllers.dto.UserRegistrationDto;

import java.util.List;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
	List<User> getAllUsers();
	User updateUser(User user);
	User getUserByEmail(String email);
	User addMovieList(User user, Movie movie);
    void deleteMovie(User user, Movie movie);
}
