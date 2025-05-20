package com.smartooth.services;

import com.smartooth.repository.AtendimentoRepository;
import com.smartooth.repository.ProfissionalRepository;
import com.smartooth.repository.RecomendacaoTratamentoRepository;
import com.smartooth.repository.UsuarioPacienteRepository;
import com.smartooth.dto.AtendimentoDTO;
import com.smartooth.model.Atendimento;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AtendimentoService {

    private final AtendimentoRepository atendimentoRepository;
    private final UsuarioPacienteRepository usuarioPacienteRepository;
    private final ProfissionalRepository profissionalRepository;
    private final RecomendacaoTratamentoRepository recomendacaoTratamentoRepository;

    public List<AtendimentoDTO> getAllAtendimentos() {
        return atendimentoRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public AtendimentoDTO getAtendimentoById(Long id) {
        return atendimentoRepository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    @Transactional
    public AtendimentoDTO saveAtendimento(AtendimentoDTO atendimentoDTO) {
        Atendimento atendimento = toEntity(atendimentoDTO);
        return toDTO(atendimentoRepository.save(atendimento));
    }

    @Transactional
    public AtendimentoDTO updateAtendimento(Long id, AtendimentoDTO atendimentoDTO) {
        Optional<Atendimento> existingAtendimento = atendimentoRepository.findById(id);
        if (existingAtendimento.isPresent()) {
            Atendimento atendimento = toEntity(atendimentoDTO);
            atendimento.setAtendimentoId(id);
            return toDTO(atendimentoRepository.save(atendimento));
        }
        return null;
    }

    @Transactional
    public void deleteAtendimento(Long id) {
        atendimentoRepository.deleteById(id);
    }

    private AtendimentoDTO toDTO(Atendimento atendimento) {
        AtendimentoDTO dto = new AtendimentoDTO();
        dto.setAtendimentoId(atendimento.getAtendimentoId());
        dto.setDescricao(atendimento.getDescricao());
        dto.setData(atendimento.getData());
        dto.setHora(atendimento.getHora());
        dto.setInclusoPlano(atendimento.getInclusoPlano());
        dto.setCusto(atendimento.getCusto());
        dto.setUsuarioPacienteId(atendimento.getUsuarioPaciente().getUsuarioPacienteId());
        dto.setProfissionalId(atendimento.getProfissional().getProfissionalId());
        dto.setRecomendacaoTratId(atendimento.getRecomendacaoTrat().getRecomendacaoId());
        return dto;
    }

    private Atendimento toEntity(AtendimentoDTO atendimentoDTO) {
        Atendimento atendimento = new Atendimento();
        atendimento.setAtendimentoId(atendimentoDTO.getAtendimentoId());
        atendimento.setDescricao(atendimentoDTO.getDescricao());
        atendimento.setData(atendimentoDTO.getData());
        atendimento.setHora(atendimentoDTO.getHora());
        atendimento.setInclusoPlano(atendimentoDTO.getInclusoPlano());
        atendimento.setCusto(atendimentoDTO.getCusto());
        atendimento.setUsuarioPaciente(usuarioPacienteRepository.findById(atendimentoDTO.getUsuarioPacienteId()).orElseThrow());
        atendimento.setProfissional(profissionalRepository.findById(atendimentoDTO.getProfissionalId()).orElseThrow());
        atendimento.setRecomendacaoTrat(recomendacaoTratamentoRepository.findById(atendimentoDTO.getRecomendacaoTratId()).orElseThrow());
        return atendimento;
    }
}
