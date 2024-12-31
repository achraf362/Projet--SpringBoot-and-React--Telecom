package com.example.demo.service;

import com.example.demo.model.Team;
import com.example.demo.repository.TeamRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class TeamServiceTest {

    private TeamRepository teamRepository;
    private TeamService teamService;

    @BeforeEach
    public void setup() {
        teamRepository = Mockito.mock(TeamRepository.class);
        teamService = new TeamService(teamRepository);
    }

    @Test
    public void testGetAllTeams() {
        Team team1 = new Team();
        team1.setId(1L);
        team1.setName("Team A");

        Team team2 = new Team();
        team2.setId(2L);
        team2.setName("Team B");

        when(teamRepository.findAll()).thenReturn(Arrays.asList(team1, team2));

        var teams = teamService.getAllTeams();
        assertEquals(2, teams.size());
        assertEquals("Team A", teams.get(0).getName());

        verify(teamRepository, times(1)).findAll();
    }

    @Test
    public void testSaveTeam() {
        Team team = new Team();
        team.setId(1L);
        team.setName("Team A");

        when(teamRepository.save(team)).thenReturn(team);

        Team savedTeam = teamService.saveTeam(team);
        assertNotNull(savedTeam);
        assertEquals("Team A", savedTeam.getName());

        verify(teamRepository, times(1)).save(team);
    }

    @Test
    public void testDeleteTeam() {
        doNothing().when(teamRepository).deleteById(1L);

        teamService.deleteTeam(1L);

        verify(teamRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateTeam() {
        Team existingTeam = new Team();
        existingTeam.setId(1L);
        existingTeam.setName("Team A");
        existingTeam.setCoach("Coach A");

        Team updatedTeam = new Team();
        updatedTeam.setName("Updated Team");
        updatedTeam.setCoach("Updated Coach");

        when(teamRepository.findById(1L)).thenReturn(Optional.of(existingTeam));
        when(teamRepository.save(existingTeam)).thenReturn(existingTeam);

        Team result = teamService.updateTeam(1L, updatedTeam);

        assertEquals("Updated Team", result.getName());
        assertEquals("Updated Coach", result.getCoach());
        verify(teamRepository, times(1)).findById(1L);
        verify(teamRepository, times(1)).save(existingTeam);
    }

    @Test
    public void testGetTeamById() {
        Team team = new Team();
        team.setId(1L);
        team.setName("Team A");

        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));

        Team result = teamService.getTeamById(1L);
        assertNotNull(result);
        assertEquals("Team A", result.getName());

        verify(teamRepository, times(1)).findById(1L);
    }
}
