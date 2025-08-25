package br.com.brunoedubems.scfv_backend.controller.response;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;
import java.util.Set;

public record UsuarioResponse(
        Long id,
        String nome,
        LocalDate dataNascimento,
        String cpf,
        String nis,
        String rg,
        String sexo,
        String telefone,
        String nomeMae,
        String nomeResponsavel,
        Long grupoId,
        boolean prioritario,
        Set<String> situacoes
) {
}
