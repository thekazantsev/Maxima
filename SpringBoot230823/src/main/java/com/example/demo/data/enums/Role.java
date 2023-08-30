package com.example.demo.data.enums;

public enum Role {
    ADMIN("Администратор"),
    USER("Пользователь");

    private final String ruName;

    Role(String ruName) {
        this.ruName = ruName;
    }

    public String getRuName() {
        return ruName;
    }
}
