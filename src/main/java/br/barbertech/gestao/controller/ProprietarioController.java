package br.barbertech.gestao.controller;

import br.barbertech.gestao.entity.Proprietario;
import br.barbertech.gestao.repository.ProprietarioRepository;
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
public class ProprietarioController {

    @PersistenceContext
    private EntityManager manager;

    @Autowired
    private ProprietarioRepository repository;

    @GetMapping ("/proprietarios")
    public List<Proprietario> listar() {

        return manager.createQuery("from Proprietario",Proprietario.class).getResultList();

    }

    @PostMapping ("/cadastrar-proprietario")
    public ResponseEntity<Proprietario> cadastrar(@RequestBody Proprietario novoProprietario) {
        Proprietario proprietarioSalvo = repository.save(novoProprietario);
        return ResponseEntity.ok(proprietarioSalvo);
    }

}
