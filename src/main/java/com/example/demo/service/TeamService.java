package com.example.demo.service;

import com.example.demo.model.Team;
import com.example.demo.repository.TeamRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TeamService {
    private final TeamRepository teamRepository;

    public TeamService(TeamRepository teamRepository) {
        this.teamRepository = teamRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team saveTeam(Team team) {
        return teamRepository.save(team);
    }
    public Team updateTeam(Long id, Team updatedTeam) {
        Team existingTeam = getTeamById(id);
        existingTeam.setName(updatedTeam.getName());
        existingTeam.setCoach(updatedTeam.getCoach());
        existingTeam.setCity(updatedTeam.getCity());
        return teamRepository.save(existingTeam);
    }
    public void deleteTeam(Long id) {
        teamRepository.deleteById(id);
    }

    public Team getTeamById(Long id) {
        Optional<Team> team = teamRepository.findById(id);
        return team.orElse(null); // Retourne null si l'équipe n'existe pas
    }
}