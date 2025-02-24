package com.landingis.api.converter;

import org.springframework.core.convert.converter.Converter;

import java.util.Arrays;

public class StringToEnumConverter<T extends Enum<T>> implements Converter<String, T> {

    private final Class<T> enumType;

    public StringToEnumConverter(Class<T> enumType) {
        this.enumType = enumType;
    }

    @Override
    public T convert(String source) {
        if (source.trim().isEmpty()) {
            throw new IllegalArgumentException("Enum value cannot be empty.");
        }
        try {
            return Enum.valueOf(enumType, source.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid value: '" + source + "'. Allowed values: "
                    + Arrays.toString(enumType.getEnumConstants()));
        }
    }
}
