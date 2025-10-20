package br.barbertech.gestao.controller;

import br.barbertech.gestao.entity.Proprietario;
import br.barbertech.gestao.repository.ProprietarioRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @DeleteMapping("/proprietarios/{id_usuario}")
    public ResponseEntity<Void> deletar(@PathVariable Long id_usuario) {
        if (!repository.existsById(id_usuario)){
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id_usuario);
        return ResponseEntity.noContent().build();
    }

}
