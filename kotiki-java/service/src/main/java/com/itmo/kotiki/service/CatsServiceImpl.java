package com.itmo.kotiki.service;

import com.itmo.kotiki.entity.CatsEntity;
import com.itmo.kotiki.entity.Color;
import com.itmo.kotiki.entity.HumansEntity;
import com.itmo.kotiki.repo.CatsRepository;
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
    public void save(CatsEntity cats){
        catsDAO.save(cats);
    }
    public CatsEntity findCat(int id) {
        return catsDAO.getReferenceById(id);
    }
    public String getName(int id){
        return catsDAO.getReferenceById(id).getName();
    }
    public Date getBirthday(int id) {
        return catsDAO.getReferenceById(id).getBirthday();
    }
    public String getBreed(int id){
        return catsDAO.getReferenceById(id).getBreed();
    }
    public Color getColor(int id){
        return catsDAO.getReferenceById(id).getColor();
    }
    public HumansEntity getHuman(int id){
        return catsDAO.getReferenceById(id).getHumanByHumansId();
    }
    public List<CatsEntity> getAll(){
        return catsDAO.findAll();
    }
    public void delete(int id){
        catsDAO.deleteById(id);
    }
}
