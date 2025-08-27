package br.com.brunoedubems.scfv_backend.controller.response.error;

public record ValidacaoError(
        String field,
        String message,
        Object rejectedValue,
        String code
) {
}
