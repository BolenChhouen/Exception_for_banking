package com.bank.exception.custom;

import com.bank.exception.BaseException;
import com.bank.exception.ErrorCategory;
import com.bank.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class UserNotFoundException extends BaseException {
    protected UserNotFoundException(Long id) {
        super(
                "Account not found with id: " + id,
                ErrorCode.ACCOUNT_NOT_FOUND,
                HttpStatus.NOT_FOUND,
                ErrorCategory.BUSINESS,
                false
        );
    }
}
