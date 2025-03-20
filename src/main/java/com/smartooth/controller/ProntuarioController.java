package com.smartooth.controller;


import com.smartooth.dto.ProntuarioDTO;
import com.smartooth.services.ProntuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/prontuarios")
public class ProntuarioController {

    private final ProntuarioService prontuarioService;

    @Autowired
    public ProntuarioController(ProntuarioService prontuarioService) {
        this.prontuarioService = prontuarioService;
    }


    @PostMapping
    public ResponseEntity<ProntuarioDTO> createProntuario(@Valid @RequestBody ProntuarioDTO prontuarioDTO) {
        return prontuarioService.save(prontuarioDTO);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ProntuarioDTO>> getProntuarioById(@PathVariable Long id) {
        ProntuarioDTO prontuario = prontuarioService.getProntuarioById(id).getBody();

        EntityModel<ProntuarioDTO> prontuarioModel = EntityModel.of(prontuario,
                linkTo(methodOn(ProntuarioController.class).getProntuarioById(id)).withSelfRel(),
                linkTo(methodOn(ProntuarioController.class).getAllProntuarios()).withRel("prontuarios"));

        return ResponseEntity.ok(prontuarioModel);
    }


    @PutMapping("/{id}")
    public ResponseEntity<ProntuarioDTO> updateProntuario(@PathVariable Long id,
                                                          @Valid @RequestBody ProntuarioDTO prontuarioDTO) {
        return prontuarioService.update(id, prontuarioDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProntuario(@PathVariable Long id) {
        prontuarioService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ProntuarioDTO>>> getAllProntuarios() {
        List<EntityModel<ProntuarioDTO>> prontuarioModels = new ArrayList<>();

        List<ProntuarioDTO> prontuarios = prontuarioService.getAllProntuarios().getBody();

        for (ProntuarioDTO prontuarioDTO : prontuarios) {
            EntityModel<ProntuarioDTO> prontuarioModel = EntityModel.of(prontuarioDTO,
                    linkTo(methodOn(ProntuarioController.class).getProntuarioById(prontuarioDTO.getProntuarioId())).withSelfRel());
            prontuarioModels.add(prontuarioModel);
        }

        return ResponseEntity.ok(CollectionModel.of(prontuarioModels));
    }
}
