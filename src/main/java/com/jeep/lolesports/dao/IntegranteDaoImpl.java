package com.jeep.lolesports.dao;

import com.jeep.lolesports.model.Integrante;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class IntegranteDaoImpl implements IntegranteDao {
    @Autowired
    private SessionFactory sessionFactory;

    @SuppressWarnings("unchecked")
    @Override
    public List<Integrante> findAll() {
        Session session = sessionFactory.openSession();

        List<Integrante> categories = session.createCriteria(Integrante.class).list();

        session.close();
        return categories;
    }

    @Override
    public Integrante findById(String id) {
        Session session = sessionFactory.openSession();
        Integrante integrante = session.get(Integrante.class, id);
        session.close();

        return integrante;
    }

    @Override
    public Integrante findByAccountId(int id) {
        Session session = sessionFactory.openSession();
        Criteria criteria = session.createCriteria(Integrante.class);
        Integrante integrante = (Integrante) criteria.add(Restrictions.eq("accountId", id))
                .uniqueResult();
        session.close();
        return integrante;
    }

    @Override
    public void save(Integrante integrante) {
        //Open session
        Session session = sessionFactory.openSession();

        //Begin transaction
        session.beginTransaction();

        //Save the category
        session.saveOrUpdate(integrante);

        //Commit the transaction
        session.getTransaction().commit();

        //Close the session
        session.close();
    }

    @Override
    public void delete(Integrante integrante) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.delete(integrante);
        session.getTransaction().commit();
        session.close();
    }
}
