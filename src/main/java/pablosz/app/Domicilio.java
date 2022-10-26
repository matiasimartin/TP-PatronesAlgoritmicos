package pablosz.app;

import pablosz.ann.NotPersistable;

public class Domicilio {

    private String direccion;
    @NotPersistable
    private String entreCalles;

    private String telefono;

    private boolean edificio;

    private String piso;

    private String departamento;

    public Domicilio (String direccion, String entreCalles, String telefono, boolean edificio, String piso, String departamento){
        super();
        this.direccion = direccion;
        this.entreCalles = entreCalles;
        this.telefono = telefono;
        this.edificio = edificio;
        this.piso = piso;
        this.departamento = departamento;
    }


    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getEntreCalles() {
        return entreCalles;
    }

    public void setEntreCalles(String entreCalles) {
        this.entreCalles = entreCalles;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public boolean isEdificio() {
        return edificio;
    }

    public void setEdificio(boolean edificio) {
        this.edificio = edificio;
    }

    public String getPiso() {
        return piso;
    }

    public void setPiso(String piso) {
        this.piso = piso;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }
}
