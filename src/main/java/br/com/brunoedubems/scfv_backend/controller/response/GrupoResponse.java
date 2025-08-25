package br.com.brunoedubems.scfv_backend.controller.response;

import java.util.List;
import java.util.Set;

public record GrupoResponse(
        Long id,
        String nome,
        String tecnico,
        String faixaEtaria,
        List<UsuarioResponse> usuarios

) {
}
