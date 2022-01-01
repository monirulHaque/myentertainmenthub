package net.monirul.springboot.models;

import javax.persistence.*;

@Entity
@Table(name="series", uniqueConstraints = @UniqueConstraint(columnNames = "apiId"))
public class Series {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private long Id;
    private String apiId;
    private String name;
    private String image;
    private String language;
    private String overview;
    private String tmdbScore;
    private String releaseDate;

    public Series() {

    }

    public Series(String apiId, String name, String image, String language, String overview, String tmdbScore, String releaseDate) {
        this.apiId = apiId;
        this.name = name;
        this.image = image;
        this.language = language;
        this.tmdbScore = tmdbScore;
        this.overview = overview;
        this.releaseDate = releaseDate;
    }

    public long getId() {
        return Id;
    }

    public void setId(long id) {
        Id = id;
    }

    public String getApiId() {
        return apiId;
    }

    public void setApiId(String apiId) {
        this.apiId = apiId;
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

    public String getTmdbScore() {
        return tmdbScore;
    }

    public void setTmdbScore(String tmdbScore) {
        this.tmdbScore = tmdbScore;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }
}
