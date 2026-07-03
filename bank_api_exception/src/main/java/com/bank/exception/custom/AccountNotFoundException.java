package com.bank.exception.custom;

import com.bank.exception.BaseException;
import com.bank.exception.ErrorCategory;
import com.bank.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class AccountNotFoundException extends BaseException {
    protected AccountNotFoundException(Long accountId) {
        super(
                "Account not found with id: " + accountId,
                ErrorCode.ACCOUNT_NOT_FOUND,
                HttpStatus.NOT_FOUND,
                ErrorCategory.BUSINESS,
                false
        );
    }
}
