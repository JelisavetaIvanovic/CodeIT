package com.example.demo.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.domain.Army;
import com.example.demo.domain.Game;
import com.example.demo.enums.StatusEnum;
import com.example.demo.repository.ArmyRepository;
import com.example.demo.repository.GameRepository;
import com.example.demo.resource.ArmyResource;

@Service
public class GameService {
    private final Logger log = LoggerFactory.getLogger(ArmyResource.class);

    @Autowired
    private GameRepository gameRepository;
    @Autowired
    private ArmyService armyService;
    @Autowired
    GameLogService gameLogService;
    @Autowired
    ArmyRepository armyRepository;

    public Game createGame() {
        Game game = new Game();
        gameRepository.save(game);
        return game;
    }

    public Game findGameById(UUID gameId) {
        return gameRepository.findById(gameId).orElse(null);
    }

    public List<Game> findAllGames() {
        return gameRepository.findAll();
    }

    public void runAttack(UUID gameId) {
        Game game = findGameById(gameId);

        if(game.getStatus() == StatusEnum.NEW) {
            if(game.getNumberOfArmies() < 5) {
                log.error("Not enough armies to start the game.");
                gameLogService.addLog(game.getId(), "Not enough armies to start the game.");
            } else {
                game.setStatus(StatusEnum.ACTIVE);
                List<Army> armyList = armyService.findAttackingArmies(gameId, game.getLastAttackId() + 1, 5);
                for (Army army : armyList) {
                    boolean armyDestroyed = armyService.attacWithArmy(gameId, army.getId(), army.getStrategy(), army.getCurrentUnits());
                    if(armyDestroyed == true) {
                        Integer numberOfArmies = game.getNumberOfArmies() - 1;
                        game.setNumberOfArmies(numberOfArmies);
                        if(numberOfArmies == 1) {
                            game.setStatus(StatusEnum.ENDED);
                        }
                    }
                }
                game.setLastAttackId(armyList.get(armyList.size() - 1).getAttackId());
                gameRepository.save(game);
            }
        } else if (game.getStatus() == StatusEnum.ACTIVE) {
            List<Army> armyList = armyService.findAttackingArmies(gameId, game.getLastAttackId() + 1, 5);
            for (Army army : armyList) {
                boolean armyDestroyed = armyService.attacWithArmy(gameId, army.getId(), army.getStrategy(), army.getCurrentUnits());
                if(armyDestroyed == true) {
                    Integer numberOfArmies = game.getNumberOfArmies() - 1;
                    game.setNumberOfArmies(numberOfArmies);
                    if(numberOfArmies == 1) {
                        game.setStatus(StatusEnum.ENDED);
                    }
                }
            }
            game.setLastAttackId(armyList.get(armyList.size() - 1).getAttackId());
            gameRepository.save(game);
        } else {
            log.error("The game has ended.");
            gameLogService.addLog(game.getId(), "The game has ended.");
        }
    }

    public void addArmy(UUID gameId, Army army) {
        Game game = findGameById(gameId);

        if(game.getStatus() == StatusEnum.ACTIVE) {
            armyRepository.incrementAttackIdForGame(game.getId(), game.getLastAttackId() + 1);
            army.setAttackId(game.getLastAttackId() + 1);
            game.setNumberOfArmies(game.getNumberOfArmies() + 1);
            army.setPreinitialized(false);

            log.info(String.format("Army %s has been added in the game with uuid %s.", army.getName(), game.getId()));
            gameLogService.addLog(game.getId(), String.format("Army %s has been added in the game with uuid %s.", army.getName(), game.getId()));

        } else if(game.getStatus() == StatusEnum.NEW) {
            army.setAttackId(game.getNumberOfArmies() + 1);
            game.setNumberOfArmies(game.getNumberOfArmies() + 1);
            army.setPreinitialized(true);

            log.info(String.format("Army %s has been added in the game with uuid %s.", army.getName(), game.getId()));
            gameLogService.addLog(game.getId(), String.format("Army %s has been added in the game with uuid %s.", army.getName(), game.getId()));

        } else {
            log.error("The game is over, can not add army.");
            gameLogService.addLog(game.getId(), "The game is over, can not add army.");
        }
        armyRepository.save(army);
        gameRepository.save(game);
    }

    public void resetGame(UUID gameId) {
        Game game = findGameById(gameId);

        armyService.deleteArmy(gameId);
        Integer numberOfArmies = armyRepository.selectNumberOfArmies(gameId);
        List<Army> initializedArmies = armyRepository.findInitializedArmies(gameId);

        for(Army army : initializedArmies) {
            army.setCurrentUnits(army.getInitialUnits());
            army.setAvailableAttackDate(Instant.now());
            armyRepository.save(army);
        }
        game.setNumberOfArmies(numberOfArmies);
        game.setLastAttackId(0);
        game.setStatus(StatusEnum.NEW);
        gameRepository.save(game);
    }
}
