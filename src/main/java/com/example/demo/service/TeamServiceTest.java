package com.example.demo.service;

import com.example.demo.model.Team;
import com.example.demo.repository.TeamRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class TeamServiceTest {

    @Mock
    private TeamRepository teamRepository;

    @InjectMocks
    private TeamService teamService;

    public TeamServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllTeams() {
        Team team1 = new Team();
        team1.setName("Team A");
        Team team2 = new Team();
        team2.setName("Team B");

        when(teamRepository.findAll()).thenReturn(Arrays.asList(team1, team2));

        var teams = teamService.getAllTeams();

        assertEquals(2, teams.size());
        assertEquals("Team A", teams.get(0).getName());
    }

    @Test
    void testSaveTeam() {
        Team team = new Team();
        team.setName("Team A");

        when(teamRepository.save(any(Team.class))).thenReturn(team);

        Team savedTeam = teamService.saveTeam(team);

        assertNotNull(savedTeam);
        assertEquals("Team A", savedTeam.getName());
    }

    @Test
    void testDeleteTeam() {
        doNothing().when(teamRepository).deleteById(1L);

        teamService.deleteTeam(1L);

        verify(teamRepository, times(1)).deleteById(1L);
    }

    @Test
    void testGetTeamById() {
        Team team = new Team();
        team.setId(1L);
        team.setName("Team A");

        when(teamRepository.findById(1L)).thenReturn(Optional.of(team));

        Team foundTeam = teamService.getTeamById(1L);

        assertNotNull(foundTeam);
        assertEquals(1L, foundTeam.getId());
    }
}
