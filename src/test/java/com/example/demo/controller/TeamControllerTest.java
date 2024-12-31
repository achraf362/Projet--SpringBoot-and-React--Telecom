package com.example.demo.controller;

import com.example.demo.model.Team;
import com.example.demo.service.TeamService;
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

public class TeamControllerTest {

    private MockMvc mockMvc;
    private TeamService teamService;
    private TeamController teamController;

    @BeforeEach
    public void setup() {
        teamService = Mockito.mock(TeamService.class);
        teamController = new TeamController(teamService);
        mockMvc = MockMvcBuilders.standaloneSetup(teamController).build();
    }

    @Test
    public void testGetAllTeams() throws Exception {
        Team team1 = new Team();
        team1.setId(1L);
        team1.setName("Team A");
        team1.setCoach("Coach A");
        team1.setCity("City A");

        Team team2 = new Team();
        team2.setId(2L);
        team2.setName("Team B");
        team2.setCoach("Coach B");
        team2.setCity("City B");

        when(teamService.getAllTeams()).thenReturn(Arrays.asList(team1, team2));

        mockMvc.perform(get("/api/teams"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("Team A"))
                .andExpect(jsonPath("$[1].name").value("Team B"));

        verify(teamService, times(1)).getAllTeams();
    }

    @Test
    public void testCreateTeam() throws Exception {
        Team team = new Team();
        team.setId(1L);
        team.setName("Team A");
        team.setCoach("Coach A");
        team.setCity("City A");

        when(teamService.saveTeam(Mockito.any(Team.class))).thenReturn(team);

        mockMvc.perform(post("/api/teams")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Team A\",\"coach\":\"Coach A\",\"city\":\"City A\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Team A"));

        verify(teamService, times(1)).saveTeam(Mockito.any(Team.class));
    }

    @Test
    public void testUpdateTeam() throws Exception {
        Team updatedTeam = new Team();
        updatedTeam.setId(1L);
        updatedTeam.setName("Team Updated");
        updatedTeam.setCoach("Coach Updated");
        updatedTeam.setCity("City Updated");

        when(teamService.updateTeam(eq(1L), Mockito.any(Team.class))).thenReturn(updatedTeam);

        mockMvc.perform(put("/api/teams/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Team Updated\",\"coach\":\"Coach Updated\",\"city\":\"City Updated\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Team Updated"));

        verify(teamService, times(1)).updateTeam(eq(1L), Mockito.any(Team.class));
    }

    @Test
    public void testDeleteTeam() throws Exception {
        doNothing().when(teamService).deleteTeam(1L);

        mockMvc.perform(delete("/api/teams/1"))
                .andExpect(status().isNoContent());

        verify(teamService, times(1)).deleteTeam(1L);
    }
}
