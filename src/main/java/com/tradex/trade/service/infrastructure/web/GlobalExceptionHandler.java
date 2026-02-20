package com.tradex.trade.service.infrastructure.web;

import com.tradex.trade.service.shared.ApplicationException;
import com.tradex.trade.service.shared.PersistanceFailureException;
import com.tradex.trade.service.domain.exception.RecordNotFoundException;
import com.tradex.trade.service.domain.exception.ValidationException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;
import java.time.Instant;
import java.util.List;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ProblemDetailsDTO> handleValidation(
            ValidationException ex,
            HttpServletRequest request) {

        return buildProblem(
                ex,
                HttpStatus.BAD_REQUEST,
                request,
                ex.getSubErrors()
        );
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ProblemDetailsDTO> handleNotFound(
            RecordNotFoundException ex,
            HttpServletRequest request) {

        return buildProblem(
                ex,
                HttpStatus.NOT_FOUND,
                request,
                null
        );
    }

    @ExceptionHandler(RecordNotFoundException.class)
    public ResponseEntity<ProblemDetailsDTO> handleResourceAlreadyExists(
            RecordNotFoundException ex,
            HttpServletRequest request) {

        return buildProblem(
                ex,
                HttpStatus.UNPROCESSABLE_CONTENT,
                request,
                null
        );
    }

    @ExceptionHandler(PersistanceFailureException.class)
    public ResponseEntity<ProblemDetailsDTO> handlePersistanceFailure(
            RecordNotFoundException ex,
            HttpServletRequest request) {

        return buildProblem(
                ex,
                HttpStatus.INTERNAL_SERVER_ERROR,
                request,
                null
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ProblemDetailsDTO> handleSpringValidation(
            MethodArgumentNotValidException ex,
            HttpServletRequest request) {

        List<SubErrorDTO> subErrors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(err -> new SubErrorDTO(
                        err.getField(),
                        err.getRejectedValue(),
                        err.getDefaultMessage()))
                .toList();

        ValidationException wrapped =
                new ValidationException("Validation failed", subErrors);

        return buildProblem(
                wrapped,
                HttpStatus.BAD_REQUEST,
                request,
                subErrors
        );
    }

    private ResponseEntity<ProblemDetailsDTO> buildProblem(
            ApplicationException ex,
            HttpStatus status,
            HttpServletRequest request,
            List<SubErrorDTO> subErrors) {

        String correlationId = MDC.get(CorrelationIdFilter.MDC_KEY);

        ProblemDetailsDTO problem = new ProblemDetailsDTO(
                URI.create("https://api.example.com/problems/" + ex.getErrorCode().name()),
                status.getReasonPhrase(),
                status.value(),
                ex.getMessage(),
                URI.create(request.getRequestURI()),
                ex.getErrorCode().name(),
                correlationId,
                Instant.now(),
                subErrors
        );

        return ResponseEntity.status(status).body(problem);
    }

}
