package practicajpaavanzada.entidades;

import java.time.LocalDate;
import javax.persistence.Entity;

@Entity
public class Consulta extends Base {

    private static final long serialVersionUID = 1L;

    private LocalDate fecha;
    private String diagnostico;

    public Consulta() {
    }

    public Consulta(LocalDate fecha, String diagnostico) {
        this.fecha = fecha;
        this.diagnostico = diagnostico;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    @Override
    public String toString() {
        return fecha + " - " + diagnostico;
    }
}
