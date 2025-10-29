package br.barbertech.gestao.repository;

import br.barbertech.gestao.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

}
