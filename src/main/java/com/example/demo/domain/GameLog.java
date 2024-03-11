package com.example.demo.domain;

import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.ManyToOne;
import java.io.Serializable;
import java.time.Instant;
import java.util.UUID;
import javax.persistence.Table;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
@Table(name = "game_log")
public class GameLog implements Serializable {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "text")
    private String text;

    @ManyToOne(fetch = FetchType.LAZY)
    private UUID game;

    @Column(name = "creation_date")
    private Instant creationDate;

    public GameLog() {}
    
    public GameLog(UUID game) {
        this.id = UUID.randomUUID();
        this.text = "";
        this.game = game;
        this.creationDate = Instant.now();
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID logId) {
        this.id = logId;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public UUID getGame() {
        return game;
    }

    public void setGame(UUID game) {
        this.game = game;
    }

    public Instant getCreationDate() {
        return creationDate;
    }

    public void setCreationDate(Instant creationDate) {
        this.creationDate = creationDate;
    }

    @Override
    public String toString() {
        return "GameLog ["
                + (id != null ? "id=" + id + ", " : "")
                + (text != null ? "text=" + text + ", " : "")
                + (game != null ? "game=" + game + ", " : "")
                + (creationDate != null ? "creationDate=" + creationDate + ", " : "")
                + "]";
    }
}


    