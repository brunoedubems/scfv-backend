package br.com.brunoedubems.scfv_backend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "O nome é obrigatório")
    @Size(max = 100, message = "O nome deve ter no máximo 100 caracteres")
    @Column(nullable = false, length = 100)
    private String nome;

    @NotNull(message = "A data de nascimento é obrigatória")
    @Past(message = "A data de nascimento deve estar no passado")
    @Column(name = "data_nascimento", nullable = false)
    private LocalDate dataNascimento;

    @NotBlank(message = "O CPF é obrigatório")
    @Size(min = 11, max = 11, message = "O CPF deve ter exatamente 11 dígitos")
    @Pattern(regexp = "\\d{11}", message = "O CPF deve conter apenas números")
    @Column(unique = true, nullable = false, length = 11)
    private String cpf;

    @NotBlank(message = "O NIS é obrigatório")
    @Size(min = 11, max = 11, message = "O NIS deve ter exatamente 11 dígitos")
    @Pattern(regexp = "\\d{11}", message = "O NIS deve conter apenas números")
    @Column(unique = true, nullable = false, length = 11)
    private String nis;

    @Size(max = 20, message = "O RG deve ter no máximo 20 caracteres")
    @Column(length = 20)
    private String rg;

    @Size(max = 10, message = "O sexo deve ter no máximo 10 caracteres")
    @Column(length = 10)
    private String sexo;

    @Size(max = 20, message = "O telefone deve ter no máximo 20 caracteres")
    @Column(length = 20)
    private String telefone;

    @Size(max = 100, message = "O nome da mãe deve ter no máximo 100 caracteres")
    @Column(name = "nome_mae", length = 100)
    private String nomeMae;

    @Size(max = 100, message = "O nome do responsável deve ter no máximo 100 caracteres")
    @Column(name = "nome_responsavel", length = 100)
    private String nomeResponsavel;

    private boolean prioritario;

    @ManyToOne
    @JoinColumn(name = "group_id")
    private Grupo grupo;

}
