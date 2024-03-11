package com.example.demo.enums;

public enum StrategyEnum {
    RANDOM("RANDOM"),
    WEAKEST("WEAKEST"),
    STRONGEST("STRONGEST");

    private String strategy;

    private StrategyEnum(String strategy) {
        this.strategy = strategy;
    }

    @Override
    public String toString() {
        return this.strategy;
    }
}
