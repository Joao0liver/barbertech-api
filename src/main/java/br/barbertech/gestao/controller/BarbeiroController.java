package br.barbertech.gestao.controller;

import br.barbertech.gestao.entity.Barbeiro;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class BarbeiroController {

    @GetMapping("/barbeiros")
    public List<Barbeiro> listar() {

        return manager.createQuery("from Barbeiro", Barbeiro.class).getResultList();

    }

    @PostMapping
    public ResponseEntity<Barbeiro> cadastrar(@RequestBody Barbeiro novoBarbeiro) {
        Barbeiro barbeiroSalvo = repository.save(novoBarbeiro);
        return ResponseEntity.ok(barbeiroSalvo);
    }

    @DeleteMapping("/barbeiros/{id_usuario}")
    public ResponseEntity<Void> deletar(@PathVariable Long id_usuario) {
        if (!repository.existsById(id_usuario)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id_usuario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/Barbeiro/{id_usuario}")
    public ResponseEntity<Barbeiro> editar(@PathVariable Long id_usuario, @RequestBody Barbeiro dto) {
        return repository.findById(id_usuario)
                .map(barbeiroExistente -> {
                    barbeiroExistente.setNome_usuario(dto.getNome_usuario());
                    barbeiroExistente.setCpf_usuario(dto.getCpf_usuario());
                    barbeiroExistente.setTelefone(dto.getTelefone());
                    barbeiroExistente.setSenha(dto.getSenha());
                    barbeiroExistente.setStatus_usuario(1);

                    Barbeiro BarbeiroSalvo = repository.save(barbeiroExistente);
                    return ResponseEntity.ok(BarbeiroSalvo);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
