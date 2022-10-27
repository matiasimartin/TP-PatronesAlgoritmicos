package pablosz.app;


import java.io.Serializable;

public class PersistidorId implements Serializable {
    private long sesion;

    private String clase;

    public PersistidorId() {}

    public PersistidorId(long sesion, String clase) {
        this.sesion = sesion;
        this.clase = clase;
    }

    // equals() and hashCode()
}