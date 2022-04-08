package com.itmo.kotiki.entity;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "cats", schema = "public", catalog = "kotiki")
public class CatsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "cat_id", nullable = false)
    private int catId;
    @Basic
    @Column(name = "name", nullable = true, length = -1)
    private String name;
    @Basic
    @Column(name = "birthday", nullable = false)
    private Date birthday;
    @Enumerated(EnumType.STRING)
    @Column(name = "color", nullable = true, length = -1)
    private Color color;
    @Basic
    @Column(name = "breed", nullable = true, length = -1)
    private String breed;
    @ManyToOne
    @JoinColumn(name = "human_Id", referencedColumnName = "human_id")
    private HumansEntity humanByHumansId;

    public CatsEntity() {
    }

    public CatsEntity(String name, Date date, Color color, String breed) {
        this.name = name;
        this.birthday = date;
        this.color = color;
        this.breed = breed;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public HumansEntity getHumanByHumansId() {
        return humanByHumansId;
    }

    public void setHumanByHumansId(HumansEntity humanByHumansId) {
        this.humanByHumansId = humanByHumansId;
    }

    public int getCatId() {
        return catId;
    }

    public void setCatId(int catId) {
        this.catId = catId;
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

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
