package br.barbertech.gestao.controller; // Ajuste o pacote conforme necessário

import br.barbertech.gestao.dto.ServicoDTO;
import br.barbertech.gestao.entity.Servico;
import br.barbertech.gestao.repository.ServicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus; // Para carregar uma resposta mais direta na consulta


import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/servicos") // Endpoint base para todos os métodos
public class ServicosController {

    @Autowired
    private ServicoRepository repository;

    // Listar todos
    @GetMapping("")
    public List<Servico> listar() {
        return repository.findAll();
    }

    // Listar 1 registro específico
    @GetMapping("/{idServico}")
    public Optional<Servico> getServicoPorId(@PathVariable Long idServico) {
        return repository.findById(idServico);
    }

    // Lista por nome buscado
    @GetMapping(params = "nome")
    public ResponseEntity<?> buscarServicos(
            @RequestParam(required = false) String nome) {

        List<Servico> servicos;

        if (nome!=null && !nome.trim().isEmpty()) {
            servicos = repository.findByNomeServico(nome);
        } else {

            String mensagemErro = "O parâmetro 'nome' é obrigatório para realizar a pesquisa.";

            // Retorna o Status 400 com a mensagem no corpo da resposta
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(mensagemErro);
        }

        if (servicos.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(servicos);
    }

    // Cadastrar novo
    @PostMapping
    public ResponseEntity<Servico> cadastrar(@RequestBody ServicoDTO dto) { // String é para momstrar a mensagem *
        // * (Se necessário, mudar para Servico - que relaciona o objeto)



        Servico novoServico = new Servico();
        novoServico.setNomeServico(dto.nomeServico());
        novoServico.setPrecoCusto(dto.precoCusto());
        novoServico.setPrecoVenda(dto.precoVenda());
        novoServico.setNomeServico(dto.nomeServico());

        Servico servicoSalvo = repository.save(novoServico);
        return ResponseEntity.ok(servicoSalvo);


        //ANTIGA DEVOLUÇÃO DE RESPOSTA
        //String mensagemCadastrado = "Serviço cadastrado com sucesso!";
        //return new ResponseEntity<>(mensagemCadastrado, HttpStatus.CREATED);


        //Antigo modelo que cadastrava
        //Servico servicoSalvo = repository.save(novoServico);
        //return ResponseEntity.ok(servicoSalvo);

        // Novo modelo para mostrar mensagem

    }

    // Deletar por ID
    @DeleteMapping("/{idServico}")
    public ResponseEntity<Void> deletarServico(@PathVariable Long idServico) {
        if (!repository.existsById(idServico)) {
            return ResponseEntity.notFound().build(); // retorna erro se não existir o id
        }

        repository.deleteById(idServico);
        return ResponseEntity.noContent().build(); // retorna 200
    }

    // Atualizar por ID
    @PutMapping("/{idServico}")
    public ResponseEntity<Servico> atualizarServico(@PathVariable Long idServico, @RequestBody ServicoDTO dto) {
        return repository.findById(idServico)
                .map(servicoExistente -> {
                    // Atualiza apenas os campos que podem ser modificados
                    servicoExistente.setNomeServico(dto.nomeServico());
                    servicoExistente.setPrecoCusto(dto.precoCusto());
                    servicoExistente.setPrecoVenda(dto.precoVenda());
                    servicoExistente.setStatusServico(true);

                    Servico servicoSalvo = repository.save(servicoExistente);
                    return ResponseEntity.ok(servicoSalvo); // 200 OK
                })
                .orElseGet(() -> ResponseEntity.notFound().build()); // 404 Not Found
    }
}