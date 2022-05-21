package com.itmo.kotiki.dataAccessObject.entity;

public enum ColorCat {
    BLACK("BLACK"),
    WHITE("WHITE"),
    RED("RED"),
    ORANGE("ORANGE");
    private final String code;

    ColorCat(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
