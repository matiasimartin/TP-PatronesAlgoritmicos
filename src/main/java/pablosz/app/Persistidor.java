package pablosz.app;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.core.JsonParser;


@Entity
public class Persistidor {
    @Id
    private long sesion;
    private String clase;
    private String contenido; //json

    public long getSesion() {
        return sesion;
    }

    public void setSesion(long sesion) {
        this.sesion = sesion;
    }

    public String getClase() {
        return clase;
    }

    public void setClase(String clase) {
        this.clase = clase;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
