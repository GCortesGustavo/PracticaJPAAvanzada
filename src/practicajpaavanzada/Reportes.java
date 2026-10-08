package practicajpaavanzada;

import java.time.LocalDate;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import practicajpaavanzada.entidades.Consulta;
import practicajpaavanzada.entidades.Consulta;
import practicajpaavanzada.entidades.Medicamento;
import practicajpaavanzada.entidades.Medicamento;
import practicajpaavanzada.entidades.Paciente;
import practicajpaavanzada.entidades.Paciente;

/** Consultas JPQL pedidas en la practica (puntos 3 a 10). */
public class Reportes {

    private final EntityManager em;

    public Reportes(EntityManager em) {
        this.em = em;
    }

    // 3. Pacientes mayores de 30 años
    public void pacientesMayoresDe(int edad) {
        titulo("3. Pacientes mayores de " + edad + " años");
        TypedQuery<Paciente> q = em.createQuery(
                "SELECT p FROM Paciente p WHERE p.edad > :edad AND p.eliminado = false",
                Paciente.class);
        q.setParameter("edad", edad);
        for (Paciente p : q.getResultList()) {
            System.out.println("  " + p);
        }
    }

    // 4. Consultas realizadas por un medico especifico (por matricula)
    public void consultasDeMedico(String matricula) {
        titulo("4. Consultas del médico con matrícula " + matricula);
        TypedQuery<Consulta> q = em.createQuery(
                "SELECT c FROM Medico m JOIN m.consultas c WHERE m.matricula = :matricula "
                + "ORDER BY c.fecha", Consulta.class);
        q.setParameter("matricula", matricula);
        for (Consulta c : q.getResultList()) {
            System.out.println("  " + c);
        }
    }

    // 5. Medicamentos asociados a un paciente (por DNI)
    public void medicamentosDePaciente(int dni) {
        titulo("5. Medicamentos del paciente con DNI " + dni);
        TypedQuery<Medicamento> q = em.createQuery(
                "SELECT m FROM Paciente p JOIN p.medicamentos m WHERE p.dni = :dni",
                Medicamento.class);
        q.setParameter("dni", dni);
        for (Medicamento m : q.getResultList()) {
            System.out.println("  " + m);
        }
    }

    // 6. Consultas con su diagnostico y el nombre del paciente
    public void consultasConPaciente() {
        titulo("6. Consultas con diagnóstico y paciente");
        List<Object[]> filas = em.createQuery(
                "SELECT c.fecha, c.diagnostico, p.nombre, p.apellido "
                + "FROM Paciente p JOIN p.consultas c ORDER BY c.fecha", Object[].class)
                .getResultList();
        for (Object[] f : filas) {
            System.out.printf("  %s | %-28s | %s %s%n", (LocalDate) f[0], f[1], f[2], f[3]);
        }
    }

    // 7. Promedio de edad de los pacientes
    public void promedioEdad() {
        titulo("7. Promedio de edad de los pacientes");
        Double promedio = em.createQuery(
                "SELECT AVG(p.edad) FROM Paciente p WHERE p.eliminado = false", Double.class)
                .getSingleResult();
        System.out.printf("  %.2f años%n", promedio);
    }

    // 8. Pacientes con una obra social especifica
    public void pacientesPorObraSocial(String obraSocial) {
        titulo("8. Pacientes con obra social " + obraSocial);
        TypedQuery<Paciente> q = em.createQuery(
                "SELECT p FROM Paciente p WHERE p.obraSocial = :os AND p.eliminado = false",
                Paciente.class);
        q.setParameter("os", obraSocial);
        for (Paciente p : q.getResultList()) {
            System.out.println("  " + p);
        }
    }

    // 9. Medicos y la cantidad de consultas que atendieron
    public void medicosYCantidadConsultas() {
        titulo("9. Médicos y cantidad de consultas atendidas");
        List<Object[]> filas = em.createQuery(
                "SELECT m.nombre, m.apellido, COUNT(c) FROM Medico m LEFT JOIN m.consultas c "
                + "GROUP BY m.id, m.nombre, m.apellido", Object[].class)
                .getResultList();
        for (Object[] f : filas) {
            System.out.println("  Dr/a. " + f[0] + " " + f[1] + ": " + f[2] + " consulta(s)");
        }
    }

    // 10. Pacientes junto con la descripcion de su historia clinica
    public void pacientesConHistoriaClinica() {
        titulo("10. Pacientes y su historia clínica");
        List<Object[]> filas = em.createQuery(
                "SELECT p.nombre, p.apellido, h.descripcion FROM Paciente p "
                + "LEFT JOIN p.historiaClinica h", Object[].class)
                .getResultList();
        for (Object[] f : filas) {
            String descripcion = (f[2] == null) ? "(sin historia clínica)" : (String) f[2];
            System.out.println("  " + f[0] + " " + f[1] + ": " + descripcion);
        }
    }

    private void titulo(String texto) {
        System.out.println();
        System.out.println("=== " + texto + " ===");
    }
}
