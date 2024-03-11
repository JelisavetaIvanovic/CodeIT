package com.example.demo.enums;

public enum StatusEnum {
    NEW("NEW"),
    ACTIVE("ACTIVE"),
    ENDED("ENDED");

    private String status;

    private StatusEnum(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return this.status;
    }
}
