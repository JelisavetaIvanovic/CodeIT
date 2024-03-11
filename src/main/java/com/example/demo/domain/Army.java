package com.example.demo.domain;

import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;

import javax.persistence.ManyToOne;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.Table;
import com.example.demo.enums.StrategyEnum;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;

@Entity
@Table(name = "army")
public class Army implements Serializable {

    @Id
    @Column(name = "id")
    private UUID  id;

    @ManyToOne(fetch = FetchType.LAZY)
    private UUID game;

    @Column(name = "name")
    private String name;

    @Column(name = "strategy")
    @Enumerated(EnumType.STRING)
    private StrategyEnum strategy;

    @Column(name = "initial_units")
    private Integer initialUnits;

    @Column(name = "current_units")
    private Integer currentUnits;

    @Column(name = "available_attack_date")
    private Instant availableAttackDate;

    @Column(name = "attack_id")
    private Integer attackId;

    @Column(name = "preinitialized")
    private Boolean preinitialized;

    public Army() {}

    public Army(UUID game, String name, StrategyEnum strategy, Integer units) {
        this.id = UUID.randomUUID();
        this.game = game;
        this.name = name;
        this.strategy = strategy;
        this.initialUnits = units;
        this.currentUnits = units;
        this.availableAttackDate = Instant.now();
        this.attackId = 0;
        this.preinitialized = true;
    }

    public Army(UUID id, UUID game, String name, StrategyEnum strategy, Integer initialUnits, Integer currentUnits, Instant availableAttackDate, Integer attackId, Boolean preinitialized) {
        this.id = id;
        this.game = game;
        this.name = name;
        this.strategy = strategy;
        this.initialUnits = initialUnits;
        this.currentUnits = currentUnits;
        this.availableAttackDate = availableAttackDate;
        this.attackId = attackId;
        this.preinitialized = preinitialized;

    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID armyID) {
        this.id = armyID;
    }

    public UUID getGame() {
        return game;
    }

    public void setGame(UUID game) {
        this.game = game;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public StrategyEnum getStrategy() {
        return strategy;
    }

    public void setStrategy(StrategyEnum strategy) {
        this.strategy = strategy;
    }

    public Integer getInitialUnits() {
        return initialUnits;
    }

    public void setInitialUnits(Integer initialUnits) {
        this.initialUnits = initialUnits;
    }

    public Integer getCurrentUnits() {
        return currentUnits;
    }

    public void setCurrentUnits(Integer currentUnits) {
        this.currentUnits = currentUnits;
    }

    public Instant getAvailableAttackDate() {
        return availableAttackDate;
    }

    public void setAvailableAttackDate(Instant availableAttackDate) {
        this.availableAttackDate = availableAttackDate;
    }

    public Integer getAttackId() {
        return attackId;
    }

    public void setAttackId(Integer attackId) {
        this.attackId = attackId;
    }

    public Boolean getPreinitialized() {
        return preinitialized;
    }

    public void setPreinitialized(Boolean preinitialized) {
        this.preinitialized = preinitialized;
    }

    @Override
    public String toString() {
        return "Army ["
                + (id != null ? "id=" + id + ", " : "")
                + (game != null ? "game=" + game + ", " : "")
                + (name != null ? "name=" + name + ", " : "")
                + (strategy != null ? "strategy=" + strategy + ", " : "")
                + (initialUnits != null ? "initialUnits=" + initialUnits : "")
                + (currentUnits != null ? "currentUnits=" + currentUnits : "")
                + (availableAttackDate != null ? "availableAttackDate=" + availableAttackDate : "")
                + (attackId != null ? "attackId=" + attackId : "")
                + (preinitialized != null ? "preinitialized=" + preinitialized : "")
                + "]";
    }
}
