package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import com.example.demo.domain.GameLog;
import java.util.List;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

@Repository
public interface GameLogRepository extends JpaRepository<GameLog, UUID> {
    @Query("select "
                + "new com.example.demo.domain.GameLog("
                + "log.id as id,"
                + "log.game as game,"
                + "log.text as text,"
                + "log.creationDate as creationDate"
                + ") "
                + "from GameLog log "
                + "where log.game = :game "
                + "order by log.creationDate asc")
        public List<GameLog> findAllLogsByGameId(
                        @Param("game") UUID game);
}
