package com.itmo.kotiki.entity;

public enum Role {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER");
    private String code;
    Role(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
