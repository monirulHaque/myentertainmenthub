package net.monirul.springboot.models;

import javax.persistence.*;


@Entity
@Table(name =  "game", uniqueConstraints = @UniqueConstraint(columnNames = "apiId"))
public class Game {
    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long id;
    public String apiId;
    private String name;
}