package com.example.demo.service;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.domain.Army;
import com.example.demo.enums.StrategyEnum;
import com.example.demo.repository.ArmyRepository;
import com.example.demo.resource.ArmyResource;

@Service
public class ArmyService {
    private final Logger log = LoggerFactory.getLogger(ArmyResource.class);

    @Autowired
    private ArmyRepository armyRepository;
    @Autowired
    GameLogService gameLogService;

    public List<Army> findAllArmies(UUID game) {
        return armyRepository.findAllArmiesByGameId(game); 
    }

    public List<Army> findAttackingArmies(UUID game, Integer attackId, Integer n) {
        Instant time = Instant.now();
        List<Army> armyList = armyRepository.findArmiesByAttackIdGreater(game, time, attackId, n);
        if(armyList.size() < n) {
            Integer k = n - armyList.size();
            armyList.addAll(armyRepository.findArmiesByAttackIdLess(game, time, attackId, k));
        } 
        return armyList;
    }

    public Army findArmyById(UUID id) {
        return armyRepository.findById(id).orElse(null);
    }

    public boolean attacWithArmy(UUID gameId, UUID armyId, StrategyEnum strategy, Integer units) {
        Integer attackChances = (int)(Math.random() * 101);
        boolean armyDestroyed = false;
        Army enemy = new Army();
        Integer damage = 0; 
        Army army = findArmyById(armyId);

        if(attackChances < units) {
            if(strategy == StrategyEnum.RANDOM) {
                enemy = armyRepository.findRandomArmy(gameId, armyId).get(0);
            } else if(strategy == StrategyEnum.STRONGEST) {
                enemy = armyRepository.findStrongestArmy(gameId, armyId).get(0);
            } else {
                enemy = armyRepository.findWeakestArmy(gameId, armyId).get(0);
            }
            
            if(units > 1) {
                damage = (int)(0.5 * units);
            } else {
                damage = 1;
            }
            Integer reducedUnits = enemy.getCurrentUnits() - damage;
            log.info(String.format("Army %s(%s) attacked enemy %s(%s)", army.getName() ,army.getId(), enemy.getName(), enemy.getId()));
            gameLogService.addLog(gameId, String.format("Army %s(%s) attacked enemy %s(%s)", army.getName() ,army.getId(), enemy.getName(), enemy.getId()));

            if(reducedUnits <= 0) {
                reducedUnits = 0;
                armyDestroyed = true;
                log.info(String.format("Enemy %s(%s) is destroyed", enemy.getName() ,enemy.getId()));
                gameLogService.addLog(gameId, String.format("Enemy %s(%s) is destroyed", enemy.getName() ,enemy.getId()));
            }
            armyRepository.reduceUnits(enemy.getId(), reducedUnits);
        } else {
            log.info(String.format("Army %S(%S) attack failed", army.getName() ,army.getId()));
            gameLogService.addLog(gameId, String.format("Army %S(%S) attack failed", army.getName() ,army.getId()));
        }

        army.setAvailableAttackDate(Instant.now().plusMillis(army.getCurrentUnits()));
        armyRepository.save(army);
        return armyDestroyed;
    }

    public void deleteArmy(UUID gameId) {
        armyRepository.deleteArmy(gameId);
    }

    public void findInitializedArmies(UUID gameId) {
        armyRepository.findInitializedArmies(gameId);
    }
}
