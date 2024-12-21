package com.example.demo.controller;

import com.example.demo.model.Player;
import com.example.demo.model.Team;
import com.example.demo.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerControllerTest {

    @Mock
    private PlayerService playerService;

    @InjectMocks
    private PlayerController playerController;

    private Player player;
    private Team team;

    @BeforeEach
    void setup() {
        // Création d'une équipe
        team = new Team();
        team.setId(1L);
        team.setName("Team A");
        team.setCoach("Coach A");
        team.setCity("City A");

        // Création d'un joueur avec une équipe associée
        player = new Player();
        player.setId(1L);
        player.setName("Player 1");
        player.setPosition("Forward");
        player.setTeam(team);  // Associer l'équipe au joueur
    }

    @Test
    void testGetAllPlayers() {
        // Simuler la réponse du service avec une liste contenant un joueur
        when(playerService.getAllPlayers()).thenReturn(List.of(player));

        // Appel du contrôleur
        var players = playerController.getAllPlayers();

        // Vérifications
        assertEquals(1, players.size());
        assertEquals("Player 1", players.get(0).getName());
        assertEquals("Team A", players.get(0).getTeam().getName());  // Vérifier que l'équipe est bien associée
    }

    @Test
    void testCreatePlayer() {
        // Simuler la création d'un joueur
        when(playerService.savePlayer(any(Player.class))).thenReturn(player);

        // Appel du contrôleur
        var response = playerController.createPlayer(player);

        // Vérifications
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Player 1", response.getBody().getName());
        assertEquals("Team A", response.getBody().getTeam().getName());  // Vérifier que l'équipe est bien associée
    }

    @Test
    void testUpdatePlayer() {
        // Simuler la mise à jour d'un joueur
        when(playerService.savePlayer(any(Player.class))).thenReturn(player);

        // Appel du contrôleur
        var response = playerController.updatePlayer(1L, player);

        // Vérifications
        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Player 1", response.getBody().getName());
        assertEquals("Team A", response.getBody().getTeam().getName());  // Vérifier l'équipe après mise à jour
    }

    @Test
    void testDeletePlayer() {
        // Simuler la suppression d'un joueur
        doNothing().when(playerService).deletePlayer(1L);

        // Appel du contrôleur
        var response = playerController.deletePlayer(1L);

        // Vérifications
        assertEquals(204, response.getStatusCodeValue());
        verify(playerService, times(1)).deletePlayer(1L);
    }
}
