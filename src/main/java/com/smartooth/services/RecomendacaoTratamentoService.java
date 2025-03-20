package com.smartooth.services;

import com.smartooth.exceptions.ResourceNotFoundException;
import com.smartooth.repository.PlanoRepository;
import com.smartooth.repository.RecomendacaoTratamentoRepository;
import com.smartooth.dto.RecomendacaoTratamentoDTO;
import com.smartooth.model.RecomendacaoTratamento;
import com.smartooth.model.Plano;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecomendacaoTratamentoService {

    private final RecomendacaoTratamentoRepository recomendacaoTratamentoRepository;
    private final PlanoRepository planoRepository;

    public ResponseEntity<List<RecomendacaoTratamentoDTO>> getAllRecomendacoes() {
        List<RecomendacaoTratamentoDTO> recomendacoesDTO = recomendacaoTratamentoRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(recomendacoesDTO);
    }

    public ResponseEntity<RecomendacaoTratamentoDTO> getRecomendacaoById(Long id) {
        RecomendacaoTratamento recomendacaoTratamento = recomendacaoTratamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recomendação não encontrada com o ID: " + id));
        return ResponseEntity.ok(convertToDTO(recomendacaoTratamento));
    }

    @Transactional
    public ResponseEntity<String> save(RecomendacaoTratamentoDTO recomendacaoTratamentoDTO) {
        Plano plano = planoRepository.findById(recomendacaoTratamentoDTO.getPlanoId())
                .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado com o ID: " + recomendacaoTratamentoDTO.getPlanoId()));

        RecomendacaoTratamento recomendacaoTratamento = convertToEntity(recomendacaoTratamentoDTO, plano);

        recomendacaoTratamentoRepository.save(recomendacaoTratamento);
        return ResponseEntity.status(201).body("Recomendação criada com sucesso.");
    }

    @Transactional
    public ResponseEntity<String> update(Long id, RecomendacaoTratamentoDTO recomendacaoTratamentoDTO) {
        RecomendacaoTratamento recomendacaoTratamento = recomendacaoTratamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recomendação não encontrada com o ID: " + id));

        Plano plano = planoRepository.findById(recomendacaoTratamentoDTO.getPlanoId())
                .orElseThrow(() -> new ResourceNotFoundException("Plano não encontrado com o ID: " + recomendacaoTratamentoDTO.getPlanoId()));

        recomendacaoTratamento.setDataRec(recomendacaoTratamentoDTO.getDataRec());
        recomendacaoTratamento.setPlano(plano);

        recomendacaoTratamentoRepository.save(recomendacaoTratamento);
        return ResponseEntity.ok("Recomendação atualizada com sucesso.");
    }

    @Transactional
    public ResponseEntity<String> delete(Long id) {
        recomendacaoTratamentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Recomendação não encontrada com o ID: " + id));

        recomendacaoTratamentoRepository.deleteById(id);
        return ResponseEntity.ok("Recomendação deletada com sucesso.");
    }

    // Método de conversão de DTO para entidade
    private RecomendacaoTratamento convertToEntity(RecomendacaoTratamentoDTO dto, Plano plano) {
        RecomendacaoTratamento entity = new RecomendacaoTratamento();
        entity.setDataRec(dto.getDataRec());
        entity.setPlano(plano);
        return entity;
    }

    private RecomendacaoTratamentoDTO convertToDTO(RecomendacaoTratamento entity) {
        RecomendacaoTratamentoDTO dto = new RecomendacaoTratamentoDTO();
        dto.setRecomendacaoId(entity.getRecomendacaoId());
        dto.setDataRec(entity.getDataRec());
        dto.setPlanoId(entity.getPlano().getPlanoId());
        return dto;
    }
}
