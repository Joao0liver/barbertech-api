package br.barbertech.gestao.repository;

import br.barbertech.gestao.entity.Barbeiro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarbeiroRepository extends JpaRepository<Barbeiro, Long> {

}
