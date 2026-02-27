package com.deepblue.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.apache.commons.lang3.StringUtils;

public class ContentValidator implements ConstraintValidator<ContentValid, String> {

    private int max;

    @Override
    public void initialize(ContentValid constraintAnnotation) {
        this.max = constraintAnnotation.max();
    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if (StringUtils.isBlank(value)) {
            String message = "content cannot be empty";
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return false;
        }

        if(value.length() > this.max) {
            String message = "content's maximum is " + this.max + " length";
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate(message).addConstraintViolation();
            return false;
        }

        return true;
    }


    public static void main(String[] args) {
        String param = "    ";
        System.out.println(StringUtils.isBlank(param));
    }
}