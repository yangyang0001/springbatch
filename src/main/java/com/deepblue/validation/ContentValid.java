package com.deepblue.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target({ METHOD, FIELD, ANNOTATION_TYPE, PARAMETER})
@Retention(RUNTIME)
@Documented
@Constraint(validatedBy = ContentValidator.class)
public @interface ContentValid {

    int max() default 100;

    String message() default "can not empty";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
