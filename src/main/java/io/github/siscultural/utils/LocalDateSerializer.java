package io.github.siscultural.utils;

import com.fasterxml.jackson.databind.JsonSerializable;
import com.google.gson.JsonElement;
import com.google.gson.JsonPrimitive;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;

import java.lang.reflect.Type;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 * Created by natarajan on 02/11/16.
 */
public class LocalDateSerializer implements JsonSerializer<LocalDate> {
    @Override
    public JsonElement serialize(LocalDate date, Type typeOfSrc, JsonSerializationContext context) {
        return new JsonPrimitive(date.format(DateTimeFormatter.ISO_LOCAL_DATE)); // "yyyy-mm-dd"
//        return new JsonPrimitive(date.format(DateTimeFormatter.ofPattern("dd/MM/yyyy"))); // "yyyy-mm-dd"
    }
}
