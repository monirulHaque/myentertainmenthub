package net.monirul.springboot.services;

import net.monirul.springboot.controllers.dto.MovieDto;
import net.monirul.springboot.controllers.dto.UserRegistrationDto;
import net.monirul.springboot.models.Movie;
import net.monirul.springboot.models.Role;
import net.monirul.springboot.models.User;
import net.monirul.springboot.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

public interface MovieService {
    Movie save(MovieDto movieDto);
}
