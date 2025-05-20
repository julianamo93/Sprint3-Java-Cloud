package com.smartooth.controller;

import com.smartooth.dto.SistemaPontosDTO;
import com.smartooth.services.SistemaPontosService;
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
@RequestMapping("/sistemapontos")
public class SistemaPontosController {

    private final SistemaPontosService sistemaPontosService;

    @Autowired
    public SistemaPontosController(SistemaPontosService sistemaPontosService) {
        this.sistemaPontosService = sistemaPontosService;
    }


    @PostMapping
    public ResponseEntity<SistemaPontosDTO> createSistemaPontos(@Valid @RequestBody SistemaPontosDTO sistemaPontosDTO) {
        return sistemaPontosService.save(sistemaPontosDTO);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<SistemaPontosDTO>> getSistemaPontosById(@PathVariable Long id) {
        SistemaPontosDTO sistemaPontos = sistemaPontosService.getSistemaPontosById(id).getBody();

        EntityModel<SistemaPontosDTO> sistemaPontosModel = EntityModel.of(sistemaPontos,
                linkTo(methodOn(SistemaPontosController.class).getSistemaPontosById(id)).withSelfRel(),
                linkTo(methodOn(SistemaPontosController.class).getAllSistemaPontos()).withRel("sistemapontos"));

        return ResponseEntity.ok(sistemaPontosModel);
    }


    @PutMapping("/{id}")
    public ResponseEntity<SistemaPontosDTO> updateSistemaPontos(@PathVariable Long id,
                                                                @Valid @RequestBody SistemaPontosDTO sistemaPontosDTO) {
        return sistemaPontosService.update(id, sistemaPontosDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSistemaPontos(@PathVariable Long id) {
        sistemaPontosService.delete(id);
        return ResponseEntity.noContent().build();
    }


    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<SistemaPontosDTO>>> getAllSistemaPontos() {
        List<EntityModel<SistemaPontosDTO>> sistemaPontosModels = new ArrayList<>();

        List<SistemaPontosDTO> sistemaPontos = sistemaPontosService.getAllSistemaPontos().getBody();

        for (SistemaPontosDTO sistemaPontosDTO : sistemaPontos) {
            EntityModel<SistemaPontosDTO> sistemaPontosModel = EntityModel.of(sistemaPontosDTO,
                    linkTo(methodOn(SistemaPontosController.class).getSistemaPontosById(sistemaPontosDTO.getSistemaPontosId())).withSelfRel());
            sistemaPontosModels.add(sistemaPontosModel);
        }

        return ResponseEntity.ok(CollectionModel.of(sistemaPontosModels));
    }
}

