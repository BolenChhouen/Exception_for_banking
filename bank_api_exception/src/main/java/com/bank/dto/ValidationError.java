package com.bank.dto;

public record ValidationError(
        String field,
        String message
) {
}
