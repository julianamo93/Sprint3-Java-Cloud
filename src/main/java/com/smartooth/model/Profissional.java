package com.smartooth.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_profissional")
public class Profissional {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profissional_seq")
    @SequenceGenerator(name = "profissional_seq", sequenceName = "seq_profissional_id", allocationSize = 1)
    @Column(name = "profissional_id")
    private Long profissionalId;
    private String nome;
    private String especialidade;
    private String experiencia;
    private String contato;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_registro")
    private Date dataRegistro;

}

