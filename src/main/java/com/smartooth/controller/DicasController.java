package com.smartooth.controller;

import com.smartooth.dto.DicasDTO;
import com.smartooth.services.DicasService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dicas")
@RequiredArgsConstructor
public class DicasController {

    private final DicasService dicasService;

    @PostMapping
    public ResponseEntity<DicasDTO> createDica(@Valid @RequestBody DicasDTO dicasDTO) {
        DicasDTO savedDica = dicasService.saveDica(dicasDTO);
        EntityModel<DicasDTO> dicaModel = EntityModel.of(savedDica,
                linkTo(methodOn(DicasController.class).getDicaById(savedDica.getDicaId())).withSelfRel(),
                linkTo(methodOn(DicasController.class).getAllDicas()).withRel("dicas"));
        return ResponseEntity.created(dicaModel.getRequiredLink("self").toUri()).body(savedDica);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<DicasDTO>> getDicaById(@PathVariable Long id) {
        DicasDTO dicasDTO = dicasService.getDicaById(id);

        if (dicasDTO == null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<DicasDTO> dicaModel = EntityModel.of(dicasDTO,
                linkTo(methodOn(DicasController.class).getDicaById(id)).withSelfRel(),
                linkTo(methodOn(DicasController.class).getAllDicas()).withRel("dicas"));

        return ResponseEntity.ok(dicaModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<DicasDTO> updateDica(@PathVariable Long id,
                                               @Valid @RequestBody DicasDTO dicasDTO) {
        DicasDTO updatedDica = dicasService.updateDica(id, dicasDTO);

        if (updatedDica == null) {
            return ResponseEntity.notFound().build();
        }

        EntityModel<DicasDTO> dicaModel = EntityModel.of(updatedDica,
                linkTo(methodOn(DicasController.class).getDicaById(updatedDica.getDicaId())).withSelfRel(),
                linkTo(methodOn(DicasController.class).getAllDicas()).withRel("dicas"));


        return ResponseEntity.ok(updatedDica);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDica(@PathVariable Long id) {
        dicasService.deleteDica(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<DicasDTO>>> getAllDicas() {
        List<EntityModel<DicasDTO>> dicasModels = new ArrayList<>();

        List<DicasDTO> dicasDTO = dicasService.getAllDicas();

        for (DicasDTO dicaDTO : dicasDTO) {
            EntityModel<DicasDTO> dicaModel = EntityModel.of(dicaDTO,
                    linkTo(methodOn(DicasController.class).getDicaById(dicaDTO.getDicaId())).withSelfRel());
            dicasModels.add(dicaModel);
        }

        return ResponseEntity.ok(CollectionModel.of(dicasModels));
    }
}
