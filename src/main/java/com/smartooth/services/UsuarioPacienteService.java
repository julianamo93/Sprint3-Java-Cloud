package com.smartooth.services;

import com.smartooth.exceptions.EmailAlreadyExistsException;
import com.smartooth.exceptions.ResourceNotFoundException;
import com.smartooth.repository.UsuarioPacienteRepository;
import com.smartooth.dto.UsuarioPacienteDTO;
import com.smartooth.model.UsuarioPaciente;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsuarioPacienteService {

    private final UsuarioPacienteRepository usuarioPacienteRepository;

    private UsuarioPacienteDTO convertToDTO(UsuarioPaciente usuarioPaciente) {
        UsuarioPacienteDTO dto = new UsuarioPacienteDTO();
        dto.setUsuarioPacienteId(usuarioPaciente.getUsuarioPacienteId());
        dto.setNome(usuarioPaciente.getNome());
        dto.setSobrenome(usuarioPaciente.getSobrenome());
        dto.setEmail(usuarioPaciente.getEmail());
        dto.setDataNasc(usuarioPaciente.getDataNasc());
        dto.setGenero(usuarioPaciente.getGenero());
        dto.setCep(usuarioPaciente.getCep());
        dto.setLogradouro(usuarioPaciente.getLogradouro());
        dto.setNumero(usuarioPaciente.getNumero());
        dto.setComplemento(usuarioPaciente.getComplemento());
        dto.setBairro(usuarioPaciente.getBairro());
        dto.setCidade(usuarioPaciente.getCidade());
        dto.setUf(usuarioPaciente.getUf());
        dto.setContato(usuarioPaciente.getContato());
        dto.setDescontos(usuarioPaciente.getDescontos());
        dto.setSenha(usuarioPaciente.getSenha());
        return dto;
    }

    @Transactional
    public ResponseEntity<UsuarioPacienteDTO> createUsuario(UsuarioPacienteDTO usuarioPacienteDTO) {

        if (usuarioPacienteRepository.findByEmail(usuarioPacienteDTO.getEmail()).isPresent()) {
            throw new EmailAlreadyExistsException("Já existe um usuário com este e-mail.");
        }


        UsuarioPaciente usuarioPaciente = new UsuarioPaciente();
        usuarioPaciente.setNome(usuarioPacienteDTO.getNome());
        usuarioPaciente.setSobrenome(usuarioPacienteDTO.getSobrenome());
        usuarioPaciente.setEmail(usuarioPacienteDTO.getEmail());
        usuarioPaciente.setDataNasc(usuarioPacienteDTO.getDataNasc());
        usuarioPaciente.setGenero(usuarioPacienteDTO.getGenero());
        usuarioPaciente.setCep(usuarioPacienteDTO.getCep());
        usuarioPaciente.setLogradouro(usuarioPacienteDTO.getLogradouro());
        usuarioPaciente.setNumero(usuarioPacienteDTO.getNumero());
        usuarioPaciente.setComplemento(usuarioPacienteDTO.getComplemento());
        usuarioPaciente.setBairro(usuarioPacienteDTO.getBairro());
        usuarioPaciente.setCidade(usuarioPacienteDTO.getCidade());
        usuarioPaciente.setUf(usuarioPacienteDTO.getUf());
        usuarioPaciente.setContato(usuarioPacienteDTO.getContato());
        usuarioPaciente.setDescontos(usuarioPacienteDTO.getDescontos());
        usuarioPaciente.setSenha(usuarioPacienteDTO.getSenha());

        usuarioPacienteRepository.save(usuarioPaciente);

        UsuarioPacienteDTO dto = convertToDTO(usuarioPaciente);
        return ResponseEntity.status(201).body(dto);
    }

    @Transactional
    public ResponseEntity<UsuarioPacienteDTO> updateUsuario(Long id, UsuarioPacienteDTO usuarioPacienteDTO) {
        UsuarioPaciente usuarioPaciente = usuarioPacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + id));

        usuarioPaciente.setNome(usuarioPacienteDTO.getNome());
        usuarioPaciente.setSobrenome(usuarioPacienteDTO.getSobrenome());
        usuarioPaciente.setEmail(usuarioPacienteDTO.getEmail());
        usuarioPaciente.setDataNasc(usuarioPacienteDTO.getDataNasc());
        usuarioPaciente.setGenero(usuarioPacienteDTO.getGenero());
        usuarioPaciente.setCep(usuarioPacienteDTO.getCep());
        usuarioPaciente.setLogradouro(usuarioPacienteDTO.getLogradouro());
        usuarioPaciente.setNumero(usuarioPacienteDTO.getNumero());
        usuarioPaciente.setComplemento(usuarioPacienteDTO.getComplemento());
        usuarioPaciente.setBairro(usuarioPacienteDTO.getBairro());
        usuarioPaciente.setCidade(usuarioPacienteDTO.getCidade());
        usuarioPaciente.setUf(usuarioPacienteDTO.getUf());
        usuarioPaciente.setContato(usuarioPacienteDTO.getContato());
        usuarioPaciente.setDescontos(usuarioPacienteDTO.getDescontos());
        usuarioPaciente.setSenha(usuarioPacienteDTO.getSenha());

        usuarioPacienteRepository.save(usuarioPaciente);


        UsuarioPacienteDTO dto = convertToDTO(usuarioPaciente);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<List<UsuarioPacienteDTO>> getAllUsuarios() {

        List<UsuarioPacienteDTO> usuariosDTO = usuarioPacienteRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(usuariosDTO);
    }

    public ResponseEntity<UsuarioPacienteDTO> getUsuarioPacienteById(Long id) {
        UsuarioPaciente usuarioPaciente = usuarioPacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + id));

        UsuarioPacienteDTO usuarioPacienteDTO = convertToDTO(usuarioPaciente);
        return ResponseEntity.ok(usuarioPacienteDTO);
    }


    @Transactional
    public ResponseEntity<Void> deleteUsuario(Long id) {
        UsuarioPaciente usuarioPaciente = usuarioPacienteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não encontrado com o ID: " + id));

        usuarioPacienteRepository.delete(usuarioPaciente);
        return ResponseEntity.noContent().build();
    }
}
