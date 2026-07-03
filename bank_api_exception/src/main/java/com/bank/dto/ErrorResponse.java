package com.bank.dto;

import java.time.LocalDateTime;

public record ErrorResponse(
        String errorId,
        int status,
        String errorCode,
        String category,
        boolean retryable,
        String errorMessage,
        String path,
        LocalDateTime timestamp
) {
}
