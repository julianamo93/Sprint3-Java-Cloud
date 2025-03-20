package com.smartooth.dto;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import java.util.Date;

@Data
public class ProfissionalDTO {

    private Long profissionalId;

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "A especialidade é obrigatória.")
    private String especialidade;

    @NotBlank(message = "A experiência é obrigatória.")
    private String experiencia;

    @NotBlank(message = "O contato é obrigatório.")
    private String contato;

    @NotNull(message = "A data de registro é obrigatória.")
    private Date dataRegistro;
}
