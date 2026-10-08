package practicajpaavanzada.entidades;

import practicajpaavanzada.entidades.Base;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class HistoriaClinica extends Base {

    private static final long serialVersionUID = 1L;

    private String descripcion;

    // Relacion 1..1 con Paciente (lado dueno: la FK paciente_id queda en esta tabla)
    @OneToOne
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;

    public HistoriaClinica() {
    }

    public HistoriaClinica(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    @Override
    public String toString() {
        return descripcion;
    }
}
