package com.smartooth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.hateoas.RepresentationModel;

import java.util.Date;

@Data
@EqualsAndHashCode(callSuper = false)
public class UsuarioPacienteDTO extends RepresentationModel<UsuarioPacienteDTO> {

    private Long usuarioPacienteId;

    @NotBlank(message = "O nome é obrigatório.")
    private String nome;

    @NotBlank(message = "O sobrenome é obrigatório.")
    private String sobrenome;

    @Email(message = "O email deve ser válido.")
    @NotBlank(message = "O email é obrigatório.")
    private String email;

    @NotNull(message = "A data de nascimento é obrigatória.")
    private Date dataNasc;

    @NotBlank(message = "O gênero é obrigatório.")
    private String genero;

    @NotBlank(message = "O CEP é obrigatório.")
    @Size(min = 8, max = 9, message = "O CEP deve ter 8 ou 9 caracteres.")
    private String cep;

    @NotBlank(message = "O logradouro é obrigatório.")
    private String logradouro;

    @NotBlank(message = "O número é obrigatório.")
    private String numero;

    private String complemento;

    @NotBlank(message = "O bairro é obrigatório.")
    private String bairro;

    @NotBlank(message = "A cidade é obrigatória.")
    private String cidade;

    @NotBlank(message = "A UF é obrigatória.")
    private String uf;

    @NotBlank(message = "O contato é obrigatório.")
    private String contato;

    private Double descontos;

    @NotBlank(message = "A senha é obrigatória.")
    private String senha;
}


