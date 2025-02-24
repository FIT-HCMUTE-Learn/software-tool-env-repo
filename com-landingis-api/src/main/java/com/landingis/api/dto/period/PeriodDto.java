package com.landingis.api.dto.period;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PeriodDto {
    private Long periodId;
    private String periodName;
    private String periodDescription;
    private LocalDate periodStartDate;
    private LocalDate periodDueDate;
    private Integer periodState;
}
