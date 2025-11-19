package br.barbertech.gestao.repository;

import br.barbertech.gestao.entity.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {

    List<Barbeiro> findByNomeUsuarioContaining(String nomeUsuario);

}
