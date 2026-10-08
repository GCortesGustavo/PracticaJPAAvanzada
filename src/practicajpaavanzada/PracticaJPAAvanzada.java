package practicajpaavanzada;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 * Programacion 3 - UTN - Unidad 10: JPA (practica avanzada).
 */
public class PracticaJPAAvanzada {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("PracticaJPAAvanzadaPU");
        EntityManager em = emf.createEntityManager();

        try {
            // 2. Persistir los datos de prueba
            CargaDatos.cargar(em);

            // Limpia el contexto para que las consultas lean directamente de la base de datos
            em.clear();

            Reportes reportes = new Reportes(em);
            reportes.pacientesMayoresDe(30);                 // 3
            reportes.consultasDeMedico("MP-1001");           // 4
            reportes.medicamentosDePaciente(30123456);       // 5
            reportes.consultasConPaciente();                 // 6
            reportes.promedioEdad();                         // 7
            reportes.pacientesPorObraSocial("OSEP");         // 8
            reportes.medicosYCantidadConsultas();            // 9
            reportes.pacientesConHistoriaClinica();          // 10
        } finally {
            em.close();
            emf.close();
        }
    }
}
