package br.com.brunoedubems.scfv_backend.controller.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.util.Set;

public record UsuarioRequest(
        @NotBlank
        @Size(max = 100)
        String nome,

        @NotNull
        @Past
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")
        LocalDate dataNascimento,

        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String cpf,

        @NotBlank
        @Pattern(regexp = "\\d{11}")
        String nis,

        @Size(max = 20)
        String rg,

        @Size(max = 10)
        String sexo,

        @Size(max = 20)
        String telefone,

        @Size(max = 100)
        String nomeMae,

        @Size(max = 100)
        String nomeResponsavel,

        Long grupoId,
        boolean prioritario,

        Set<String> situacoes
) {}
