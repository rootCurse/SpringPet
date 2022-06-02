package com.itmo.kotiki.dataAccessObject.entity;

import javax.persistence.*;

@Entity
@Table(name = "users", schema = "public", catalog = "kotiki")
public class UsersEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "user_id", nullable = false)
    private int userId;
    @Basic
    @Column(name = "username", nullable = false, length = -1)
    private String username;
    @Basic
    @Column(name = "password", nullable = false, length = -1)
    private String password;
    @Basic
    @Column(name = "role", nullable = false, length = -1)
    private String role;
    @OneToOne
    @JoinColumn(name = "human_id", referencedColumnName = "human_id")
    private HumansEntity humanByHumanId;

    public UsersEntity() {
    }

    public UsersEntity(String login, String password, Role role, HumansEntity humanId) {
        this.username = login;
        this.password = password;
        this.role = role.getCode();
        this.humanByHumanId = humanId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getLogin() {
        return username;
    }

    public void setLogin(String login) {
        this.username = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public HumansEntity getHumansByHumanId() {
        return humanByHumanId;
    }

    public void setHumansByHumanId(HumansEntity humansByHumanId) {
        this.humanByHumanId = humansByHumanId;
    }
}

