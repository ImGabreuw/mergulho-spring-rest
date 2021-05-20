package me.gabreuw.algalog.domain.repository;

import me.gabreuw.algalog.domain.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    List<Cliente> findByNome(String nome);
    List<Cliente> findByNomeContaining(String nome);

    Optional<Cliente> findByEmail(String email);

}