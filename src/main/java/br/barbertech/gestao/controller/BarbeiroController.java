package br.barbertech.gestao.controller;

import br.barbertech.gestao.entity.Barbeiro;
import br.barbertech.gestao.repository.BarbeiroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
public class BarbeiroController {

    @Autowired
    private BarbeiroRepository repository;

    @GetMapping("/barbeiros")
    public List<Barbeiro> listar() {

        return repository.findAll();

    }

    @PostMapping("/cadastrar-barbeiros")
    public ResponseEntity<Barbeiro> cadastrar(@RequestBody Barbeiro novoBarbeiro) {
        Barbeiro barbeiroSalvo = repository.save(novoBarbeiro);
        return ResponseEntity.ok(barbeiroSalvo);
    }

    @DeleteMapping("/barbeiros/{id_usuario}")
    public ResponseEntity<Void> deletar(@PathVariable Long idUsuario) {
        if (!repository.existsById(idUsuario)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(idUsuario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/barbeiro/{id_usuario}")
    public ResponseEntity<Barbeiro> editar(@PathVariable Long id_usuario, @RequestBody Barbeiro dto) {
        return repository.findById(id_usuario)
                .map(barbeiroExistente -> {
                    barbeiroExistente.setNomeUsuario(dto.NomeUsuario());
                    barbeiroExistente.setCpfUsuario(dto.CpfUsuario());
                    barbeiroExistente.setTelefone(dto.telefone());
                    barbeiroExistente.setSenha(dto.Senha());
                    barbeiroExistente.setStatusUsuario(0);

                    Barbeiro BarbeiroSalvo = repository.save(barbeiroExistente);
                    return ResponseEntity.ok(BarbeiroSalvo);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }
}
