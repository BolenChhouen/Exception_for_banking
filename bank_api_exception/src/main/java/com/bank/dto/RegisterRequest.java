package com.bank.dto;

import com.bank.entity.User;

public record RegisterRequest(
        String username,
        String password,
        String role
) {
}
