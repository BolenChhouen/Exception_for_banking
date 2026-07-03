package com.bank.exception;

import org.springframework.http.HttpStatus;

public abstract class BaseException extends RuntimeException {

    private final ErrorCode errorCode;
    private final HttpStatus status;
    private final ErrorCategory category;
    private final boolean retryable;

    protected BaseException(
            String message,
            ErrorCode errorCode,
            HttpStatus status,
            ErrorCategory category,
            boolean retryable) {
        super(message);
        this.errorCode = errorCode;
        this.status = status;
        this.category = category;
        this.retryable = retryable;
    }

    public ErrorCode getErrorCode() {
        return errorCode;
    }
    public ErrorCategory getCategory() {
        return category;
    }
    public HttpStatus getStatus() {
        return status;
    }
    public boolean isRetryable() {
        return retryable;
    }
}
