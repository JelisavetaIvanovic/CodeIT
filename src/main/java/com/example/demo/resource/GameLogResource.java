package com.example.demo.resource;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.GameLogService;

@RestController
@RequestMapping("/api/game-log")
public class GameLogResource {
    @Autowired
    private GameLogService gameLogService;

    @GetMapping("/get-logs")
    public List<String> getLogs(@RequestParam UUID gameId) {
        return gameLogService.getLogs(gameId);
    }
}
