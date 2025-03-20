package com.smartooth.model;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "tb_recomendacao_trat")
public class RecomendacaoTratamento {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "recomendacao_seq")
    @SequenceGenerator(name = "recomendacao_seq", sequenceName = "seq_recomendacao_id", allocationSize = 1)
    @Column(name = "recomendacao_id")
    private Long recomendacaoId;

    @Temporal(TemporalType.DATE)
    @Column(name = "data_rec")
    private Date dataRec;

    @ManyToOne
    @JoinColumn(name = "plano_id", nullable = false)
    private Plano plano;

}

