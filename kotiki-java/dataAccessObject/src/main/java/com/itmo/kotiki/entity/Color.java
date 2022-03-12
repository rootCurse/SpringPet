package com.itmo.kotiki.entity;

public enum Color {
    BLACK("BLACK"),
    WHITE("WHITE"),
    RED("RED"),
    ORANGE("ORANGE"),
    qwerty("qwerty");
    private String code;
    Color(String code){
        this.code = code;
    }
    public String getCode(){ return code;}
}
