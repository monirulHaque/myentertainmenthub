package net.monirul.springboot.services;

import net.monirul.springboot.models.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import net.monirul.springboot.controllers.dto.UserRegistrationDto;

public interface UserService extends UserDetailsService{
	User save(UserRegistrationDto registrationDto);
}
