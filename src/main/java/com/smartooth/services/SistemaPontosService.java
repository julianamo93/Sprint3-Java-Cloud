package com.smartooth.services;

import com.smartooth.exceptions.ResourceNotFoundException;
import com.smartooth.repository.PlanoRepository;
import com.smartooth.repository.SistemaPontosRepository;
import com.smartooth.dto.SistemaPontosDTO;
import com.smartooth.model.SistemaPontos;
import com.smartooth.model.Plano;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class SistemaPontosService {

    private final SistemaPontosRepository sistemaPontosRepository;
    private final PlanoRepository planoRepository;

    public SistemaPontosService(SistemaPontosRepository sistemaPontosRepository, PlanoRepository planoRepository) {
        this.sistemaPontosRepository = sistemaPontosRepository;
        this.planoRepository = planoRepository;
    }

    @Transactional
    public ResponseEntity<SistemaPontosDTO> save(SistemaPontosDTO sistemaPontosDTO) {
        Plano plano = planoRepository.findById(sistemaPontosDTO.getPlanoId())
                .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado com o ID: " + sistemaPontosDTO.getPlanoId()));

        SistemaPontos sistemaPontos = convertToEntity(sistemaPontosDTO, plano);
        sistemaPontosRepository.save(sistemaPontos);

        return ResponseEntity.status(201).body(convertToDTO(sistemaPontos));  // Retorna o DTO com status 201
    }

    public ResponseEntity<SistemaPontosDTO> getSistemaPontosById(Long id) {
        SistemaPontos sistemaPontos = sistemaPontosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sistema de pontos não encontrado com o ID: " + id));

        return ResponseEntity.ok(convertToDTO(sistemaPontos));
    }

    @Transactional
    public ResponseEntity<SistemaPontosDTO> update(Long id, SistemaPontosDTO sistemaPontosDTO) {
        SistemaPontos sistemaPontos = sistemaPontosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sistema de pontos não encontrado com o ID: " + id));

        Plano plano = planoRepository.findById(sistemaPontosDTO.getPlanoId())
                .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado com o ID: " + sistemaPontosDTO.getPlanoId()));

        sistemaPontos.setTotalPontos(sistemaPontosDTO.getTotalPontos());
        sistemaPontos.setTipoPontos(sistemaPontosDTO.getTipoPontos());
        sistemaPontos.setPlano(plano);

        sistemaPontosRepository.save(sistemaPontos);

        return ResponseEntity.ok(convertToDTO(sistemaPontos));
    }

    @Transactional
    public ResponseEntity<Void> delete(Long id) {
        sistemaPontosRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Sistema de pontos não encontrado com o ID: " + id));

        sistemaPontosRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    public ResponseEntity<List<SistemaPontosDTO>> getAllSistemaPontos() {
        List<SistemaPontosDTO> sistemaPontosDTOs = sistemaPontosRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());

        return ResponseEntity.ok(sistemaPontosDTOs);
    }

    private SistemaPontos convertToEntity(SistemaPontosDTO dto, Plano plano) {
        SistemaPontos entity = new SistemaPontos();
        entity.setTotalPontos(dto.getTotalPontos());
        entity.setTipoPontos(dto.getTipoPontos());
        entity.setPlano(plano);
        return entity;
    }

    private SistemaPontosDTO convertToDTO(SistemaPontos entity) {
        SistemaPontosDTO dto = new SistemaPontosDTO();
        dto.setSistemaPontosId(entity.getSistemaPontosId());
        dto.setTotalPontos(entity.getTotalPontos());
        dto.setTipoPontos(entity.getTipoPontos());
        dto.setPlanoId(entity.getPlano().getPlanoId());
        return dto;
    }
}

