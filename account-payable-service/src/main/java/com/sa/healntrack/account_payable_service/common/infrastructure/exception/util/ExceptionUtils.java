package com.sa.healntrack.account_payable_service.common.infrastructure.exception.util;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.validation.FieldError;

public class ExceptionUtils {
    
    private ExceptionUtils() { }

    public static boolean isCausedBy(Throwable cause, Class<? extends Throwable> exceptionType) {
        while (cause != null) {
            if (exceptionType.isInstance(cause)) {
                return true;
            }
            cause = cause.getCause();
        }
        return false;
    }

    public static Map<String, String> extractFieldErrors(List<FieldError> errors) {
        return errors.stream()
                .collect(Collectors.toMap(
                        FieldError::getField,
                        FieldError::getDefaultMessage));
    }

}
