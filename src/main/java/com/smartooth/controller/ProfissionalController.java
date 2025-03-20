package com.smartooth.controller;

import com.smartooth.dto.ProfissionalDTO;
import com.smartooth.services.ProfissionalService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@RestController
@RequestMapping("/profissional")
@RequiredArgsConstructor
public class ProfissionalController {

    private final ProfissionalService profissionalService;

    @PostMapping
    public ResponseEntity<ProfissionalDTO> createProfissional(@Valid @RequestBody ProfissionalDTO profissionalDTO) {
        return profissionalService.createProfissional(profissionalDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<ProfissionalDTO>> getProfissionalById(@PathVariable Long id) {
        ProfissionalDTO profissional = profissionalService.getProfissionalById(id).getBody();

        EntityModel<ProfissionalDTO> profissionalModel = EntityModel.of(profissional,
                linkTo(methodOn(ProfissionalController.class).getProfissionalById(id)).withSelfRel(),
                linkTo(methodOn(ProfissionalController.class).getAllProfissionais()).withRel("profissionais"));

        return ResponseEntity.ok(profissionalModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfissionalDTO> updateProfissional(@PathVariable Long id,
                                                              @Valid @RequestBody ProfissionalDTO profissionalDTO) {
        return profissionalService.updateProfissional(id, profissionalDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProfissional(@PathVariable Long id) {
        profissionalService.deleteProfissional(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<ProfissionalDTO>>> getAllProfissionais() {
        List<EntityModel<ProfissionalDTO>> profissionaisModels = new ArrayList<>();

        List<ProfissionalDTO> profissionais = profissionalService.getAllProfissionais().getBody();

        for (ProfissionalDTO profissional : profissionais) {
            EntityModel<ProfissionalDTO> profissionalModel = EntityModel.of(profissional,
                    linkTo(methodOn(ProfissionalController.class).getProfissionalById(profissional.getProfissionalId())).withSelfRel());
            profissionaisModels.add(profissionalModel);
        }

        return ResponseEntity.ok(CollectionModel.of(profissionaisModels));
    }
}




