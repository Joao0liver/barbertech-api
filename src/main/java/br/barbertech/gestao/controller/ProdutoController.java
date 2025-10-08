package br.barbertech.gestao.controller;

import br.barbertech.gestao.entity.Produto;
import br.barbertech.gestao.repository.ProdutoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ProdutoRepository repository;

    @GetMapping ("/produtos")
    public List<Produto> listar() {

        return manager.createQuery("from Produto",Produto.class).getResultList();

    }

    @PostMapping ("/cadastrar-produto")
    public ResponseEntity<Produto> cadastrar(@RequestBody Produto novoProduto) {
        Produto produtoSalvo = repository.save(novoProduto);
        return ResponseEntity.ok(produtoSalvo);
    }

}
