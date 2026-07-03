package com.bank.exception;

import com.bank.dto.ErrorResponse;
import com.bank.dto.ValidationError;
import com.bank.dto.ValidationErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.constraints.Positive;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BaseException.class)
    public ProblemDetail handleBaseException(
            BaseException ex,
            HttpServletRequest request
    ){

        ProblemDetail problem = ProblemDetail.forStatusAndDetail(
                ex.getStatus(),
                ex.getMessage()
        );

        problem.setTitle(ex.getStatus().getReasonPhrase());
        problem.setDetail(ex.getMessage());

//        Adding Custom Properties
        problem.setProperty("errorCode", ex.getErrorCode());
        problem.setProperty("path", request.getRequestURI());

        // because APIs usually use UTC.
        problem.setProperty("timestamp", Instant.now());

        return  problem;
    }


    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetail> handleMethodArgumentNotValidException(
            MethodArgumentNotValidException ex,
            HttpServletRequest request
    ){

        Map<String,String> errors;

        ProblemDetail problem =
                ProblemDetail.forStatus(HttpStatus.BA);



        List<Map<String, String>> errorList = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> Map.of(
                        "field", error.getField(),
                        "message", error.getDefaultMessage()
                        ))
                .toList();

        problem.setTitle("Invalid Request");
        problem.setProperty("error", errorList);
        problem.setProperty("errorCode", HttpStatus.BAD_REQUEST.getReasonPhrase());
        problem.setProperty("path", request.getRequestURI());
        problem.setProperty("timestamp", Instant.now());

        return ResponseEntity.badRequest().body(problem);
    }

    @ExceptionHandler(Exception.class)
    public ProblemDetail handleException(
            Exception ex,
            HttpServletRequest request) {
        ProblemDetail problem = ProblemDetail
                .forStatusAndDetail(
                        HttpStatus.INTERNAL_SERVER_ERROR,
                        "Unexpected error occurred!"
                );

        problem.setTitle("Internal Server Error");

        return problem;
    }

    // Helper
    private ProblemDetail forStatus(HttpStatus status) {
        ProblemDetail problem = ProblemDetail.forStatus(status);

    }

}
