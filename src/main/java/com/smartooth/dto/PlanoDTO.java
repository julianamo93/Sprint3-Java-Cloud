package com.smartooth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;



@Data
public class PlanoDTO {


    private Long planoId;

    @NotBlank(message = "A descrição é obrigatória.")
    private String descricao;

    @NotBlank(message = "O tipo do plano é obrigatório.")
    private String tipoPlano;

    @NotBlank(message = "A marca do plano é obrigatória.")
    private String marcaPlano;

    @NotBlank(message = "O tipo de pagamento é obrigatório.")
    private String tipoPagamento;


    @NotNull(message = "O ID do usuário paciente é obrigatório.")
    private Long usuarioPacienteId;
}



