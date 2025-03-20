package com.smartooth.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import lombok.Data;

import java.util.Date;

@Data
public class RecomendacaoTratamentoDTO {

    private Long recomendacaoId;

    @NotNull(message = "A data da recomendação não pode ser nula.")
    @PastOrPresent(message = "A data da recomendação deve ser uma data passada ou a data presente.")
    private Date dataRec;

    @NotNull(message = "O ID do plano não pode ser nulo.")
    private Long planoId;
}

