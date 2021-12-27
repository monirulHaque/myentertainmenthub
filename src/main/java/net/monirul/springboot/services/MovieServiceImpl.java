package net.monirul.springboot.services;

import net.monirul.springboot.controllers.dto.MovieDto;
import net.monirul.springboot.models.Movie;
import net.monirul.springboot.repository.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MovieServiceImpl implements MovieService{

    private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        super();
        this.movieRepository = movieRepository;
    }


    public Movie save(MovieDto movieDto) {
        Movie movie = new Movie(movieDto.getId(),
                movieDto.getName(),
                movieDto.getImage(),
                movieDto.getLanguage(),
                movieDto.getOverview(),
                movieDto.getTmdbScore(),
                movieDto.getReleaseDate());

        return movieRepository.save(movie);
    }
}
