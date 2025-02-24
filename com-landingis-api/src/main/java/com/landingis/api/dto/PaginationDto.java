package com.landingis.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaginationDto<T> {
    private List<T> content;
    private long totalElements;
    private int totalPages;
}
