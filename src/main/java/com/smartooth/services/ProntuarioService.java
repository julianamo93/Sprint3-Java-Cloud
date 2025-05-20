package com.smartooth.services;

import com.smartooth.exceptions.ResourceNotFoundException;
import com.smartooth.model.Prontuario;
import com.smartooth.repository.ProntuarioRepository;
import com.smartooth.dto.ProntuarioDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProntuarioService {

    private final ProntuarioRepository prontuarioRepository;

    @Transactional
    public ResponseEntity<ProntuarioDTO> save(ProntuarioDTO prontuarioDTO) {
        Prontuario prontuario = convertToEntity(prontuarioDTO);
        prontuarioRepository.save(prontuario);
        return ResponseEntity.status(201).body(convertToDTO(prontuario));
    }

    public ResponseEntity<ProntuarioDTO> getProntuarioById(Long id) {
        Prontuario prontuario = prontuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prontuário não encontrado com o ID: " + id));
        return ResponseEntity.ok(convertToDTO(prontuario));
    }

    @Transactional
    public ResponseEntity<ProntuarioDTO> update(Long id, ProntuarioDTO prontuarioDTO) {
        Prontuario prontuario = prontuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prontuário não encontrado com o ID: " + id));

        prontuario.setPrescricao(prontuarioDTO.getPrescricao());
        prontuario.setObservacoes(prontuarioDTO.getObservacoes());
        prontuario.setDataRegistro(prontuarioDTO.getDataRegistro());

        prontuarioRepository.save(prontuario);
        return ResponseEntity.ok(convertToDTO(prontuario));
    }

    @Transactional
    public ResponseEntity<Void> delete(Long id) {
        prontuarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Prontuário não encontrado com o ID: " + id));
        prontuarioRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<ProntuarioDTO>> getAllProntuarios() {
        List<ProntuarioDTO> prontuariosDTO = prontuarioRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(prontuariosDTO);
    }

    private Prontuario convertToEntity(ProntuarioDTO dto) {
        return Prontuario.builder()
                .prescricao(dto.getPrescricao())
                .observacoes(dto.getObservacoes())
                .dataRegistro(dto.getDataRegistro())
                .build();
    }

    private ProntuarioDTO convertToDTO(Prontuario entity) {
        return ProntuarioDTO.builder()
                .prontuarioId(entity.getProntuarioId())
                .prescricao(entity.getPrescricao())
                .observacoes(entity.getObservacoes())
                .dataRegistro(entity.getDataRegistro())
                .build();
    }
}

