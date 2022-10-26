package pablosz.app;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Persona {

    private String nombre;
    @Id
    private int dni;

    public Persona(String nombre, int dni){
        super();
        this.nombre = nombre;
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

}
