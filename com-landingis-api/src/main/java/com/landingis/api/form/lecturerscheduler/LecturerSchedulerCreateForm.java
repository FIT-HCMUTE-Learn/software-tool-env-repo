package com.landingis.api.form.lecturerscheduler;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class LecturerSchedulerCreateForm {

    @ApiModelProperty(value = "Lecturer id", example = "1", required = true)
    @NotNull(message = "Lecturer id cannot be null")
    private Long lecturerId;

    @ApiModelProperty(value = "Course id", example = "1", required = true)
    @NotNull(message = "Course id cannot be null")
    private Long courseId;

    @ApiModelProperty(value = "Period id", example = "1", required = true)
    @NotNull(message = "Period id cannot be null")
    private Long periodId;
}
