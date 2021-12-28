package net.monirul.springboot.services;

import net.monirul.springboot.controllers.dto.MovieDto;
import net.monirul.springboot.models.Movie;
import net.monirul.springboot.repository.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    private MovieRepository movieRepository;

    public MovieServiceImpl(MovieRepository movieRepository) {
        super();
        this.movieRepository = movieRepository;
    }


    public Movie save(MovieDto movieDto) {
        Movie movie = new Movie(movieDto.getApiId(),
                movieDto.getName(),
                movieDto.getImage(),
                movieDto.getLanguage(),
                movieDto.getOverview(),
                movieDto.getTmdbScore(),
                movieDto.getReleaseDate());

        return movieRepository.save(movie);
    }

    @Override
    public Movie findMovieById(Long Id) throws Exception {
        return movieRepository.findById(Id).orElseThrow(() -> new Exception("Element not found!"));
    }

    @Override
    public List<Movie> getAllMovies() {
        return movieRepository.findAll();
    }

    @Override
    public Movie findMovieByApiId(String apiId) {
        return movieRepository.findByApiId(apiId);
    }

}
