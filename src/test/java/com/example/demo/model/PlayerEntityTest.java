package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PlayerEntityTest {

    @Test
    void testPlayerGettersAndSetters() {
        Player player = new Player();
        player.setId(1L);
        player.setName("Test Player");
        player.setPosition("Forward");

        Team team = new Team();
        team.setName("Team A");

        player.setTeam(team);

        assertEquals(1L, player.getId());
        assertEquals("Test Player", player.getName());
        assertEquals("Forward", player.getPosition());
        assertEquals("Team A", player.getTeam().getName());
    }
}
