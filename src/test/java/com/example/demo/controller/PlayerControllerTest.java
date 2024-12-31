package com.example.demo.controller;

import com.example.demo.model.Player;
import com.example.demo.service.PlayerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PlayerControllerTest {

    private MockMvc mockMvc;
    private PlayerService playerService;
    private PlayerController playerController;

    @BeforeEach
    public void setup() {
        playerService = Mockito.mock(PlayerService.class);
        playerController = new PlayerController(playerService);
        mockMvc = MockMvcBuilders.standaloneSetup(playerController).build();
    }

    @Test
    public void testGetAllPlayers() throws Exception {
        Player player1 = new Player();
        player1.setId(1L);
        player1.setName("John Doe");
        player1.setPosition("Defender");

        Player player2 = new Player();
        player2.setId(2L);
        player2.setName("Jane Smith");
        player2.setPosition("Midfielder");

        when(playerService.getAllPlayers()).thenReturn(Arrays.asList(player1, player2));

        mockMvc.perform(get("/api/players"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"))
                .andExpect(jsonPath("$[1].name").value("Jane Smith"));

        verify(playerService, times(1)).getAllPlayers();
    }

    @Test
    public void testCreatePlayer() throws Exception {
        Player player = new Player();
        player.setId(1L);
        player.setName("John Doe");
        player.setPosition("Defender");

        when(playerService.savePlayer(Mockito.any(Player.class))).thenReturn(player);

        mockMvc.perform(post("/api/players")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Doe\",\"position\":\"Defender\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));

        verify(playerService, times(1)).savePlayer(Mockito.any(Player.class));
    }

    @Test
    public void testUpdatePlayer() throws Exception {
        Player updatedPlayer = new Player();
        updatedPlayer.setId(1L);
        updatedPlayer.setName("John Updated");
        updatedPlayer.setPosition("Forward");

        when(playerService.updatePlayer(eq(1L), Mockito.any(Player.class))).thenReturn(updatedPlayer);

        mockMvc.perform(put("/api/players/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"John Updated\",\"position\":\"Forward\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Updated"));

        verify(playerService, times(1)).updatePlayer(eq(1L), Mockito.any(Player.class));
    }

    @Test
    public void testDeletePlayer() throws Exception {
        doNothing().when(playerService).deletePlayer(1L);

        mockMvc.perform(delete("/api/players/1"))
                .andExpect(status().isNoContent());

        verify(playerService, times(1)).deletePlayer(1L);
    }
}
