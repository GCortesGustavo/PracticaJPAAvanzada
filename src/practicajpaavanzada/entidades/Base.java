package practicajpaavanzada.entidades;

import java.io.Serializable;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * Clase base abstracta. Al ser @MappedSuperclass NO genera una tabla propia:
 * sus atributos (id y eliminado) se agregan a la tabla de cada entidad hija.
 */
@MappedSuperclass
public abstract class Base implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean eliminado = false;

    /** Baja logica: no borra el registro, solo lo marca como eliminado. */
    public void eliminar() {
        this.eliminado = true;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Boolean getEliminado() {
        return eliminado;
    }

    public void setEliminado(Boolean eliminado) {
        this.eliminado = eliminado;
    }
}
