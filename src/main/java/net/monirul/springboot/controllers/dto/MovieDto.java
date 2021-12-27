package net.monirul.springboot.controllers.dto;

import java.util.Date;

public class MovieDto {
    private String id;
    private String name;
    private String image;
    private String language;
    private String overview;
    private String tmdbScore;
    private String releaseDate;

    public MovieDto(String id, String name, String image, String language, String overview, String tmdbScore, String releaseDate) {
        this.id = id;
        this.name = name;
        this.image = image;
        this.language = language;
        this.overview = overview;
        this.tmdbScore = tmdbScore;
        this.releaseDate = releaseDate;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getTmdbScore() {
        return tmdbScore;
    }

    public void setTmdbScore(String tmdbScore) {
        this.tmdbScore = tmdbScore;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
