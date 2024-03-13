package com.example.demo.resource;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.demo.domain.Army;
import com.example.demo.domain.ArmyDTO;
import com.example.demo.domain.Game;
import com.example.demo.service.ArmyService;
import com.example.demo.service.GameLogService;
import com.example.demo.service.GameService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import org.slf4j.Logger;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RestController
@RequestMapping("/api/game")
public class GameResource {
    private final Logger log = LoggerFactory.getLogger(GameResource.class);

    @Autowired
    private GameService gameService;
    @Autowired
    private ArmyService armyService;
    @Autowired
    GameLogService gameLogService;

    @PostMapping("/create-game")
    public ResponseEntity<UUID> createGame() {
        Game createdGame = gameService.createGame();
        UUID gameId = createdGame.getId();
        log.info("Game has been created.");
        gameLogService.addLog(gameId, "Game has been created.");
        return new ResponseEntity<>(gameId, HttpStatus.CREATED);
    }

    @GetMapping("/list-games")
    public Map<String, List<String>> listGames() {
        List<Game> games = gameService.findAllGames(); 
        Map<String, List<String>> gameList = new HashMap<>();

        for (int i = 0; i < games.size(); i++) {
            Game game = games.get(i);

            List<Army> armies = armyService.findAllArmies(game.getId());

            List<String> armyDetailsList = new ArrayList<>();

            for (Army army : armies) {
                String armyDetails = String.format(
                        "%s: %s, %s/%s",
                        army.getId(),
                        army.getName(),
                        army.getCurrentUnits(),
                        army.getInitialUnits()
                );
                armyDetailsList.add(armyDetails);
            }

            gameList.put((game.getId().toString()), armyDetailsList);
        }
    
            return gameList;
    }

    @PostMapping("/add-army")
    public void addArmy(
            @RequestParam UUID gameId,
            @RequestBody ArmyDTO army
    ) {
        Army newArmy = new Army(gameId, army.getName(), army.getStrategy(), army.getUnits());

        gameService.addArmy(gameId, newArmy);
    }

    @PostMapping("/run-attack")
    public void runAttack(@RequestParam UUID gameId) {
        gameService.runAttack(gameId);
    }

    @DeleteMapping("/reset")
    public void resetGame(@RequestParam UUID gameId) {
        gameService.resetGame(gameId);
    }
}
