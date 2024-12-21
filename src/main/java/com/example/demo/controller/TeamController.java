package com.example.demo.controller;

import com.example.demo.model.Team;
import com.example.demo.service.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teams")
public class TeamController {
    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

    @GetMapping
    public List<Team> getAllTeams() {
        return teamService.getAllTeams();
    }

    @PostMapping
    public ResponseEntity<Team> createTeam(@RequestBody Team team) {
        return ResponseEntity.ok(teamService.saveTeam(team));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Team> updateTeam(@PathVariable Long id, @RequestBody Team updatedTeam) {
        Team existingTeam = teamService.getTeamById(id);
        if (existingTeam == null) {
            return ResponseEntity.notFound().build();
        }
        existingTeam.setName(updatedTeam.getName());
        existingTeam.setCoach(updatedTeam.getCoach());
        existingTeam.setCity(updatedTeam.getCity());
        return ResponseEntity.ok(teamService.saveTeam(existingTeam));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTeam(@PathVariable Long id) {
        teamService.deleteTeam(id);
        return ResponseEntity.noContent().build();
    }
}
