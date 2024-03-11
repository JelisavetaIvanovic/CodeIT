package com.example.demo.repository;

import org.springframework.stereotype.Repository;
import com.example.demo.domain.Game;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface GameRepository extends JpaRepository<Game, UUID> {
    
}
