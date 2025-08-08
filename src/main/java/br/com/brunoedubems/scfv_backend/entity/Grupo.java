package br.com.brunoedubems.scfv_backend.entity;

import jakarta.persistence.*;


@Entity
@Table(name = "grupo")
public class Grupo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String tecnico;

    @Column(name = "faixa_etaria")
    private String faixaEtaria;
}
