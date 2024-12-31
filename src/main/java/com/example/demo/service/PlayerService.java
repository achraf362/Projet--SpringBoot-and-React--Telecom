package com.example.demo.service;

import com.example.demo.model.Player;
import com.example.demo.model.Team;
import com.example.demo.repository.PlayerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PlayerService {
    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public List<Player> getAllPlayers() {
        return playerRepository.findAll();
    }

    public Player savePlayer(Player player) {
        return playerRepository.save(player);
    }

    public void deletePlayer(Long id) {
        playerRepository.deleteById(id);
    }
    public Player updatePlayer(Long id, Player updatedPlayer) {
        Player existingPlayer = getPlayerById(id);
        existingPlayer.setName(updatedPlayer.getName());
        existingPlayer.setPosition(updatedPlayer.getPosition());
        existingPlayer.setTeam(updatedPlayer.getTeam());
        return playerRepository.save(existingPlayer);
    }
    public Player getPlayerById(Long id) {
        Optional<Player> player = playerRepository.findById(id);
        return player.orElse(null); // Retourne null si l'équipe n'existe pas
    }
}