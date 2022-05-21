package com.itmo.kotiki.service;

import com.itmo.kotiki.dataAccessObject.entity.CatsEntity;
import com.itmo.kotiki.dataAccessObject.entity.Color;
import com.itmo.kotiki.dataAccessObject.entity.HumansEntity;
import com.itmo.kotiki.dataAccessObject.repository.CatsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service("CatsService")
public class CatsServiceImpl implements CatsService {
    private final CatsRepository catsDAO;

    @Autowired
    public CatsServiceImpl(CatsRepository catsDAO) {
        this.catsDAO = catsDAO;
    }

    public void save(CatsEntity cats) {
        catsDAO.save(cats);
    }

    public CatsEntity findCat(int id) {

        return catsDAO.getById(id);
    }

    public String getName(int id) {
        return catsDAO.getById(id).getName();
    }

    public String getName(int id, int humanId) {
        var cat = catsDAO.getById(id);
        if (cat.getHumanByHumansId().getHumanId() != humanId)
            return null;
        return cat.getName();
    }

    public Date getBirthday(int id) {
        return catsDAO.getById(id).getBirthday();
    }

    public Date getBirthday(int id, int humanId) {
        var cat = catsDAO.getById(id);
        if (cat.getHumanByHumansId().getHumanId() != humanId)
            return null;
        return catsDAO.getById(id).getBirthday();
    }

    public String getBreed(int id) {
        return catsDAO.getById(id).getBreed();
    }

    public String getBreed(int id, int humanId) {
        var cat = catsDAO.getById(id);
        if (cat.getHumanByHumansId().getHumanId() != humanId)
            return null;
        return catsDAO.getById(id).getBreed();
    }

    public Color getColor(int id) {
        return catsDAO.getById(id).getColor();
    }

    public Color getColor(int id, int humanId) {
        var cat = catsDAO.getById(id);
        if (cat.getHumanByHumansId().getHumanId() != humanId)
            return null;
        return catsDAO.getById(id).getColor();
    }

    public HumansEntity getHuman(int id) {
        return catsDAO.getById(id).getHumanByHumansId();
    }

    public List<CatsEntity> getAll() {
        return catsDAO.findAll();
    }

    public void delete(int id) {
        catsDAO.deleteById(id);
    }
}
