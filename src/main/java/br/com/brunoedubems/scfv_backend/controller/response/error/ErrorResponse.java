package br.com.brunoedubems.scfv_backend.controller.response.error;

import org.springframework.boot.context.properties.bind.validation.ValidationErrors;

import java.util.List;

public record ErrorResponse(
        String timestamp,
        int status,
        String error,
        String message,
        String path,
        List<ValidacaoError> validationErrors
) {
}
