package com.chandanws.fse.springboot.persistence.constraints;

import javax.validation.Constraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.Payload;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;

@Target(FIELD)
@Retention(RUNTIME)
@Constraint(validatedBy = NumericConstraint.NumberConstraintValidator.class)
public @interface NumericConstraint {

    String message() default "Invalid data";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int min() default 0;

    int max() default Integer.MAX_VALUE;

    class NumberConstraintValidator implements ConstraintValidator<NumericConstraint, Integer> {

        private int min;
        private int max;

        @Override
        public void initialize(NumericConstraint constraintAnnotation) {
            this.min = constraintAnnotation.min();
            this.max = constraintAnnotation.max();
        }

        @Override
        public boolean isValid(Integer value, ConstraintValidatorContext context) {
            return value != null && value >= min && value <= max;
        }
    }
}
