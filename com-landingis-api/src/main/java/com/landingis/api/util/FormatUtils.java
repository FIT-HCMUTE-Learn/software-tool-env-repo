package com.landingis.api.util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class FormatUtils {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_DATE;

    public static String formatDate(LocalDate date) {
        return (date != null) ? date.format(FORMATTER) : null;
    }

    public static LocalDate parseDate(String dateStr) {
        return (dateStr != null && !dateStr.isEmpty()) ? LocalDate.parse(dateStr, FORMATTER) : null;
    }
}
