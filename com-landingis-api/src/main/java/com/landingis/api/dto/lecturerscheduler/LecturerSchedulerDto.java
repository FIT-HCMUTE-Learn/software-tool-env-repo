package com.landingis.api.dto.lecturerscheduler;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.landingis.api.dto.period.PeriodDto;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class LecturerSchedulerDto {
    private Long lecturerId;
    private Long courseId;
    private PeriodDto period;
}
