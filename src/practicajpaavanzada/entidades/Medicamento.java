package practicajpaavanzada.entidades;

import javax.persistence.Entity;

@Entity
public class Medicamento extends Base {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String droga;
    private int pesoEnGramos;

    public Medicamento() {
    }

    public Medicamento(String nombre, String droga, int pesoEnGramos) {
        this.nombre = nombre;
        this.droga = droga;
        this.pesoEnGramos = pesoEnGramos;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDroga() {
        return droga;
    }

    public void setDroga(String droga) {
        this.droga = droga;
    }

    public int getPesoEnGramos() {
        return pesoEnGramos;
    }

    public void setPesoEnGramos(int pesoEnGramos) {
        this.pesoEnGramos = pesoEnGramos;
    }

    @Override
    public String toString() {
        return nombre + " (droga: " + droga + ", " + pesoEnGramos + " g)";
    }
}
