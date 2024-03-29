package com.example.demo.service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.domain.GameLog;
import com.example.demo.repository.GameLogRepository;

@Service
public class GameLogService {
    @Autowired
    GameLogRepository gameLogRepository;

    public GameLog addLog(UUID gameId, String text) {
        GameLog log = new GameLog(gameId);
        log.setGame(gameId);
        log.setText(text);
        log.setCreationDate(Instant.now());

        return gameLogRepository.save(log);
    }

    public List<String> getLogs(UUID gameId) {
        List<GameLog> logList = gameLogRepository.findAllLogsByGameId(gameId);
        List<String> textLogs = new ArrayList<>();

        for (GameLog log : logList) {
            textLogs.add(String.format("%s: %s", log.getCreationDate(),log.getText()));
        }

        return textLogs;
    }
}
