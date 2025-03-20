package com.smartooth.controller;

import com.smartooth.services.UsuarioPacienteService;
import com.smartooth.dto.UsuarioPacienteDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioPacienteController {

    private final UsuarioPacienteService usuarioPacienteService;

    @Autowired
    public UsuarioPacienteController(UsuarioPacienteService usuarioPacienteService) {
        this.usuarioPacienteService = usuarioPacienteService;
    }

    @PostMapping
    public ResponseEntity<UsuarioPacienteDTO> createUsuario(@Valid @RequestBody UsuarioPacienteDTO usuarioPacienteDTO) {
        return usuarioPacienteService.createUsuario(usuarioPacienteDTO);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EntityModel<UsuarioPacienteDTO>> getUsuarioById(@PathVariable Long id) {
        UsuarioPacienteDTO usuario = usuarioPacienteService.getUsuarioPacienteById(id).getBody();

        EntityModel<UsuarioPacienteDTO> usuarioModel = EntityModel.of(usuario,
                linkTo(methodOn(UsuarioPacienteController.class).getUsuarioById(id)).withSelfRel(),
                linkTo(methodOn(UsuarioPacienteController.class).getAllUsuarios()).withRel("usuarios"));

        return ResponseEntity.ok(usuarioModel);
    }

    @PutMapping("/{id}")
    public ResponseEntity<UsuarioPacienteDTO> updateUsuario(@PathVariable Long id,
                                                            @Valid @RequestBody UsuarioPacienteDTO usuarioPacienteDTO) {
        return usuarioPacienteService.updateUsuario(id, usuarioPacienteDTO);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsuario(@PathVariable Long id) {
        usuarioPacienteService.deleteUsuario(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<CollectionModel<EntityModel<UsuarioPacienteDTO>>> getAllUsuarios() {

        List<EntityModel<UsuarioPacienteDTO>> usuariosModels = new ArrayList<>();


        List<UsuarioPacienteDTO> usuarios = usuarioPacienteService.getAllUsuarios().getBody();


        for (UsuarioPacienteDTO usuario : usuarios) {
            EntityModel<UsuarioPacienteDTO> usuarioModel = EntityModel.of(usuario,
                    linkTo(methodOn(UsuarioPacienteController.class).getUsuarioById(usuario.getUsuarioPacienteId())).withSelfRel());
            usuariosModels.add(usuarioModel);
        }


        return ResponseEntity.ok(CollectionModel.of(usuariosModels));
    }
}





