package com.landingis.api.util;

import com.landingis.api.exception.ResourceNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Arrays;

public class MappingUtils {

    public static <T, ID> T getEntityById(JpaRepository<T, ID> repository, ID id, Class<T> entityClass) {
        return repository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException(entityClass.getSimpleName() + " with ID " + id + " not found")
        );
    }

    public static <E extends Enum<E>> E mapStringToEnum(String value, Class<E> enumType) {
        if (value == null || value.trim().isEmpty()) {
            throw new IllegalArgumentException(enumType.getSimpleName() + " cannot be null or empty");
        }
        return Arrays.stream(enumType.getEnumConstants())
                .filter(e -> e.name().equalsIgnoreCase(value))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(
                        "Invalid " + enumType.getSimpleName() + ": '" + value + "'. Allowed values: " + Arrays.toString(enumType.getEnumConstants())
                ));
    }
}
