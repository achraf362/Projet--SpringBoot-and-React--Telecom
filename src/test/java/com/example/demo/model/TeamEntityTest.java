package com.example.demo.model;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TeamEntityTest {

    @Test
    void testTeamGettersAndSetters() {
        Team team = new Team();
        team.setId(1L);
        team.setName("Test Team");
        team.setCoach("Test Coach");
        team.setCity("Test City");

        assertEquals(1L, team.getId());
        assertEquals("Test Team", team.getName());
        assertEquals("Test Coach", team.getCoach());
        assertEquals("Test City", team.getCity());
    }
}
