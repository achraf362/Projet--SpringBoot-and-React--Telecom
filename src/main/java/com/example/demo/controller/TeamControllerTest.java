package com.example.demo.controller;

import com.example.demo.model.Team;
import com.example.demo.service.TeamService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeamControllerTest {

    @Mock
    private TeamService teamService;

    @InjectMocks
    private TeamController teamController;

    public TeamControllerTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTeams() {
        Team team1 = new Team();
        team1.setName("Team A");
        Team team2 = new Team();
        team2.setName("Team B");

        when(teamService.getAllTeams()).thenReturn(Arrays.asList(team1, team2));

        List<Team> teams = teamController.getAllTeams();

        assertEquals(2, teams.size());
        assertEquals("Team A", teams.get(0).getName());
    }

    @Test
    void testCreateTeam() {
        Team team = new Team();
        team.setName("Team A");

        when(teamService.saveTeam(any(Team.class))).thenReturn(team);

        ResponseEntity<Team> response = teamController.createTeam(team);

        assertEquals(200, response.getStatusCodeValue());
        assertEquals("Team A", response.getBody().getName());
    }

    @Test
    void testDeleteTeam() {
        doNothing().when(teamService).deleteTeam(1L);

        ResponseEntity<Void> response = teamController.deleteTeam(1L);

        assertEquals(204, response.getStatusCodeValue());
        verify(teamService, times(1)).deleteTeam(1L);
    }
}
