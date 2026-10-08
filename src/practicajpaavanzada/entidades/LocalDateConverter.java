package practicajpaavanzada.entidades;

import java.sql.Date;
import java.time.LocalDate;
import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

/**
 * Convierte LocalDate <-> java.sql.Date para que funcione con cualquier
 * version de JPA/EclipseLink. Se aplica automaticamente a todos los LocalDate.
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate fecha) {
        return fecha == null ? null : Date.valueOf(fecha);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date fecha) {
        return fecha == null ? null : fecha.toLocalDate();
    }
}
