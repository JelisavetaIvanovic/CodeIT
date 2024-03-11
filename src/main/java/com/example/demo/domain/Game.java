package com.example.demo.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;
import javax.persistence.Column;
import javax.persistence.Table;
import com.example.demo.enums.StatusEnum;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
@Table(name = "game")
public class Game implements Serializable {
    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private StatusEnum status;

    @Column(name = "creation_date")
    private Instant creationDate;

    @Column(name = "number_of_armies")
    private Integer numberOfArmies;

    @Column(name = "last_attack_id")
    private Integer lastAttackId;
    
    public Game() {
        this.id = UUID.randomUUID();
        this.status = StatusEnum.NEW;
        this.creationDate = Instant.now();
        this.numberOfArmies = 0;
        this.lastAttackId = 0;
    }

    public Game(UUID id, StatusEnum status, Instant creationDate, Integer numberOfArmies, Integer lastAttackId) {
        this.id = id;
        this.status = status;
        this.creationDate = creationDate;
        this.numberOfArmies = numberOfArmies;
        this.lastAttackId = lastAttackId;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public StatusEnum getStatus() {
        return status;
    }

    public void setStatus(StatusEnum status) {
        this.status = status;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    public Integer getNumberOfArmies() {
        return numberOfArmies;
    }

    public void setNumberOfArmies(Integer numberOfArmies) {
        this.numberOfArmies = numberOfArmies;
    }

    public Integer getLastAttackId() {
        return lastAttackId;
    }

    public void setLastAttackId(Integer lastAttackId) {
        this.lastAttackId = lastAttackId;
    }

    @Override
    public String toString() {
        return "Game ["
                + (id != null ? "id=" + id + ", " : "")
                + (status != null ? "status=" + status + ", " : "")
                + (creationDate != null ? "creationDate=" + creationDate + ", " : "")
                + (numberOfArmies != null ? "numberOfArmies=" + numberOfArmies + ", " : "")
                + (lastAttackId != null ? "lastAttackId=" + lastAttackId : "") + "]";
    }
}
