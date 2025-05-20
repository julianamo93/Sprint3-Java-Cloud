package com.smartooth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.Date;

@Data
public class AtendimentoDTO {

    private Long atendimentoId;

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    @NotNull(message = "A data é obrigatória.")
    private Date data;

    @NotNull(message = "A hora é obrigatória.")
    private Date hora;

    @NotNull(message = "O status de inclusão no plano é obrigatório.")
    private char inclusoPlano;

    @NotNull(message = "O custo é obrigatório.")
    private Double custo;

    @NotNull(message = "O ID do usuário paciente é obrigatório.")
    private Long usuarioPacienteId;

    @NotNull(message = "O ID do profissional é obrigatório.")
    private Long profissionalId;

    @NotNull(message = "O ID da recomendação de tratamento é obrigatório.")
    private Long recomendacaoTratId;
}


