package net.monirul.springboot.services;

import net.monirul.springboot.controllers.dto.MovieDto;
import net.monirul.springboot.models.Movie;

import java.util.List;
import java.util.Optional;

public interface MovieService {
    Movie save(MovieDto movieDto);
    List<Movie> getAllMovies();
    Movie findMovieById(Long Id) throws Exception;
    Movie findMovieByApiId(String Id);
    boolean existsMovieByApiId(String apiId);
}
