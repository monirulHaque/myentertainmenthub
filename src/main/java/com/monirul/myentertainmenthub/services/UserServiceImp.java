package com.monirul.myentertainmenthub.services;

import com.monirul.myentertainmenthub.controllers.dto.UserRegistrationDto;
import com.monirul.myentertainmenthub.models.Role;
import com.monirul.myentertainmenthub.models.User;
import com.monirul.myentertainmenthub.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImp implements UserService {

    private UserRepository userRepository;

    public UserServiceImp(UserRepository userRepository) {
        super();
        this.userRepository = userRepository;
    }

    @Override
    public User save(UserRegistrationDto registrationDto) {
        User user = new User(
                registrationDto.getName(),
                registrationDto.getUsername(),
                registrationDto.getEmail(),
                registrationDto.getPassword(),
                Arrays.asList(new Role("ROLE_USER")));
        return userRepository.save(user);
    }

}
