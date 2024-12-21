package com.example.demo.service;

import com.example.demo.model.Player;
import com.example.demo.model.Team;
import com.example.demo.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PlayerServiceTest {

    @Mock
    private PlayerRepository playerRepository;

    @InjectMocks
    private PlayerService playerService;

    public PlayerServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllPlayers() {
        Player player1 = new Player();
        player1.setName("Player A");
        Player player2 = new Player();
        player2.setName("Player B");

        when(playerRepository.findAll()).thenReturn(Arrays.asList(player1, player2));

        var players = playerService.getAllPlayers();

        assertEquals(2, players.size());
        assertEquals("Player A", players.get(0).getName());
    }

    @Test
    void testSavePlayer() {
        Player player = new Player();
        player.setName("Player A");

        when(playerRepository.save(any(Player.class))).thenReturn(player);

        Player savedPlayer = playerService.savePlayer(player);

        assertNotNull(savedPlayer);
        assertEquals("Player A", savedPlayer.getName());
    }

    @Test
    void testDeletePlayer() {
        doNothing().when(playerRepository).deleteById(1L);

        playerService.deletePlayer(1L);

        verify(playerRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetPlayerById() {
        Player player = new Player();
        player.setId(1L);
        player.setName("Player A");

        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));

        Player foundPlayer = playerService.getPlayerById(1L);

        assertNotNull(foundPlayer);
        assertEquals(1L, foundPlayer.getId());
    }
}
