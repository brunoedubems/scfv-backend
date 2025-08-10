package br.com.brunoedubems.scfv_backend.dto;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {

    Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    String nome;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve estar no passado")
    LocalDate dataNascimento;

    @NotBlank(message = "O CPF é obrigatório")
    @Size(min = 11, max = 11, message = "O CPF deve ter exatamente 11 dígitos")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter apenas números")
    String cpf;

    @NotBlank(message = "O NIS é obrigatório")
    @Size(min = 11, max = 11, message = "O NIS deve ter exatamente 11 dígitos")
    @Pattern(regexp = "\\d{11}", message = "O NIS deve conter apenas números")
    String nis;

    private String rg;
    private String sexo;
    private String telefone;
    private String nomeMae;
    private String nomeResponsavel;
    private boolean prioritario;
    Long grupoId;

}
