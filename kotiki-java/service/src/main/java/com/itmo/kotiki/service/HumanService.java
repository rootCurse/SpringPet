package com.itmo.kotiki.service;

import com.itmo.kotiki.entity.*;
import com.itmo.kotiki.repo.HumanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;
import java.util.Set;

@Service("HumanService")
public class HumanService {
    private final HumanRepository humanDAO;
    @Autowired
    public HumanService(HumanRepository humanDAO) {
        this.humanDAO = humanDAO;
    }
    public String getName(int id){
        return humanDAO.getReferenceById(id).getName();
    }

    public Date getBirthday(int id){
        return humanDAO.getReferenceById(id).getBirthday();
    }

    public Set<CatsEntity> getCatsOfHuman(int id){
        return  humanDAO.getReferenceById(id).getCatsByHumanId();
    }

    public HumansEntity getHuman(int id){
        return humanDAO.getReferenceById(id);
    }

    public List<HumansEntity> getAllHumans(){
        return humanDAO.findAll();
    }

    public void save(String name, Long date){
        HumansEntity humansEntity = new HumansEntity(name, new Date(date));
        humanDAO.save(humansEntity);
    }
}
