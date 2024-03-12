package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.domain.Army;

import java.time.Instant;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface ArmyRepository extends JpaRepository<Army, UUID> {
    @Transactional
    @Modifying
    @Query("UPDATE Army a "
            + "SET a.attackId = a.attackId + 1 "
            + "WHERE a.game = :gameId "
            + "AND a.attackId >= :minAttackId")
        public void incrementAttackIdForGame(@Param("gameId") UUID gameId, @Param("minAttackId") Integer minAttackId);
    
    @Transactional
    @Modifying
    @Query("UPDATE Army a "
            + "SET a.currentUnits = :units "
            + "WHERE a.id = :id ")
        public void reduceUnits(@Param("id") UUID id, @Param("units") Integer units);
    
    @Query("select "
                + "new com.example.demo.domain.Army("
                + "army.id,"
                + "army.game,"
                + "army.name,"
                + "army.strategy,"
                + "army.initialUnits,"
                + "army.currentUnits,"
                + "army.availableAttackDate,"
                + "army.attackId,"
                + "army.preinitialized"
                + ") "
                + "from Army army "
                + "where army.game = :game")
        public List<Army> findAllArmiesByGameId(
                        @Param("game") UUID game);

        @Query("select "
                        + "new com.example.demo.domain.Army("
                        + "army.id,"
                        + "army.game,"
                        + "army.name,"
                        + "army.strategy,"
                        + "army.initialUnits,"
                        + "army.currentUnits,"
                        + "army.availableAttackDate,"
                        + "army.attackId,"
                        + "army.preinitialized"
                        + ") "
                        + "from Army army "
                        + "where army.game = :game "
                        + "and army.currentUnits > 0 "
                        + "and army.availableAttackDate < :time "
                        + "and army.attackId >= :attackId "
                        + "order by army.attackId "
                        + "limit :n")
        public List<Army> findArmiesByAttackIdGreater(
                        @Param("game") UUID game,
                        @Param("time") Instant time,
                        @Param("attackId") Integer attackId,
                        @Param("n") Integer n);

        @Query("select "
                        + "new com.example.demo.domain.Army("
                        + "army.id,"
                        + "army.game,"
                        + "army.name,"
                        + "army.strategy,"
                        + "army.initialUnits,"
                        + "army.currentUnits,"
                        + "army.availableAttackDate,"
                        + "army.attackId,"
                        + "army.preinitialized"
                        + ") "
                        + "from Army army "
                        + "where army.game = :game "
                        + "and army.currentUnits > 0 "
                        + "and army.availableAttackDate < :time "
                        + "and army.attackId < :attackId "
                        + "order by army.attackId "
                        + "limit :n")
        public List<Army> findArmiesByAttackIdLess(
                        @Param("game") UUID game,
                        @Param("time") Instant time,
                        @Param("attackId") Integer attackId,
                        @Param("n") Integer n);


        @Query("select "
                        + "new com.example.demo.domain.Army("
                        + "army.id,"
                        + "army.game,"
                        + "army.name,"
                        + "army.strategy,"
                        + "army.initialUnits,"
                        + "army.currentUnits,"
                        + "army.availableAttackDate,"
                        + "army.attackId,"
                        + "army.preinitialized"
                        + ") "
                        + "from Army army "
                        + "where army.game = :game "
                        + "and army.id != :id "
                        + "order by army.currentUnits desc, random() "
                        + "limit 1")
        public List<Army> findStrongestArmy(
                        @Param("game") UUID game,
                        @Param("id") UUID id);
        
        @Query("select "
                        + "new com.example.demo.domain.Army("
                        + "army.id,"
                        + "army.game,"
                        + "army.name,"
                        + "army.strategy,"
                        + "army.initialUnits,"
                        + "army.currentUnits,"
                        + "army.availableAttackDate,"
                        + "army.attackId," 
                        + "army.preinitialized"
                        + ") "
                        + "from Army army "
                        + "where army.game = :game "
                        + "and army.id != :id "
                        + "and army.currentUnits > 0 "
                        + "order by army.currentUnits asc, random() "
                        + "limit 1")
        public List<Army> findWeakestArmy(
                        @Param("game") UUID game,
                        @Param("id") UUID id);

        @Query("select "
                        + "new com.example.demo.domain.Army("
                        + "army.id,"
                        + "army.game,"
                        + "army.name,"
                        + "army.strategy,"
                        + "army.initialUnits,"
                        + "army.currentUnits,"
                        + "army.availableAttackDate,"
                        + "army.attackId,"
                        + "army.preinitialized"
                        + ") "
                        + "from Army army "
                        + "where army.game = :game "
                        + "and army.id != :id "
                        + "and army.currentUnits > 0 "
                        + "order by random() "
                        + "limit 1")
        public List<Army> findRandomArmy(
                        @Param("game") UUID game,
                        @Param("id") UUID id);

        @Transactional     
        @Modifying
        @Query("delete from Army army "
                        + "where army.game = :game "
                        + "and army.preinitialized = false")
        public void deleteArmy(@Param("game") UUID game);

        @Query("select count(army) "
                        + "from Army army "
                        + "where army.game = :game "
                        + "and army.preinitialized = true")
        public Integer selectNumberOfArmies(@Param("game") UUID game);

        @Query("select "
                        + "new com.example.demo.domain.Army("
                        + "army.id,"
                        + "army.game,"
                        + "army.name,"
                        + "army.strategy,"
                        + "army.initialUnits,"
                        + "army.currentUnits,"
                        + "army.availableAttackDate,"
                        + "army.attackId,"
                        + "army.preinitialized"
                        + ") "
                        + "from Army army "
                        + "where army.game = :game "
                        + "and army.preinitialized = true")
        public List<Army> findInitializedArmies(
                        @Param("game") UUID game);
}
