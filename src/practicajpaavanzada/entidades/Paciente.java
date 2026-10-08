package practicajpaavanzada.entidades;

import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Paciente extends Base {

    private static final long serialVersionUID = 1L;

    private String nombre;
    private String apellido;
    private int edad;
    private int dni;
    private String obraSocial;
    private LocalDate fechaNacimiento;
    private char sexo;

    // n..m con Medicamento (tabla intermedia paciente_medicamento)
    @ManyToMany
    @JoinTable(name = "paciente_medicamento",
            joinColumns = @JoinColumn(name = "paciente_id"),
            inverseJoinColumns = @JoinColumn(name = "medicamento_id"))
    private List<Medicamento> medicamentos = new ArrayList<>();

    // 1..m con Consulta (la FK paciente_id queda en la tabla Consulta)
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "paciente_id")
    private List<Consulta> consultas = new ArrayList<>();

    // 1..1 con HistoriaClinica (lado inverso: la FK esta en HistoriaClinica)
    @OneToOne(mappedBy = "paciente", cascade = CascadeType.ALL)
    private HistoriaClinica historiaClinica;

    public Paciente() {
    }

    public Paciente(String nombre, String apellido, int dni, String obraSocial,
            LocalDate fechaNacimiento, char sexo) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.obraSocial = obraSocial;
        this.fechaNacimiento = fechaNacimiento;
        this.sexo = sexo;
        // La edad se calcula a partir de la fecha de nacimiento
        this.edad = Period.between(fechaNacimiento, LocalDate.now()).getYears();
    }

    /** Asigna la historia clinica y mantiene sincronizados ambos lados de la relacion. */
    public void asignarHistoriaClinica(HistoriaClinica historia) {
        this.historiaClinica = historia;
        historia.setPaciente(this);
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public String getObraSocial() {
        return obraSocial;
    }

    public void setObraSocial(String obraSocial) {
        this.obraSocial = obraSocial;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public char getSexo() {
        return sexo;
    }

    public void setSexo(char sexo) {
        this.sexo = sexo;
    }

    public List<Medicamento> getMedicamentos() {
        return medicamentos;
    }

    public void setMedicamentos(List<Medicamento> medicamentos) {
        this.medicamentos = medicamentos;
    }

    public List<Consulta> getConsultas() {
        return consultas;
    }

    public void setConsultas(List<Consulta> consultas) {
        this.consultas = consultas;
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    @Override
    public String toString() {
        return nombre + " " + apellido + " (DNI " + dni + ", " + edad + " años, "
                + obraSocial + ")";
    }
}
