package com.itmo.kotiki.service.implement;

import com.itmo.kotiki.dao.CatDAO;
import com.itmo.kotiki.dao.CatDAOImpl;
import com.itmo.kotiki.dao.HumanDAO;
import com.itmo.kotiki.dao.HumanDAOImpl;
import com.itmo.kotiki.entity.CatsEntity;
import com.itmo.kotiki.entity.HumansEntity;
import com.itmo.kotiki.service.interfaces.CatService;

import com.itmo.kotiki.entity.Color;

import java.sql.Date;
import java.util.List;

public class CatServiceImpl implements CatService {
    private CatDAO catDAO = new CatDAOImpl();
    @Override
    public void saveCat(String nameOfCat, Date birthday, Color color, String breed) {
        var cat = new CatsEntity(nameOfCat, birthday, color, breed);
        catDAO.save(cat);
    }

    @Override
    public void deleteCat(int idOfCat) {
        var cat = catDAO.findById(idOfCat);
        catDAO.delete(cat);
    }

    @Override
    public String getName(int idOfCat) {
        var cat = catDAO.findById(idOfCat);
        return cat.getName();
    }

    @Override
    public Color getColor(int idOfCat) {
        var cat = catDAO.findById(idOfCat);
        return Color.valueOf(cat.getColor());
    }

    @Override
    public Date getBirthday(int idOfCat) {
        var cat = catDAO.findById(idOfCat);
        return cat.getBirthday();
    }

    @Override
    public String getBreed(int idOfCat) {
        var cat = catDAO.findById(idOfCat);
        return cat.getBreed();
    }

    @Override
    public HumansEntity getHuman(int idOfCat) {
        var cat = catDAO.findById(idOfCat);
        return cat.getHumanByHumansId();
    }

    @Override
    public List<CatsEntity> getAllCats() {
        return catDAO.getAll();
    }

    @Override
    public void setName(int idOfCat, String name) {
        var cat = catDAO.findById(idOfCat);
        cat.setName(name);
        catDAO.update(cat);
    }

    @Override
    public void setBirthday(int idOfCat, Date birthday) {
        var cat = catDAO.findById(idOfCat);
        cat.setBirthday(birthday);
        catDAO.update(cat);
    }

    @Override
    public void setColor(int idOfCat, Color color) {
        var cat = catDAO.findById(idOfCat);
        cat.setColor(color.getCode());
        catDAO.update(cat);
    }

    @Override
    public void setBreed(int idOfCat, String breed) {
        var cat = catDAO.findById(idOfCat);
        cat.setBreed(breed);
        catDAO.update(cat);
    }

    @Override
    public void setHuman(int idOfCat, int idOfHuman) {
        var cat = catDAO.findById(idOfCat);
        var humanDAO = new HumanDAOImpl();
        var human = humanDAO.findById(idOfHuman);
        cat.setHumanByHumansId(human);
    }

    @Override
    public void changeCatDAO(CatDAOImpl catDAO) {
        this.catDAO = catDAO;
    }
}
