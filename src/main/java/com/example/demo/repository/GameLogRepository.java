package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import com.example.demo.domain.GameLog;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface GameLogRepository extends JpaRepository<GameLog, UUID> {
}
