package com.smartooth.services;

import com.smartooth.exceptions.ResourceNotFoundException;
import com.smartooth.repository.ProcedimentoRepository;
import com.smartooth.repository.ProntuarioRepository;
import com.smartooth.repository.SistemaPontosRepository;
import com.smartooth.repository.UsuarioPacienteRepository;
import com.smartooth.dto.ProcedimentoDTO;
import com.smartooth.model.Procedimento;
import com.smartooth.model.Prontuario;
import com.smartooth.model.SistemaPontos;
import com.smartooth.model.UsuarioPaciente;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProcedimentoService {

    private final ProcedimentoRepository procedimentoRepository;
    private final ProntuarioRepository prontuarioRepository;
    private final SistemaPontosRepository sistemaPontosRepository;
    private final UsuarioPacienteRepository usuarioPacienteRepository;

    public List<ProcedimentoDTO> getAllProcedimentos() {
        List<Procedimento> procedimentos = procedimentoRepository.findAll();
        return procedimentos.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public ResponseEntity<ProcedimentoDTO> getProcedimentoById(Long id) {
        Procedimento procedimento = procedimentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Procedimento não encontrado com o ID: " + id));
        return ResponseEntity.ok(convertToDTO(procedimento));
    }

    @Transactional
    public ResponseEntity<String> createProcedimento(ProcedimentoDTO procedimentoDTO) {
        Prontuario prontuario = prontuarioRepository.findById(procedimentoDTO.getProntuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Prontuário não encontrado com o ID: " + procedimentoDTO.getProntuarioId()));

        SistemaPontos sistemaPontos = sistemaPontosRepository.findById(procedimentoDTO.getSistemaPontosId())
                .orElseThrow(() -> new ResourceNotFoundException("Sistema de Pontos não encontrado com o ID: " + procedimentoDTO.getSistemaPontosId()));

        UsuarioPaciente usuarioPaciente = usuarioPacienteRepository.findById(procedimentoDTO.getUsuarioPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário Paciente não encontrado com o ID: " + procedimentoDTO.getUsuarioPacienteId()));

        Procedimento procedimento = convertToEntity(procedimentoDTO, prontuario, sistemaPontos, usuarioPaciente);
        procedimentoRepository.save(procedimento);
        return ResponseEntity.status(201).body("Procedimento criado com sucesso.");
    }

    @Transactional
    public ResponseEntity<String> updateProcedimento(Long id, ProcedimentoDTO procedimentoDTO) {
        Procedimento procedimento = procedimentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Procedimento não encontrado com o ID: " + id));

        Prontuario prontuario = prontuarioRepository.findById(procedimentoDTO.getProntuarioId())
                .orElseThrow(() -> new ResourceNotFoundException("Prontuário não encontrado com o ID: " + procedimentoDTO.getProntuarioId()));

        SistemaPontos sistemaPontos = sistemaPontosRepository.findById(procedimentoDTO.getSistemaPontosId())
                .orElseThrow(() -> new ResourceNotFoundException("Sistema de Pontos não encontrado com o ID: " + procedimentoDTO.getSistemaPontosId()));

        UsuarioPaciente usuarioPaciente = usuarioPacienteRepository.findById(procedimentoDTO.getUsuarioPacienteId())
                .orElseThrow(() -> new ResourceNotFoundException("Usuário Paciente não encontrado com o ID: " + procedimentoDTO.getUsuarioPacienteId()));

        procedimento.setNomeProcedimento(procedimentoDTO.getNomeProcedimento());
        procedimento.setDescricao(procedimentoDTO.getDescricao());
        procedimento.setInclusaoPlano(procedimentoDTO.getInclusaoPlano());
        procedimento.setProntuario(prontuario);
        procedimento.setSistemaPontos(sistemaPontos);
        procedimento.setUsuarioPaciente(usuarioPaciente);

        procedimentoRepository.save(procedimento);
        return ResponseEntity.ok("Procedimento atualizado com sucesso.");
    }

    @Transactional
    public ResponseEntity<Void> deleteProcedimento(Long id) {
        procedimentoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Procedimento não encontrado com o ID: " + id));

        procedimentoRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }


    private Procedimento convertToEntity(ProcedimentoDTO dto, Prontuario prontuario, SistemaPontos sistemaPontos, UsuarioPaciente usuarioPaciente) {
        Procedimento entity = new Procedimento();
        entity.setNomeProcedimento(dto.getNomeProcedimento());
        entity.setDescricao(dto.getDescricao());
        entity.setInclusaoPlano(dto.getInclusaoPlano());
        entity.setProntuario(prontuario);
        entity.setSistemaPontos(sistemaPontos);
        entity.setUsuarioPaciente(usuarioPaciente);
        return entity;
    }

    private ProcedimentoDTO convertToDTO(Procedimento entity) {
        ProcedimentoDTO dto = new ProcedimentoDTO();
        dto.setProcedimentoId(entity.getProcedimentoId());
        dto.setNomeProcedimento(entity.getNomeProcedimento());
        dto.setDescricao(entity.getDescricao());
        dto.setInclusaoPlano(entity.getInclusaoPlano());
        dto.setProntuarioId(entity.getProntuario().getProntuarioId());
        dto.setSistemaPontosId(entity.getSistemaPontos().getSistemaPontosId());
        dto.setUsuarioPacienteId(entity.getUsuarioPaciente().getUsuarioPacienteId());
        return dto;
    }
}

