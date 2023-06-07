package org.example.convertor;

import jakarta.persistence.AttributeConverter;
import jakarta.persistence.Convert;
import org.example.entity.Birthday;

import java.sql.Date;
import java.util.Optional;

// Конвертор для преобразования типов кастомных данных для хибера
@Convert
public class BirthdayConverter implements AttributeConverter<Birthday, Date> {

    @Override
    public Date convertToDatabaseColumn(Birthday attribute) {
        return Optional.ofNullable(attribute)
                .map(b -> b.birthDate())
                .map(d -> Date.valueOf(d))
                .orElse(null);
    }

    @Override
    public Birthday convertToEntityAttribute(Date dbData) {
        return Optional.ofNullable(dbData)
                .map(Date::toLocalDate)
                .map(Birthday::new)
                .orElse(null);
    }
}
