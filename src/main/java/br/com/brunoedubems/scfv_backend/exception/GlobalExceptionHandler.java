package br.com.brunoedubems.scfv_backend.exception;

import br.com.brunoedubems.scfv_backend.controller.response.error.ErrorResponse;
import br.com.brunoedubems.scfv_backend.controller.response.error.ValidacaoError;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.server.ResponseStatusException;

import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);
    private MessageSource messageSource;

    private Map<String, Object> createBody(HttpStatus status, String message, String path) {
        Map<String, Object> body = new HashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", status.value());
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        body.put("path", path);
        return body;
    }

    @ExceptionHandler({EntityNotFoundException.class, ResourceNotFoundException.class})
    public ResponseEntity<Map<String, Object>> handleNotFound(RuntimeException ex, HttpServletRequest request) {
        // Mensagens de 'not found' normalmente representam 404
        log.info("Resource not found: {}", ex.getMessage());
        Map<String, Object> body = createBody(HttpStatus.NOT_FOUND, ex.getMessage(), request.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<Map<String, Object>> handleDataIntegrity(DataIntegrityViolationException ex,
                                                                   HttpServletRequest request) {
        // Mensagem genérica para o cliente; detalhe permanece nos logs
        log.warn("Data integrity violation: {}", ex.getMessage());
        String msg = "Violação de integridade de dados (ex.: CPF/NIS duplicado ou FK impedindo operação).";
        Map<String, Object> body = createBody(HttpStatus.CONFLICT, msg, request.getRequestURI());
        return ResponseEntity.status(HttpStatus.CONFLICT).body(body);
    }

    @ExceptionHandler(ResponseStatusException.class)
    public ResponseEntity<Map<String, Object>> handleResponseStatus(ResponseStatusException ex,
                                                                    HttpServletRequest request) {
        log.info("ResponseStatusException: {}", ex.getMessage());
        Map<String, Object> body = createBody((HttpStatus) ex.getStatusCode(), ex.getReason(), request.getRequestURI());
        return ResponseEntity.status(ex.getStatusCode()).body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidation(MethodArgumentNotValidException ex,
                                                          HttpServletRequest request) {
        log.info("Validation failed: {} error(s)", ex.getBindingResult().getErrorCount());

        List<ValidacaoError> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> {
                    String msg = messageSource.getMessage(fe, LocaleContextHolder.getLocale());
                    Object rejected = fe.getRejectedValue();
                    // filtrar valores sensíveis se necessário
                    if ("senha".equalsIgnoreCase(fe.getField())) {
                        rejected = null;
                    }
                    return new ValidacaoError(fe.getField(), msg, rejected, fe.getCode());
                })
                .collect(Collectors.toList());

        ErrorResponse body = new ErrorResponse(
                Instant.now().toString(),
                HttpStatus.BAD_REQUEST.value(),
                HttpStatus.BAD_REQUEST.getReasonPhrase(),
                "Erros de validação",
                request.getRequestURI(),
                errors
        );

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleAll(Exception ex, HttpServletRequest request) {
        log.error("Unhandled error", ex);
        Map<String, Object> body = createBody(HttpStatus.INTERNAL_SERVER_ERROR,
                "Ocorreu um erro interno. Contate o suporte.", request.getRequestURI());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
