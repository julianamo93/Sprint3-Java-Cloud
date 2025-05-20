package com.smartooth.services;

import com.smartooth.exceptions.ResourceNotFoundException;
import com.smartooth.repository.ProfissionalRepository;
import com.smartooth.dto.ProfissionalDTO;
import com.smartooth.model.Profissional;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfissionalService {

    private final ProfissionalRepository profissionalRepository;

    private ProfissionalDTO convertToDTO(Profissional profissional) {
        ProfissionalDTO dto = new ProfissionalDTO();
        dto.setProfissionalId(profissional.getProfissionalId());
        dto.setNome(profissional.getNome());
        dto.setEspecialidade(profissional.getEspecialidade());
        dto.setExperiencia(profissional.getExperiencia());
        dto.setContato(profissional.getContato());
        dto.setDataRegistro(profissional.getDataRegistro());
        return dto;
    }

    @Transactional
    public ResponseEntity<ProfissionalDTO> createProfissional(ProfissionalDTO profissionalDTO) {
        Profissional profissional = new Profissional();
        profissional.setNome(profissionalDTO.getNome());
        profissional.setEspecialidade(profissionalDTO.getEspecialidade());
        profissional.setExperiencia(profissionalDTO.getExperiencia());
        profissional.setContato(profissionalDTO.getContato());
        profissional.setDataRegistro(profissionalDTO.getDataRegistro());

        profissionalRepository.save(profissional);

        ProfissionalDTO dto = convertToDTO(profissional);
        return ResponseEntity.status(201).body(dto);
    }

    @Transactional
    public ResponseEntity<ProfissionalDTO> updateProfissional(Long id, ProfissionalDTO profissionalDTO) {
        Profissional profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado com o ID: " + id));

        profissional.setNome(profissionalDTO.getNome());
        profissional.setEspecialidade(profissionalDTO.getEspecialidade());
        profissional.setExperiencia(profissionalDTO.getExperiencia());
        profissional.setContato(profissionalDTO.getContato());
        profissional.setDataRegistro(profissionalDTO.getDataRegistro());

        profissionalRepository.save(profissional);

        ProfissionalDTO dto = convertToDTO(profissional);
        return ResponseEntity.ok(dto);
    }

    public ResponseEntity<List<ProfissionalDTO>> getAllProfissionais() {
        List<ProfissionalDTO> profissionaisDTO = profissionalRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(profissionaisDTO);
    }

    public ResponseEntity<ProfissionalDTO> getProfissionalById(Long id) {
        Profissional profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado com o ID: " + id));

        ProfissionalDTO profissionalDTO = convertToDTO(profissional);
        return ResponseEntity.ok(profissionalDTO);
    }

    @Transactional
    public ResponseEntity<Void> deleteProfissional(Long id) {
        Profissional profissional = profissionalRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Profissional não encontrado com o ID: " + id));

        profissionalRepository.delete(profissional);
        return ResponseEntity.noContent().build();
    }
}


