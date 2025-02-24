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
public class LecturerSchedulerUpdateForm {

    @ApiModelProperty(value = "Lecturer scheduler id", example = "1", required = true)
    @NotNull(message = "Lecturer scheduler id                                cannot be null")
    private Long lecturerSchedulerId;

    @ApiModelProperty(value = "Lecturer id", example = "1")
    private Long lecturerId;

    @ApiModelProperty(value = "Course id", example = "1")
    private Long courseId;

    @ApiModelProperty(value = "Period id", example = "1")
    private Long periodId;
}
