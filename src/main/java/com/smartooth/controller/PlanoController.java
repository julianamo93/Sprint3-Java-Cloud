package com.smartooth.controller;

import com.smartooth.dto.PlanoDTO;
import com.smartooth.services.PlanoService;
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
@RequestMapping("/planos")
@RequiredArgsConstructor
public class PlanoController {

    private final PlanoService planoService;

    @PostMapping
    public ResponseEntity<String> createPlano(@RequestBody @Valid PlanoDTO planoDTO) {
        return planoService.createPlano(planoDTO);
    }


    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<PlanoDTO>> getPlanoById(@PathVariable Long id) {
        PlanoDTO planoDTO = planoService.getPlanoById(id).getBody();


        EntityModel<PlanoDTO> planoModel = EntityModel.of(planoDTO,
                linkTo(methodOn(PlanoController.class).getPlanoById(id)).withSelfRel(),
                linkTo(methodOn(PlanoController.class).getAllPlanos()).withRel("planos"));

        return ResponseEntity.ok(planoModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updatePlano(@PathVariable Long id,
                                              @Valid @RequestBody PlanoDTO planoDTO) {
        return planoService.updatePlano(id, planoDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePlano(@PathVariable Long id) {
        planoService.deletePlano(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<PlanoDTO>>> getAllPlanos() {
        List<EntityModel<PlanoDTO>> planosModels = new ArrayList<>();

        List<PlanoDTO> planosDTO = planoService.getAllPlanos();

        for (PlanoDTO planoDTO : planosDTO) {
            EntityModel<PlanoDTO> planoModel = EntityModel.of(planoDTO,
                    linkTo(methodOn(PlanoController.class).getPlanoById(planoDTO.getPlanoId())).withSelfRel());
            planosModels.add(planoModel);
        }

        return ResponseEntity.ok(CollectionModel.of(planosModels));
    }
}

