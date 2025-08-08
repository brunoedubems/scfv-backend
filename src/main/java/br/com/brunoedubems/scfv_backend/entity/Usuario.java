package br.com.brunoedubems.scfv_backend.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String nome;

    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @Column(unique = true, nullable = false, length = 11)
    private String cpf;

    @Column(unique = true, nullable = false, length = 11)
    private String nis;

    @Column(length = 20)
    private String rg;

    @Column(length = 10)
    private String sexo;

    @Column(length = 20)
    private String telefone;

    @Column(name = "nome_mae", length = 100)
    private String nomeMae;

    @Column(name = "nome_responsavel", length = 100)
    private String nomeResponsavel;

    private boolean prioritario;

}
