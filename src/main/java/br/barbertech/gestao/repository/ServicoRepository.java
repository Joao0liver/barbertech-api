package br.barbertech.gestao.repository;

import br.barbertech.gestao.entity.Servico;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServicoRepository extends JpaRepository<Servico, Long> {

}