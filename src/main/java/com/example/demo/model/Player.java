package com.example.demo.model;

import jakarta.persistence.*;

@Entity
public class Player {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;         // Identifiant unique pour le joueur

    private String name;     // Nom du joueur
    private String position; // Poste du joueur (ex: "Attaquant", "Défenseur")

    @ManyToOne
    @JoinColumn(name = "team_id")
    private Team team;       // Relation avec l'équipe

    // Getters et Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public Team getTeam() {
        return team;
    }

    public void setTeam(Team team) {
        this.team = team;
    }
}
