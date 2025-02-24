package com.landingis.api.validation;

import com.landingis.api.validation.impl.PeriodStateImpl;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PeriodStateImpl.class)
@Target({ElementType.FIELD, ElementType.PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
public @interface PeriodState {
    String message() default "Period state must be 0 (Init), 1 (Recruit), or 2 (Done)";

    boolean allowNull() default true;

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
