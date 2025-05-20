package com.smartooth.controller;

import com.smartooth.dto.RecomendacaoTratamentoDTO;
import com.smartooth.services.RecomendacaoTratamentoService;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.Link;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

@RestController
@RequestMapping("/recomendacoes")
public class RecomendacaoTratamentoController {

    private final RecomendacaoTratamentoService recomendacaoTratamentoService;

    public RecomendacaoTratamentoController(RecomendacaoTratamentoService recomendacaoTratamentoService) {
        this.recomendacaoTratamentoService = recomendacaoTratamentoService;
    }

    // Obter todas as recomendações com HATEOAS
    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<RecomendacaoTratamentoDTO>>> getAllRecomendacoes() {
        List<EntityModel<RecomendacaoTratamentoDTO>> recomendacoesDTO = recomendacaoTratamentoService.getAllRecomendacoes().getBody().stream()
                .map(this::toEntityModel)
                .collect(Collectors.toList());

        CollectionModel<EntityModel<RecomendacaoTratamentoDTO>> collectionModel = CollectionModel.of(recomendacoesDTO);
        collectionModel.add(linkTo(RecomendacaoTratamentoController.class).withSelfRel());

        return ResponseEntity.ok(collectionModel);
    }

    // Obter uma recomendação específica por ID com HATEOAS
    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<RecomendacaoTratamentoDTO>> getRecomendacaoById(@PathVariable Long id) {
        RecomendacaoTratamentoDTO recomendacaoTratamentoDTO = recomendacaoTratamentoService.getRecomendacaoById(id).getBody();
        EntityModel<RecomendacaoTratamentoDTO> entityModel = toEntityModel(recomendacaoTratamentoDTO);

        return ResponseEntity.ok(entityModel);
    }

    // Criar uma nova recomendação
    @PostMapping
    public ResponseEntity<String> createRecomendacao(@Valid @RequestBody RecomendacaoTratamentoDTO recomendacaoTratamentoDTO) {
        return recomendacaoTratamentoService.save(recomendacaoTratamentoDTO);
    }

    // Atualizar uma recomendação existente
    @PutMapping("/{id}")
    public ResponseEntity<String> updateRecomendacao(@PathVariable Long id, @Valid @RequestBody RecomendacaoTratamentoDTO recomendacaoTratamentoDTO) {
        return recomendacaoTratamentoService.update(id, recomendacaoTratamentoDTO);
    }

    // Deletar uma recomendação
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteRecomendacao(@PathVariable Long id) {
        return recomendacaoTratamentoService.delete(id);
    }

    // Método para mapear o DTO para EntityModel com links HATEOAS
    private EntityModel<RecomendacaoTratamentoDTO> toEntityModel(RecomendacaoTratamentoDTO recomendacaoTratamentoDTO) {
        EntityModel<RecomendacaoTratamentoDTO> entityModel = EntityModel.of(recomendacaoTratamentoDTO);

        // Link para o próprio recurso
        Link selfLink = linkTo(RecomendacaoTratamentoController.class)
                .slash(recomendacaoTratamentoDTO.getRecomendacaoId())
                .withSelfRel();
        entityModel.add(selfLink);

        // Link para a lista completa de recomendações
        Link allLinks = linkTo(RecomendacaoTratamentoController.class).withRel("all-recomendacoes");
        entityModel.add(allLinks);

        return entityModel;
    }
}


