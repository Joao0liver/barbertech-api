package br.barbertech.gestao.controller;

import br.barbertech.gestao.entity.Produto;
import br.barbertech.gestao.repository.ProdutoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ProdutoRepository repository;

    @GetMapping ("/produtos")
    public List<Produto> listarProduto() {

        return manager.createQuery("from Produto",Produto.class).getResultList();

    }

    @PostMapping ("/cadastrar-produto")
    public ResponseEntity<Produto> cadastrarProduto(@RequestBody Produto novoProduto) {
        Produto produtoSalvo = repository.save(novoProduto);
        return ResponseEntity.ok(produtoSalvo);
    }

    @DeleteMapping ("/produtos/{id_produto}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id_produto) {
        if (!repository.existsById(id_produto)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id_produto);
        return ResponseEntity.noContent().build(); // 204
    }

}
