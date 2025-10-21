package br.barbertech.gestao.controller; // Ajuste o pacote conforme necessário

import br.barbertech.gestao.entity.Servico;
import br.barbertech.gestao.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/servicos") // Endpoint base para todos os métodos
public class ServicosController {

    @Autowired
    private ServicoRepository repository;

    // (Listar todos)
    @GetMapping
    public List<Servico> listar() {
        return repository.findAll();
    }

    // (Cadastrar novo)
    @PostMapping
    public ResponseEntity<Servico> cadastrar(@RequestBody Servico novoServico) {
        Servico servicoSalvo = repository.save(novoServico);
        // Retorna 200 OK com o objeto salvo.
        return ResponseEntity.ok(servicoSalvo);
    }

    // (Deletar por ID)
    @DeleteMapping("servicos/{id_servico}")
    public ResponseEntity<Void> deletarServico(@PathVariable Long id_servico) {
        if (!repository.existsById(id_servico)) {
            return ResponseEntity.notFound().build(); // 404
        }

        repository.deleteById(id_servico);
        return ResponseEntity.noContent().build(); // 204 No Content
    }

    // (Atualizar por ID)
    @PutMapping("/{id_servico}")
    public ResponseEntity<Servico> atualizarServico(@PathVariable Long id_servico, @RequestBody Servico dto) {
        return repository.findById(id_servico)
                .map(servicoExistente -> {
                    // Atualiza apenas os campos que podem ser modificados
                    servicoExistente.setNome_servico(dto.getNome_servico());
                    servicoExistente.setPreco_custo(dto.getPreco_custo());
                    servicoExistente.setPreco_venda(dto.getPreco_venda());
                    servicoExistente.setStatus_servico(dto.getStatus_servico());

                    Servico servicoSalvo = repository.save(servicoExistente);
                    return ResponseEntity.ok(servicoSalvo); // 200 OK
                })
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404 Not Found
    }
}