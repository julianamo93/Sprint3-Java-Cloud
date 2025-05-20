package com.smartooth.repository;

import com.smartooth.model.UsuarioPaciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UsuarioPacienteRepository extends JpaRepository<UsuarioPaciente, Long> {

    Optional<UsuarioPaciente> findByEmail(String email);

}




