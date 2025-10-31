package br.barbertech.gestao.controller;

import br.barbertech.gestao.dto.ProdutoDto;
import br.barbertech.gestao.entity.Produto;
import br.barbertech.gestao.repository.ProdutoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ProdutoRepository repository;

    @GetMapping
    public List<Produto> listar() {

        return manager.createQuery("from Produto",Produto.class).getResultList();

    }

    @PostMapping
    public ResponseEntity<Produto> cadastrar(@RequestBody ProdutoDto dto) {
        Produto novoProduto = new Produto();

        novoProduto.setNomeProduto(dto.nomeProduto());
        novoProduto.setPrecoCusto(dto.precoCusto());
        novoProduto.setPrecoVenda(dto.precoVenda());
        novoProduto.setQuantidade(dto.quantidade());

        Produto produtoSalvo = repository.save(novoProduto);
        return ResponseEntity.ok(produtoSalvo);
    }

    @DeleteMapping("/{id_produto}")
    public ResponseEntity<Void> deletarProduto(@PathVariable Long id_produto) {
        if (!repository.existsById(id_produto)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id_produto);
        return ResponseEntity.noContent().build(); // 204
    }

    @PutMapping ("/{id_produto}")
    public ResponseEntity<Produto> atualizarProduto(@PathVariable Long idProduto, @RequestBody ProdutoDto dto) {
        return repository.findById(idProduto)
                .map(produtoExistente -> {
                    produtoExistente.setNomeProduto(dto.nomeProduto());
                    produtoExistente.setPrecoCusto(dto.precoCusto());
                    produtoExistente.setPrecoVenda(dto.precoVenda());
                    produtoExistente.setQuantidade(dto.quantidade());
                    produtoExistente.setStatusProduto(1);

                    Produto produtoSalvo = repository.save(produtoExistente);
                    return ResponseEntity.ok(produtoSalvo);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
