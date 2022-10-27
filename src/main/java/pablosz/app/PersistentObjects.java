package pablosz.app;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    @Transactional
    public void createSession(long key, long timeout) {

        Sesion sesion = new Sesion();
        sesion.setKey(key);
        sesion.setTimeout(timeout);
        sesion.setCreacion(java.time.LocalTime.now());

        em.persist(sesion);

    }

    @Override
    @Transactional
    public void store(long key, Object obj) throws Exception {

       Query sesion = em.createQuery("FROM Sesion WHERE key = " +key);
       try {
           Sesion s = (Sesion) sesion.getSingleResult();
       }catch(Exception e){
           throw new Exception("No existe la sesion");
      }

        Persistidor persistidor = new Persistidor();
        persistidor.setSesion(key);
        persistidor.setClase(obj.getClass().getName());

        Gson gson = new GsonBuilder().addSerializationExclusionStrategy(new AnnotationsCustomGson()).create();
        String json = gson.toJson(obj);

        persistidor.setContenido(json);

        em.persist(persistidor);

    }

    @Override
    @Transactional
    public <T> T load(long key, Class<?> clazz) throws Exception {

        Persistidor persistidor;

        String hql = "FROM Persistidor WHERE sesion = " +key+ " AND clase = " + "\'" + clazz.getName() + "\'";
        Query q = em.createQuery(hql);
        try {
            persistidor = (Persistidor) q.getSingleResult();
        }catch(Exception e) {
            return null;
        }

        Gson gson = new Gson();

        return (T) gson.fromJson(persistidor.getContenido(), clazz);
    }

    @Override
    public void remove(long key, Class<?> clazz) {

        String hql = "DELETE FROM Persistidor WHERE sesion = " +key+ " AND clase = " + "\'" + clazz.getName() + "\'";
        Query q = em.createQuery(hql);
        q.executeUpdate();

    }

    @Override
    public void destroySession(long key) {
        String borrarSesion = "DELETE FROM Sesion WHERE key = " +key;
        Query s = em.createQuery(borrarSesion);
        s.executeUpdate();

        String borrarPersistidor = "DELETE FROM Persistidor WHERE sesion = " +key;
        Query p = em.createQuery(borrarPersistidor);
        p.executeUpdate();


    }

    @Override
    public void addListener(SessionListener lst) {

    }

    @Override
    public void removeListener(SessionListener lst) {

    }
}
