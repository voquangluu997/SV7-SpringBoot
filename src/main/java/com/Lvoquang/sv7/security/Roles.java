package com.Lvoquang.sv7.security;

public enum Roles {
    ADMIN("ROLE_ADMIN"),
    USER("ROLE_USER");

    private String name;

    private Roles(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}