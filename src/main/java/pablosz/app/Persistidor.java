package pablosz.app;

import javax.persistence.Entity;
import javax.persistence.Id;

import com.fasterxml.jackson.core.JsonParser;


@Entity
public class Persistidor {
    @Id
    private int id;
    private long sesion;
    private int objeto;
    private String contenido; //json

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getSesion() {
        return sesion;
    }

    public void setSesion(long sesion) {
        this.sesion = sesion;
    }

    public int getObjeto() {
        return objeto;
    }

    public void setObjeto(int objeto) {
        this.objeto = objeto;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }
}
