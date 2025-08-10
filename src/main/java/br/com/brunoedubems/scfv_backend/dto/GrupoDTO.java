package br.com.brunoedubems.scfv_backend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class GrupoDTO {
    private Long id;
    private String nome;
    private String tecnico;
    private String faixaEtaria;
    private List<UsuarioDTO> usuarios;

}
