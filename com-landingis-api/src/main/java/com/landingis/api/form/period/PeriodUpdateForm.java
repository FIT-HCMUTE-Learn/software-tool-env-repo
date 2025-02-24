package com.landingis.api.form.period;

import com.landingis.api.validation.PeriodState;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Future;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PeriodUpdateForm {

    @ApiModelProperty(value = "Period id", example = "1", required = true)
    @NotNull(message = "Period id cannot be null")
    private Long periodId;

    @ApiModelProperty(value = "Period name", example = "period01")
    private String periodName;

    @ApiModelProperty(value = "Period description", example = "A brief description of the period")
    private String periodDescription;

    @ApiModelProperty(value = "Period start date", example = "18/2/2050")
    @Future(message = "Period start date must be in the future")
    private LocalDate periodStartDate;

    @ApiModelProperty(value = "Period due date", example = "20/2/2050")
    @Future(message = "Period due date must be in the future")
    private LocalDate periodDueDate;

    @ApiModelProperty(value = "Period state (0: Init, 1: Recruit, 2: Done", example = "0")
    @PeriodState
    private Integer periodState;
}
