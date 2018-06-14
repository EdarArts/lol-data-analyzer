package com.jeep.lolesports.dao;

import com.jeep.lolesports.model.Administrador;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@SuppressWarnings("Duplicates")
@Repository
public class AdministradorDaoImpl implements AdministradorDao {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public Administrador findByUsername(String username) {
        Session session = sessionFactory.openSession();

        Criteria criteria = session.createCriteria(Administrador.class);
        Administrador administrador = (Administrador) criteria.add(Restrictions.eq("username", username))
                .uniqueResult();
        //User user = session.get(User.class, username);
        session.close();

        return administrador;
    }

    @Override
    public void save(Administrador administrador) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(administrador);
        session.getTransaction().commit();
        session.close();
    }
}
