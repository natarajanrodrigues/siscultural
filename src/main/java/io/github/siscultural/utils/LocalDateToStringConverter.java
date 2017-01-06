/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package io.github.siscultural.utils;

import org.springframework.core.convert.converter.Converter;

import javax.persistence.AttributeConverter;
import java.lang.annotation.Annotation;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Victor Hugo <victor.hugo.origins@gmail.com>
 */
//@Converter(autoApply = true)
public class LocalDateToStringConverter implements Converter<String, LocalDate> {

    public static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    @Override
    public LocalDate convert(String s) {
        if (s == null) {
            return null;
        }

        return LocalDate.parse(s, formatter);
    }

//    @Override
//    public String convertToDatabaseColumn(LocalDate localDate) {
//
//        if (localDate == null) {
//            return null;
//        }
//
//        return formatter.format(localDate);
//    }
//
//    @Override
//    public LocalDate convertToEntityAttribute(String string) {
//
//        if (string == null) {
//            return null;
//        }
//
//        return LocalDate.parse(string, formatter);
//    }
//
//    @Override
//    public String convert(LocalDate localDate) {
//        return null;
//    }
}
