package com.itmo.kotiki.service.implement;

import com.itmo.kotiki.dao.HumanDAO;
import com.itmo.kotiki.dao.HumanDAOImpl;
import com.itmo.kotiki.entity.CatsEntity;
import com.itmo.kotiki.entity.HumansEntity;
import com.itmo.kotiki.service.interfaces.HumanService;

import java.sql.Date;
import java.util.List;
import java.util.Set;

public class HumanServiceImpl implements HumanService {
    private HumanDAO humanDAO = new HumanDAOImpl();
    @Override
    public void saveHuman(String name, Date birthday) {
        var human = new HumansEntity(name, birthday);
        humanDAO.save(human);
    }

    @Override
    public void deleteHuman(int idOfHuman) {
        var human = humanDAO.findById(idOfHuman);
        humanDAO.delete(human);
    }

    @Override
    public List<HumansEntity> getAllHumans() {
        return humanDAO.getAll();
    }

    @Override
    public String getName(int idOfHuman) {
        var human = humanDAO.findById(idOfHuman);
        return human.getName();
    }

    @Override
    public Date getBirthday(int idOfHuman) {
        var human = humanDAO.findById(idOfHuman);
        return human.getBirthday();
    }

    @Override
    public Set<CatsEntity> getCatsOfHuman(int idOfHuman) {
        return humanDAO.findCatsOFHuman(idOfHuman);
    }

    @Override
    public void setName(int idOfHuman, String name) {
        var human = humanDAO.findById(idOfHuman);
        human.setName(name);
        humanDAO.update(human);
    }

    @Override
    public void setBirthday(int idOfHuman, Date birthday) {
        var human = humanDAO.findById(idOfHuman);
        human.setBirthday(birthday);
        humanDAO.update(human);
    }

    @Override
    public void changeHumanDAO(HumanDAOImpl humanDAO) {
        this.humanDAO = humanDAO;
    }
}
