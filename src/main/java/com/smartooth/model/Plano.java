package com.smartooth.model;

import jakarta.persistence.*;
import lombok.Data;


@Data
@Entity
@Table(name = "tb_plano")
public class Plano {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "plano_seq")
    @SequenceGenerator(name = "plano_seq", sequenceName = "seq_plano_id", allocationSize = 1)
    @Column(name = "plano_id")
    private Long planoId;

    @Column(name = "tipo_plano")
    private String tipoPlano;

    @Column(name = "descricao", nullable = false)
    private String descricao;

    @Column(name = "marca_plano")
    private String marcaPlano;

    @Column(name = "tipo_pagamento")
    private String tipoPagamento;

    @ManyToOne
    @JoinColumn(name = "usuario_paciente_id", nullable = false)
    private UsuarioPaciente usuarioPaciente;
}

