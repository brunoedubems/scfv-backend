package br.com.brunoedubems.scfv_backend.controller.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record GrupoRequest(
        @NotBlank @Size(max = 100)
        String nome,
        @NotBlank @Size(max = 100)
        String tecnico,
        @NotBlank @Size(max = 50)
        String faixaEtaria
) {
}
