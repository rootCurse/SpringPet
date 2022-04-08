package com.itmo.kotiki.entity;

import javax.persistence.*;
import java.sql.Date;
import java.util.Set;

@Entity
@Table(name = "humans", schema = "public", catalog = "kotiki")
public class HumansEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "human_id", nullable = false)
    private int humanId;
    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private String name;
    @Basic
    @Column(name = "birthday", nullable = false)
    private Date birthday;
    @OneToMany(mappedBy = "humanByHumansId")
    private Set<CatsEntity> catsByHumanId;

    public HumansEntity() {
    }

    public HumansEntity(String name, Date birthday) {
        this.name = name;
        this.birthday = birthday;
    }

    public int getHumanId() {
        return humanId;
    }

    public void setHumanId(int humanId) {
        this.humanId = humanId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public Set<CatsEntity> getCatsByHumanId() {
        return catsByHumanId;
    }

    public void setCatsByHumanId(Set<CatsEntity> catsByHumanId) {
        this.catsByHumanId = catsByHumanId;
    }
}

