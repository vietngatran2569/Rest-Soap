package com.example.restdemo2.validation;

import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.Locale;

public class ValidationErrorBuilder {

    private ObjectError objectError;

    public static ValidationError fromBindingErrors(Errors errors, MessageSource messageSource) {
        String message = null;
        ValidationError error = new ValidationError("Validation failed. " + errors.getErrorCount() + " error(s)", message);
        for (ObjectError objectError : errors.getAllErrors()) {
            FieldError fieldError = (FieldError) objectError;
            message=messageSource.getMessage(fieldError.getField(), null, LocaleContextHolder.getLocale());
            objectError = new ObjectError(objectError.getObjectName(), objectError.getCodes(), objectError.getArguments(), message);
            error.addValidationError(objectError);
        }
        return error;
    }

    public static ValidationError fromBindingErrors(ConstraintViolationException e, MessageSource messageSource) {
        String message = messageSource.getMessage("NotBlank.task.name", null, LocaleContextHolder.getLocale());
        ValidationError error = new ValidationError("Validation failed. " + e.getConstraintViolations().size() + " error(s)", message);
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            error.addValidationError(new ObjectError(e.getMessage(), violation.getMessage()));
        }
        return error;
    }
}
