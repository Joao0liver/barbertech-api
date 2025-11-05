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
    @GetMapping("/{id_caixa}")
    public Optional<RegistroCaixa> getCaixaPorId(@PathVariable Long id_caixa) {
        return repository.findById(id_caixa);
    }

    //Cadastrar Registro
    @PostMapping("/cadastrarRegistro")
    public ResponseEntity<RegistroCaixa> cadastrar(@RequestBody RegistroCaixaDTO dto) {

        RegistroCaixa novoRegistro = new RegistroCaixa();
        novoRegistro.setIdUsuario(dto.idUsuario());
        novoRegistro.setIdItemCaixa(dto.idItemCaixa());
        novoRegistro.setDataCaixa(dto.dataCaixa());

        RegistroCaixa registroSalvo = repository.save(novoRegistro);

        return ResponseEntity.status(HttpStatus.CREATED).body(registroSalvo);

    }

    //Deletar por ID
    @DeleteMapping("/{id_caixa}")
    public ResponseEntity<Void> deletarResgistroCaixa(@PathVariable Long id_caixa) {
        if (!repository.existsById(id_caixa)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(id_caixa);
        return ResponseEntity.noContent().build();
    }

    //Atualizar registro
    @PutMapping("/{id_caixa}")
    public ResponseEntity<RegistroCaixa> atualizarRegistroCaixa(@PathVariable Long id_caixa, @RequestBody RegistroCaixaDTO dto) {
        return repository.findById(id_caixa)
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
