package br.barbertech.gestao.controller;

import br.barbertech.gestao.dto.ProprietarioDto;
import br.barbertech.gestao.entity.Proprietario;
import br.barbertech.gestao.repository.ProprietarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ProprietarioController {

    @Autowired
    private ProprietarioRepository repository;

    @GetMapping ("/proprietarios")
    public List<Proprietario> listar() {

        return repository.findAll();

    }

    @GetMapping ("/proprietarios/nome/{nome_usuario}")
    public List<Proprietario> buscarNome(@PathVariable String nome_usuario) {

        return repository.findByNomeUsuarioContaining(nome_usuario);

    }

    @GetMapping ("/proprietarios/id/{id_usuario}")
    public ResponseEntity<Proprietario> buscarId(@PathVariable Long id_usuario){

        Optional<Proprietario> proprietario = repository.findById(id_usuario);

        if (proprietario.isPresent()) {
            return ResponseEntity.ok(proprietario.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping ("/cadastrar-proprietario")
    public ResponseEntity<Proprietario> cadastrar(@RequestBody Proprietario novoProprietario) {
        Proprietario proprietarioSalvo = repository.save(novoProprietario);
        return ResponseEntity.ok(proprietarioSalvo);
    }

    @DeleteMapping ("/proprietarios/{id_usuario}")
    public ResponseEntity<Void> deletar(@PathVariable Long id_usuario) {
        if (!repository.existsById(id_usuario)){
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id_usuario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping ("/proprietarios/{id_usuario}")
    public ResponseEntity<Proprietario> editar(@PathVariable Long id_usuario, @RequestBody ProprietarioDto dto) {
        return repository.findById(id_usuario)
                .map(proprietarioExistente -> {
                    proprietarioExistente.setNomeUsuario(dto.nomeUsuario());
                    proprietarioExistente.setCpf_usuario(dto.cpf_usuario());
                    proprietarioExistente.setTelefone(dto.telefone());
                    proprietarioExistente.setSenha(dto.senha());
                    proprietarioExistente.setStatus_usuario(1);

                    Proprietario proprietarioSalvo = repository.save(proprietarioExistente);
                    return ResponseEntity.ok(proprietarioSalvo);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
