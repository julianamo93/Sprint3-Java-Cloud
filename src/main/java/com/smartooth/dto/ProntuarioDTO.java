package com.smartooth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class ProntuarioDTO {

    private Long prontuarioId;

    @NotBlank(message = "A prescrição não pode estar vazia.")
    @Size(max = 255, message = "A prescrição deve ter no máximo 255 caracteres.")
    private String prescricao;

    @Size(max = 255, message = "As observações devem ter no máximo 255 caracteres.")
    private String observacoes;

    private Date dataRegistro;
}


