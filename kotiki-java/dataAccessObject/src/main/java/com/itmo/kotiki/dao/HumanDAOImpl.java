package com.itmo.kotiki.dao;

import com.itmo.kotiki.entity.CatsEntity;
import com.itmo.kotiki.entity.HumansEntity;
import com.itmo.kotiki.util.SessionFactoryUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;
import java.util.Set;

public class HumanDAOImpl implements HumanDAO {
    @Override
    public HumansEntity findById(int humanId) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        HumansEntity humansEntity = session.get(HumansEntity.class, humanId);
        session.close();
        return humansEntity;
    }

    @Override
    public void save(HumansEntity human) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.save(human);
        tx1.commit();
        session.close();
    }

    @Override
    public void update(HumansEntity human) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.update(human);
        tx1.commit();
        session.close();
    }

    @Override
    public void delete(HumansEntity human) {
        Session session = SessionFactoryUtil.getSessionFactory().openSession();
        Transaction tx1 = session.beginTransaction();
        session.delete(human);
        tx1.commit();
        session.close();
    }

    @Override
    public Set<CatsEntity> findCatsOFHuman(int humanId) {
        return SessionFactoryUtil.getSessionFactory().openSession().get(HumansEntity.class, humanId).getCatsByHumanId();
    }

    @Override
    public List<HumansEntity> getAll() {
        List<HumansEntity> humans = (List<HumansEntity>) SessionFactoryUtil.getSessionFactory().openSession().createQuery("From HumansEntity ").list();
        return humans;
    }
}
