package com.example.demo.domain;

import com.example.demo.enums.StrategyEnum;
import com.fasterxml.jackson.annotation.JsonProperty;

public class ArmyDTO {
    @JsonProperty("name")
    private String name;
    @JsonProperty("strategy")
    private StrategyEnum strategy;
    @JsonProperty("units")
    private Integer units;

    public ArmyDTO() {}

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

    public Integer getUnits() {
        return units;
    }

    public void setUnits(Integer units) {
        this.units = units;
    }
}
