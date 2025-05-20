package com.smartooth.services;

import com.smartooth.repository.DicasRepository;
import com.smartooth.repository.ProntuarioRepository;
import com.smartooth.repository.UsuarioPacienteRepository;
import com.smartooth.dto.DicasDTO;
import com.smartooth.model.Dicas;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DicasService {

    private final DicasRepository dicasRepository;
    private final ProntuarioRepository prontuarioRepository;
    private final UsuarioPacienteRepository usuarioPacienteRepository;

    public DicasService(DicasRepository dicasRepository, ProntuarioRepository prontuarioRepository, UsuarioPacienteRepository usuarioPacienteRepository) {
        this.dicasRepository = dicasRepository;
        this.prontuarioRepository = prontuarioRepository;
        this.usuarioPacienteRepository = usuarioPacienteRepository;
    }

    public List<DicasDTO> getAllDicas() {
        return dicasRepository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    public DicasDTO getDicaById(Long id) {
        return dicasRepository.findById(id)
                .map(this::toDTO)
                .orElse(null);
    }

    @Transactional
    public DicasDTO saveDica(DicasDTO dicasDTO) {
        Dicas dica = toEntity(dicasDTO);
        return toDTO(dicasRepository.save(dica));
    }

    @Transactional
    public DicasDTO updateDica(Long id, DicasDTO dicasDTO) {
        Optional<Dicas> existingDica = dicasRepository.findById(id);
        if (existingDica.isPresent()) {
            Dicas dica = toEntity(dicasDTO);
            dica.setDicaId(id);
            return toDTO(dicasRepository.save(dica));
        }
        return null;
    }

    @Transactional
    public void deleteDica(Long id) {
        dicasRepository.deleteById(id);
    }

    private DicasDTO toDTO(Dicas dicas) {
        DicasDTO dto = new DicasDTO();
        dto.setDicaId(dicas.getDicaId());
        dto.setDescricao(dicas.getDescricao());
        dto.setProntuarioId(dicas.getProntuario().getProntuarioId());
        dto.setUsuarioPacienteId(dicas.getUsuarioPaciente().getUsuarioPacienteId());
        return dto;
    }

    private Dicas toEntity(DicasDTO dicasDTO) {
        Dicas dicas = new Dicas();
        dicas.setDicaId(dicasDTO.getDicaId());
        dicas.setDescricao(dicasDTO.getDescricao());
        dicas.setProntuario(prontuarioRepository.findById(dicasDTO.getProntuarioId()).orElseThrow());
        dicas.setUsuarioPaciente(usuarioPacienteRepository.findById(dicasDTO.getUsuarioPacienteId()).orElseThrow());
        return dicas;
    }
}
