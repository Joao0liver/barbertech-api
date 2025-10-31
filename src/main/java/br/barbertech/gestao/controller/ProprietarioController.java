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
@RequestMapping("/proprietarios")
public class ProprietarioController {

    @Autowired
    private ProprietarioRepository repository;

    @GetMapping
    public List<Proprietario> listar() {

        return repository.findAll();

    }

    @GetMapping("/nome/{nomeUsuario}")
    public List<Proprietario> buscarNome(@PathVariable String nomeUsuario) {

        return repository.findByNomeUsuarioContaining(nomeUsuario);

    }

    @GetMapping("/id/{idUsuario}")
    public ResponseEntity<Proprietario> buscarId(@PathVariable Long idUsuario){

        Optional<Proprietario> proprietario = repository.findById(idUsuario);

        if (proprietario.isPresent()) {
            return ResponseEntity.ok(proprietario.get());
        } else {
            return ResponseEntity.notFound().build();
        }

    }

    @PostMapping
    public ResponseEntity<Proprietario> cadastrar(@RequestBody ProprietarioDto dto) {
        Proprietario novoProprietario = new Proprietario();

        novoProprietario.setNomeUsuario(dto.nomeUsuario());
        novoProprietario.setCpfUsuario(dto.cpfUsuario());
        novoProprietario.setTelefone(dto.telefone());
        novoProprietario.setSenha(dto.senha());

        Proprietario proprietarioSalvo = repository.save(novoProprietario);
        return ResponseEntity.ok(proprietarioSalvo);
    }

    @DeleteMapping("/{idUsuario}")
    public ResponseEntity<Void> deletar(@PathVariable Long idUsuario) {
        if (!repository.existsById(idUsuario)){
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(idUsuario);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idUsuario}")
    public ResponseEntity<Proprietario> editar(@PathVariable Long idUsuario, @RequestBody ProprietarioDto dto) {
        return repository.findById(idUsuario)
                .map(proprietarioExistente -> {
                    proprietarioExistente.setNomeUsuario(dto.nomeUsuario());
                    proprietarioExistente.setCpfUsuario(dto.cpfUsuario());
                    proprietarioExistente.setTelefone(dto.telefone());
                    proprietarioExistente.setSenha(dto.senha());
                    proprietarioExistente.setStatusUsuario(1);

                    Proprietario proprietarioSalvo = repository.save(proprietarioExistente);
                    return ResponseEntity.ok(proprietarioSalvo);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
