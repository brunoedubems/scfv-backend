package br.com.brunoedubems.scfv_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

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

    @NotBlank
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nome;

    @NotNull
    @Past
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @NotBlank
    @Size(min = 11, max = 11)
    @Pattern(regexp = "\\d{11}")
    @Column(unique = true, nullable = false, length = 11)
    private String cpf;

    @NotBlank
    @Size(min = 11, max = 11)
    @Pattern(regexp = "\\d{11}")
    @Column(unique = true, nullable = false, length = 11)
    private String nis;

    @Size(max = 20)
    @Column(length = 20)
    private String rg;

    @Size(max = 10)
    @Column(length = 10)
    private String sexo;

    @Size(max = 20)
    @Column(length = 20)
    private String telefone;

    @Size(max = 100)
    @Column(name = "nome_mae", length = 100)
    private String nomeMae;

    @Size(max = 100)
    @Column(name = "nome_responsavel", length = 100)
    private String nomeResponsavel;

    @Column(nullable = false)
    private boolean prioritario;

    @ManyToOne(fetch = FetchType.LAZY)
    private Grupo grupo;

    @ElementCollection
    @CollectionTable(
            name = "usuario_situacoes",
            joinColumns = @JoinColumn(name = "usuario_id")
    )
    @Column(name = "situacao", length = 50)
    private Set<String> situacoes = new HashSet<>();

}
