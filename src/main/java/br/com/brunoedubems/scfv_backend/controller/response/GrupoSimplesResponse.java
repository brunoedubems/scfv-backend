package br.com.brunoedubems.scfv_backend.controller.response;

public record GrupoSimplesResponse(
        Long id,
        String nome,
        String tecnico,
        String faixaEtaria
) {
}
