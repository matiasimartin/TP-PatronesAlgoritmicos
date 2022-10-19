package pablosz.app;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalTime;

@Entity
public class Sesion {
    @Id
    private long key;
    private long timeout;
    private LocalTime creacion;

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }

    public long getTimeout() {
        return timeout;
    }

    public void setTimeout(long timeout) {
        this.timeout = timeout;
    }

    public LocalTime getCreacion() {
        return creacion;
    }

    public void setCreacion(LocalTime creacion) {
        this.creacion = creacion;
    }
}
