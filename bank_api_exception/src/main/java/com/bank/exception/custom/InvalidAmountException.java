package com.bank.exception.custom;

import com.bank.exception.BaseException;
import com.bank.exception.ErrorCategory;
import com.bank.exception.ErrorCode;
import org.springframework.http.HttpStatus;

public class InvalidAmountException extends BaseException {

    public InvalidAmountException() {
        super(
                "Invalid amount.",
                ErrorCode.INVALID_AMOUNT,
                HttpStatus.BAD_REQUEST,
                ErrorCategory.BUSINESS,
                true
        );
    }
}
