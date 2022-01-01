package net.monirul.springboot.services;

import net.monirul.springboot.controllers.dto.SeriesDto;
import net.monirul.springboot.models.Movie;
import net.monirul.springboot.models.Series;
import net.monirul.springboot.repository.SeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SeriesServiceImpl implements SeriesService{

    @Autowired
    private SeriesRepository seriesRepository;

    @Override
    public Series save(SeriesDto seriesDto) {
        Series series = new Series(seriesDto.getApiId(),
                seriesDto.getName(),
                seriesDto.getImage(),
                seriesDto.getLanguage(),
                seriesDto.getOverview(),
                seriesDto.getTmdbScore(),
                seriesDto.getReleaseDate());

        return seriesRepository.save(series);
    }

    @Override
    public List<Series> getAllSeries() {
        return seriesRepository.findAll();
    }

    @Override
    public Series findSeriesById(Long Id) throws Exception {
        return seriesRepository.findById(Id).orElseThrow(() -> new Exception("Element not found!"));
    }

    @Override
    public Series findSeriesByApiId(String apiId) {
        return seriesRepository.findByApiId(apiId);
    }

    @Override
    public boolean existsSeriesByApiId(String apiId) {
        return seriesRepository.existsByApiId(apiId);
    }
}
