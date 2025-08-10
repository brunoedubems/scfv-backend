package br.com.brunoedubems.scfv_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "grupo")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "O nome do grupo é obrigatório")
    @Size(max = 100, message = "O nome do grupo deve ter no máximo 100 caracteres")
    private String nome;

    @NotBlank(message = "O nome do técnico é obrigatório")
    @Size(max = 100, message = "O nome do técnico deve ter no máximo 100 caracteres")
    private String tecnico;

    @NotBlank(message = "A faixa etária é obrigatória")
    @Size(max = 50, message = "A faixa etária deve ter no máximo 50 caracteres")
    @Column(name = "faixa_etaria")
    private String faixaEtaria;

    @OneToMany(mappedBy = "grupo", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Usuario> usuarios;

}
