/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.utils;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
@Converter(autoApply = true)
public class LocalDateConverter implements AttributeConverter<LocalDate, Timestamp> {

    @Override
    public Timestamp convertToDatabaseColumn(LocalDate localDate) {

        if (localDate == null) {
            return null;
        }

        LocalDateTime myLdt = LocalDateTime.of(localDate.getYear(), localDate.getMonth(), localDate.getDayOfMonth(), 0, 0);

        return Timestamp.valueOf(myLdt);
    }

    @Override
    public LocalDate convertToEntityAttribute(Timestamp timestamp) {

        if (timestamp == null) {
            return null;
        }
        
        return timestamp.toLocalDateTime().toLocalDate();
    }

}
