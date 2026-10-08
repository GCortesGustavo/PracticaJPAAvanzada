package practicajpaavanzada;

import java.time.LocalDate;
import javax.persistence.EntityManager;
import practicajpaavanzada.entidades.Consulta;
import practicajpaavanzada.entidades.HistoriaClinica;
import practicajpaavanzada.entidades.Medicamento;
import practicajpaavanzada.entidades.Medico;
import practicajpaavanzada.entidades.Paciente;

/**
 * Persiste los datos de prueba: 3 pacientes, 2 medicos, 4 consultas,
 * 2 historias clinicas y 3 medicamentos.
 */
public class CargaDatos {

    public static void cargar(EntityManager em) {
        em.getTransaction().begin();
        try {
            // ---- 3 medicamentos ----
            Medicamento enalapril = new Medicamento("Enalapril", "Maleato de enalapril", 30);
            Medicamento ibuprofeno = new Medicamento("Ibuprofeno", "Ibuprofeno", 20);
            Medicamento paracetamol = new Medicamento("Paracetamol", "Paracetamol", 25);
            em.persist(enalapril);
            em.persist(ibuprofeno);
            em.persist(paracetamol);

            // ---- 2 medicos ----
            Medico fernandez = new Medico("Roberto", "Fernández", 52, "Cardiología", "MP-1001");
            Medico martinez = new Medico("Laura", "Martínez", 45, "Clínica Médica", "MP-2002");

            // ---- 3 pacientes ----
            Paciente juan = new Paciente("Juan", "Pérez", 30123456, "OSEP",
                    LocalDate.of(1985, 3, 15), 'M');
            Paciente maria = new Paciente("María", "González", 40234567, "OSDE",
                    LocalDate.of(1998, 7, 22), 'F');
            Paciente carlos = new Paciente("Carlos", "Rodríguez", 24345678, "OSEP",
                    LocalDate.of(1975, 11, 2), 'M');

            // ---- 4 consultas (fechas y diagnosticos distintos) ----
            Consulta c1 = new Consulta(LocalDate.of(2026, 3, 10), "Hipertensión arterial leve");
            Consulta c2 = new Consulta(LocalDate.of(2026, 4, 22), "Gripe estacional");
            Consulta c3 = new Consulta(LocalDate.of(2026, 6, 5), "Esguince de tobillo");
            Consulta c4 = new Consulta(LocalDate.of(2026, 9, 18), "Colesterol elevado");

            // Medico -> consultas que atendio
            fernandez.getConsultas().add(c1);
            fernandez.getConsultas().add(c4);
            martinez.getConsultas().add(c2);
            martinez.getConsultas().add(c3);

            // Paciente -> consultas que tuvo
            juan.getConsultas().add(c1);
            juan.getConsultas().add(c4);
            maria.getConsultas().add(c2);
            carlos.getConsultas().add(c3);

            // Paciente -> medicamentos (n..m)
            juan.getMedicamentos().add(enalapril);
            juan.getMedicamentos().add(paracetamol);
            maria.getMedicamentos().add(paracetamol);
            carlos.getMedicamentos().add(ibuprofeno);
            carlos.getMedicamentos().add(paracetamol);

            // ---- 2 historias clinicas (Maria queda sin historia a proposito) ----
            juan.asignarHistoriaClinica(new HistoriaClinica(
                    "Antecedentes familiares de hipertensión. Sin alergias conocidas."));
            carlos.asignarHistoriaClinica(new HistoriaClinica(
                    "Cirugía de rodilla en 2015. Alérgico a la penicilina."));

            // Por cascade, al persistir medicos y pacientes se persisten tambien
            // sus consultas e historias clinicas.
            em.persist(fernandez);
            em.persist(martinez);
            em.persist(juan);
            em.persist(maria);
            em.persist(carlos);

            em.getTransaction().commit();
            System.out.println("Datos de prueba persistidos correctamente.");
        } catch (RuntimeException e) {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            throw e;
        }
    }
}
