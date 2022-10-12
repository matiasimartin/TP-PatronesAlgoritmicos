package pablosz.app;

import org.apache.catalina.SessionListener;

public interface PersistentObjectsInterface {

    public void createSession(long key, long timeout);

    public void store(long key, Object obj);

    public <T> T load(long key, Class<?> clazz);

    public void remove(long key, Class<?> clazz);

    public void destroySession(long key);

    public void addListener(SessionListener lst);

    public void removeListener(SessionListener lst);

}
