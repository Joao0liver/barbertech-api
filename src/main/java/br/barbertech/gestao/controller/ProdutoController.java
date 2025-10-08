package br.barbertech.gestao.controller;

import br.barbertech.gestao.entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ProdutoController {

    @PersistenceContext
    private EntityManager manager;

    @GetMapping ("/produtos")
    public List<Produto> listar() {

        return manager.createQuery("from Produto",Produto.class).getResultList();

    }

}
