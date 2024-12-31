package com.example.demo.service;

import com.example.demo.model.Player;
import com.example.demo.repository.PlayerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class PlayerServiceTest {

    private PlayerRepository playerRepository;
    private PlayerService playerService;

    @BeforeEach
    public void setup() {
        playerRepository = Mockito.mock(PlayerRepository.class);
        playerService = new PlayerService(playerRepository);
    }

    @Test
    public void testGetAllPlayers() {
        Player player1 = new Player();
        player1.setId(1L);
        player1.setName("John Doe");

        Player player2 = new Player();
        player2.setId(2L);
        player2.setName("Jane Smith");

        when(playerRepository.findAll()).thenReturn(Arrays.asList(player1, player2));

        var players = playerService.getAllPlayers();
        assertEquals(2, players.size());
        assertEquals("John Doe", players.get(0).getName());

        verify(playerRepository, times(1)).findAll();
    }

    @Test
    public void testSavePlayer() {
        Player player = new Player();
        player.setId(1L);
        player.setName("John Doe");

        when(playerRepository.save(player)).thenReturn(player);

        Player savedPlayer = playerService.savePlayer(player);
        assertNotNull(savedPlayer);
        assertEquals("John Doe", savedPlayer.getName());

        verify(playerRepository, times(1)).save(player);
    }

    @Test
    public void testDeletePlayer() {
        doNothing().when(playerRepository).deleteById(1L);

        playerService.deletePlayer(1L);

        verify(playerRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdatePlayer() {
        Player existingPlayer = new Player();
        existingPlayer.setId(1L);
        existingPlayer.setName("John Doe");
        existingPlayer.setPosition("Defender");

        Player updatedPlayer = new Player();
        updatedPlayer.setName("Updated Name");
        updatedPlayer.setPosition("Forward");

        when(playerRepository.findById(1L)).thenReturn(Optional.of(existingPlayer));
        when(playerRepository.save(existingPlayer)).thenReturn(existingPlayer);

        Player result = playerService.updatePlayer(1L, updatedPlayer);

        assertEquals("Updated Name", result.getName());
        assertEquals("Forward", result.getPosition());
        verify(playerRepository, times(1)).findById(1L);
        verify(playerRepository, times(1)).save(existingPlayer);
    }

    @Test
    public void testGetPlayerById() {
        Player player = new Player();
        player.setId(1L);
        player.setName("John Doe");

        when(playerRepository.findById(1L)).thenReturn(Optional.of(player));

        Player result = playerService.getPlayerById(1L);
        assertNotNull(result);
        assertEquals("John Doe", result.getName());

        verify(playerRepository, times(1)).findById(1L);
    }
}
