package com.smartooth.dto;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SistemaPontosDTO {

    private Long sistemaPontosId;

    @NotNull(message = "O total de pontos não pode ser nulo.")
    @DecimalMin(value = "0.0", inclusive = false, message = "O total de pontos deve ser maior que zero.")
    private Double totalPontos;

    @NotBlank(message = "O tipo de pontos não pode ser vazio.")
    @Size(max = 155, message = "O tipo de pontos não pode exceder 155 caracteres.")
    private String tipoPontos;

    @NotNull(message = "O ID do plano não pode ser nulo.")
    private Long planoId;
}

