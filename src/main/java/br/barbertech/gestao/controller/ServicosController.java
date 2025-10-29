package br.barbertech.gestao.controller; // Ajuste o pacote conforme necessário

import br.barbertech.gestao.entity.Servico;
import br.barbertech.gestao.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus; // Para carregar uma resposta mais direta na consulta


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

    // Listar 1 registro específico:
    @GetMapping("/{id_servico}")
    public ResponseEntity<Servico> getServicoPorId(@PathVariable Long id_servico) {
        return repository.findById(id_servico)
                .map(servico -> ResponseEntity.ok(servico))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // (Cadastrar novo)
    @PostMapping ("/cadastrar-servico")
    public ResponseEntity<String> cadastrar(@RequestBody Servico novoServico) { // String é para momstrar a mensagem *
        // * (Se necessário, mudar para Servico - que relaciona o objeto)

        //Antigo modelo que cadastrava
        //Servico servicoSalvo = repository.save(novoServico);
        //return ResponseEntity.ok(servicoSalvo);

        // Novo modelo para mostrar mensagem
        repository.save(novoServico);
        String mensagemCadastrado = "Serviço cadastrado com sucesso!";
        return new ResponseEntity<>(mensagemCadastrado, HttpStatus.CREATED);
    }

    // (Deletar por ID)
    @DeleteMapping("servicos/{id_servico}")
    public ResponseEntity<Void> deletarServico(@PathVariable Long id_servico) {
        if (!repository.existsById(id_servico)) {
            return ResponseEntity.notFound().build(); // retorna erro se não existir o id
        }

        repository.deleteById(id_servico);
        return ResponseEntity.noContent().build(); // retorna 200
    }

    // (Atualizar por ID)
    @PutMapping("/{id_servico}")
    public ResponseEntity<Servico> atualizarServico(@PathVariable Long id_servico, @RequestBody Servico dto) {
        return repository.findById(id_servico)
                .map(servicoExistente -> {
                    // Atualiza apenas os campos que podem ser modificados
                    servicoExistente.toString(dto.getNome_servico());
                    servicoExistente.set_Preco_custo(dto.getPreco_custo());
                    servicoExistente.setPreco_venda(dto.getPreco_venda());
                    servicoExistente.setStatus_servico(dto.getStatus_servico());

                    Servico servicoSalvo = repository.save(servicoExistente);
                    return ResponseEntity.ok(servicoSalvo); // 200 OK
                })
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404 Not Found
    }
}