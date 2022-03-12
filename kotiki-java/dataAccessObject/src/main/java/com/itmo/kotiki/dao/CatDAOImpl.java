package com.itmo.kotiki.dao;

import com.itmo.kotiki.entity.CatsEntity;
import com.itmo.kotiki.util.SessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class CatDAOImpl implements CatDAO{
    @Override
    public CatsEntity findById(int id) {
        return SessionFactoryUtil.getSessionFactory().openSession().get(CatsEntity.class, id);
    }

    @Override
    public void save(CatsEntity cat) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.save(cat);
        transaction.commit();
        session.close();
    }

    @Override
    public void update(CatsEntity cat) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.update(cat);
        transaction.commit();
        session.close();
    }

    @Override
    public void delete(CatsEntity cat) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        session.delete(cat);
        transaction.commit();
        session.close();
    }

    @Override
    public List<CatsEntity> getAll() {
        List<CatsEntity> cats = (List<CatsEntity>)  SessionFactoryUtil.getSessionFactory().openSession().createQuery("From CatsEntity ").list();
        return cats;
    }
}
