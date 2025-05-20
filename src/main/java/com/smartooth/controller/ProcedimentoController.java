package com.smartooth.controller;

import com.smartooth.dto.ProcedimentoDTO;
import com.smartooth.services.ProcedimentoService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/procedimentos")
@RequiredArgsConstructor
public class ProcedimentoController {

    private final ProcedimentoService procedimentoService;

    @PostMapping
    public ResponseEntity<String> createProcedimento(@Valid @RequestBody ProcedimentoDTO procedimentoDTO) {
        return procedimentoService.createProcedimento(procedimentoDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ProcedimentoDTO>> getProcedimentoById(@PathVariable Long id) {
        ProcedimentoDTO procedimentoDTO = procedimentoService.getProcedimentoById(id).getBody();

        EntityModel<ProcedimentoDTO> procedimentoModel = EntityModel.of(procedimentoDTO,
                linkTo(methodOn(ProcedimentoController.class).getProcedimentoById(id)).withSelfRel(),
                linkTo(methodOn(ProcedimentoController.class).getAllProcedimentos()).withRel("procedimentos"));

        return ResponseEntity.ok(procedimentoModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateProcedimento(@PathVariable Long id,
                                                     @Valid @RequestBody ProcedimentoDTO procedimentoDTO) {
        return procedimentoService.updateProcedimento(id, procedimentoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProcedimento(@PathVariable Long id) {
        return procedimentoService.deleteProcedimento(id);
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ProcedimentoDTO>>> getAllProcedimentos() {
        List<EntityModel<ProcedimentoDTO>> procedimentosModels = procedimentoService.getAllProcedimentos().stream()
                .map(procedimentoDTO -> EntityModel.of(procedimentoDTO,
                        linkTo(methodOn(ProcedimentoController.class).getProcedimentoById(procedimentoDTO.getProcedimentoId())).withSelfRel()))
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(procedimentosModels));
    }
}

