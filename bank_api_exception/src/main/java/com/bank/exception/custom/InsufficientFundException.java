package com.bank.exception.custom;

import com.bank.exception.BaseException;
import com.bank.exception.ErrorCategory;
import com.bank.exception.ErrorCode;
import org.springframework.http.HttpStatus;

import java.math.BigDecimal;

public class InsufficientFundException extends BaseException {


    public InsufficientFundException(
            BigDecimal balance,
            BigDecimal amount
    ) {
        super(
                "Balance: " + balance
                        + ", Attempted Withdrawal: " + amount,
                ErrorCode.INSUFFICIENT_BALANCE,
                HttpStatus.BAD_REQUEST,
                ErrorCategory.BUSINESS,
                true
        );
    }
}
