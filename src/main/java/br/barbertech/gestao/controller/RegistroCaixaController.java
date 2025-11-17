package br.barbertech.gestao.controller;

import br.barbertech.gestao.dto.RegistroCaixaDTO;
import br.barbertech.gestao.entity.RegistroCaixa;
import br.barbertech.gestao.repository.RegistroCaixaRepository;
import org.springframework.beans.CachedIntrospectionResults;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/registroCaixa")
public class RegistroCaixaController {

    @Autowired
    private RegistroCaixaRepository repository;

    //Listar todos registros
    @GetMapping("")
    public List<RegistroCaixa> listar() {
        return repository.findAll();
    }

    //Get específico por ID
    @GetMapping("/{idCaixa}")
    public Optional<RegistroCaixa> getCaixaPorId(@PathVariable Long idCaixa) {
        return repository.findById(idCaixa);
    }

    //Cadastrar Registro
    @PostMapping
    public ResponseEntity<RegistroCaixa> cadastrar(@RequestBody RegistroCaixaDTO dto) {

        RegistroCaixa novoRegistro = new RegistroCaixa();
        novoRegistro.setIdUsuario(dto.idUsuario());
        novoRegistro.setIdItemCaixa(dto.idItemCaixa());
        novoRegistro.setDataCaixa(dto.dataCaixa());

        RegistroCaixa registroSalvo = repository.save(novoRegistro);

        return ResponseEntity.status(HttpStatus.CREATED).body(registroSalvo);

    }

    //Deletar por ID
    @DeleteMapping("/{idCaixa}")
    public ResponseEntity<Void> deletarResgistroCaixa(@PathVariable Long idCaixa) {
        if (!repository.existsById(idCaixa)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(idCaixa);
        return ResponseEntity.noContent().build();
    }

    //Atualizar registro
    @PutMapping("/{idCaixa}")
    public ResponseEntity<RegistroCaixa> atualizarRegistroCaixa(@PathVariable Long idCaixa, @RequestBody RegistroCaixaDTO dto) {
        return repository.findById(idCaixa)
                .map(registroExistente -> {
                    registroExistente.setIdUsuario(dto.idUsuario());
                    registroExistente.setIdItemCaixa(dto.idItemCaixa());
                    registroExistente.setDataCaixa(dto.dataCaixa());

                    RegistroCaixa registroSalvo = repository.save(registroExistente);
                    return ResponseEntity.ok(registroSalvo);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
