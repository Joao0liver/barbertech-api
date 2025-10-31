package br.barbertech.gestao.repository;

import br.barbertech.gestao.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    List<Produto> findByNomeProdutoContaining(String nomeProduto);

}

