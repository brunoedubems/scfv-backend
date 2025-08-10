package br.com.brunoedubems.scfv_backend.dto;

import br.com.brunoedubems.scfv_backend.entity.Grupo;
import jakarta.persistence.*;
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

    private Long id;
    private String nome;
    private LocalDate dataNascimento;
    private String cpf;
    private String nis;
    private String rg;
    private String sexo;
    private String telefone;
    private String nomeMae;
    private String nomeResponsavel;
    private boolean prioritario;
    private GrupoDTO grupo;

}
