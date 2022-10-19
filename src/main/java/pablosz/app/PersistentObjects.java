package pablosz.app;

import org.apache.catalina.SessionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


import javax.persistence.EntityManager;
import javax.persistence.Query;


@Repository
public class PersistentObjects implements PersistentObjectsInterface {

    @Autowired
    private EntityManager em;

    @Override
    public void createSession(long key, long timeout) {

        Sesion sesion = new Sesion();
        sesion.setKey(key);
        sesion.setTimeout(timeout);
        sesion.setCreacion(java.time.LocalTime.now());

        em.persist(sesion);


    }

    @Override
    @Transactional
    public void store(long key, Object obj) {

        objeto.setSesion(key);
        em.persist(obj);

    }

    @Override
    public <T> T load(long key, Class<?> clazz) {


        String hql = "FROM " + clazz.toString() + "WHERE sesion = " +key;
        Query q = em.createQuery(hql);
        Class<?> obj = (Class<?>) q.getSingleResult();

        return (T) obj;
    }

    @Override
    public void remove(long key, Class<?> clazz) {

    }

    @Override
    public void destroySession(long key) {

    }

    @Override
    public void addListener(SessionListener lst) {

    }

    @Override
    public void removeListener(SessionListener lst) {

    }
}
