package net.monirul.springboot.services;

import net.monirul.springboot.controllers.dto.SeriesDto;
import net.monirul.springboot.models.Series;

import java.util.List;

public interface SeriesService {
    Series save(SeriesDto seriesDto);
    List<Series> getAllSeries();
    Series findSeriesById(Long Id) throws Exception;
    Series findSeriesByApiId(String Id);
    boolean existsSeriesByApiId(String apiId);
}
