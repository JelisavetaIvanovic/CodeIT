package com.example.demo.resource;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.service.GameLogService;

@RestController
@RequestMapping("/api/game-log")
public class GameLogResource {
    @Autowired
    private GameLogService gameLogService;

    @PostMapping("/add-log")
    public void addLog(@RequestParam UUID gameId, String text) {
        gameLogService.addLog(gameId, text);
    }
}
