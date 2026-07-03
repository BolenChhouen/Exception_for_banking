package com.bank.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ValidationErrorResponse(
        int status,
        String errorCode,
        List<ValidationError> errors,
        String path,
        LocalDateTime timestamp
) {
}
