package com.tradex.trade.service.common.exception;

import com.tradex.trade.service.api.dto.ProblemDetailsDTO;
import com.tradex.trade.service.api.dto.SubErrorDTO;
import com.tradex.trade.service.infrastructure.web.CorrelationIdFilter;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
