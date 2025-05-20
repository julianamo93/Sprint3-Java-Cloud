package com.smartooth.controller;

import com.smartooth.dto.AtendimentoDTO;
import com.smartooth.services.AtendimentoService;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/atendimentos")
@RequiredArgsConstructor
public class AtendimentoController {

    private final AtendimentoService atendimentoService;

    @PostMapping
    public ResponseEntity<AtendimentoDTO> createAtendimento(@Valid @RequestBody AtendimentoDTO atendimentoDTO) {
        AtendimentoDTO savedAtendimento = atendimentoService.saveAtendimento(atendimentoDTO);

        var selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AtendimentoController.class)
                .getAtendimentoById(savedAtendimento.getAtendimentoId())).withSelfRel();
        var allAtendimentosLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AtendimentoController.class)
                .getAllAtendimentos()).withRel("atendimentos");


        return ResponseEntity.created(selfLink.toUri()).body(savedAtendimento);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AtendimentoDTO> getAtendimentoById(@PathVariable Long id) {
        AtendimentoDTO atendimentoDTO = atendimentoService.getAtendimentoById(id);

        if (atendimentoDTO == null) {
            return ResponseEntity.notFound().build();
        }

        var selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AtendimentoController.class)
                .getAtendimentoById(id)).withSelfRel();
        var allAtendimentosLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AtendimentoController.class)
                .getAllAtendimentos()).withRel("atendimentos");

        return ResponseEntity.ok(atendimentoDTO);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AtendimentoDTO> updateAtendimento(@PathVariable Long id,
                                                            @Valid @RequestBody AtendimentoDTO atendimentoDTO) {
        AtendimentoDTO updatedAtendimento = atendimentoService.updateAtendimento(id, atendimentoDTO);

        if (updatedAtendimento == null) {
            return ResponseEntity.notFound().build();
        }


        var selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AtendimentoController.class)
                .getAtendimentoById(updatedAtendimento.getAtendimentoId())).withSelfRel();
        var allAtendimentosLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AtendimentoController.class)
                .getAllAtendimentos()).withRel("atendimentos");

        return ResponseEntity.ok(updatedAtendimento);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAtendimento(@PathVariable Long id) {
        atendimentoService.deleteAtendimento(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<CollectionModel<AtendimentoDTO>> getAllAtendimentos() {
        var atendimentosDTO = atendimentoService.getAllAtendimentos();

        for (AtendimentoDTO atendimentoDTO : atendimentosDTO) {
            var selfLink = WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(AtendimentoController.class)
                    .getAtendimentoById(atendimentoDTO.getAtendimentoId())).withSelfRel();
        }

        return ResponseEntity.ok(CollectionModel.of(atendimentosDTO));
    }
}
