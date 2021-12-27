package net.monirul.springboot.models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name =  "movie", uniqueConstraints = @UniqueConstraint(columnNames = "apiId"))
public class Movie {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    public String apiId;
    private String name;
    private String image;
    private double score;
    private Date releaseDate;

}
