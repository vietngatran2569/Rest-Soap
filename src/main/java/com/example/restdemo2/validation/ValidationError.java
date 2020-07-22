package com.example.restdemo2.validation;

import com.fasterxml.jackson.annotation.JsonInclude;
import org.springframework.validation.ObjectError;

import java.util.ArrayList;
import java.util.List;

public class ValidationError {
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ObjectError> errors = new ArrayList<>();
    private String description;
    private String message;

    public ValidationError(String description, String message) {
        this.description = description;
        this.message = message;
    }

    void addValidationError(ObjectError objectError) {
        errors.add(objectError);
    }

    public List<ObjectError> getErrors() {
        return errors;
    }

    public String getDescription() {
        return description;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public String toString() {
        return "ValidationError{" +
                "errors=" + errors +
                ", description='" + description + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}
