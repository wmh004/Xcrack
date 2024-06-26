package com.example.Xcrack.Utility;

import java.sql.Date;
import java.time.LocalDate;

import jakarta.persistence.AttributeConverter;

public class LocalDateConverter implements AttributeConverter<LocalDate, Date> {

    @Override
    public Date convertToDatabaseColumn(LocalDate localDate) {
        return localDate == null ? null : Date.valueOf(localDate);
    }

    @Override
    public LocalDate convertToEntityAttribute(Date date) {
        return date == null ? null : date.toLocalDate();
    }

}
