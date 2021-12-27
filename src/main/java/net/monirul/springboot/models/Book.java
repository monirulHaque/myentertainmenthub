package net.monirul.springboot.models;

import javax.persistence.*;
import java.util.Date;


@Entity
@Table(name =  "book", uniqueConstraints = @UniqueConstraint(columnNames = "apiId"))
public class Book {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    private String apiId;
    private String name;
    private double score;
    private Date releaseDate;
    private String authorName;
}