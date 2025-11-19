package br.barbertech.gestao.controller;

import br.barbertech.gestao.dto.ItemCaixaDto;
import br.barbertech.gestao.entity.ItemCaixa;
import br.barbertech.gestao.entity.Produto;
import br.barbertech.gestao.repository.ItemCaixaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/itemCaixa")
public class ItemCaixaController {

    @Autowired
    private ItemCaixaRepository repository;

    @GetMapping
    public List<ItemCaixa> listar() {
        return repository.findAll();
    }

    @GetMapping("/id/{idItemCaixa}")
    public Optional<ItemCaixa> listarId(@PathVariable Long idItemCaixa) {
        return repository.findById(idItemCaixa);
    }

    @PostMapping
    public ResponseEntity<ItemCaixa> cadastrar(@RequestBody ItemCaixaDto dto) {
        ItemCaixa novoItemCaixa = new ItemCaixa();

        novoItemCaixa.setIdItem(dto.idItem());
        novoItemCaixa.setQuantidadeItem(dto.quantidadeItem());
        novoItemCaixa.setValor(dto.valor());

        ItemCaixa itemCaixaSalvo = repository.save(novoItemCaixa);
        return ResponseEntity.ok(itemCaixaSalvo);
    }

    @DeleteMapping("/{idItemCaixa}")
    public ResponseEntity<Void> deletar(@PathVariable Long idItemCaixa) {
        if (!repository.existsById(idItemCaixa)) {
            return ResponseEntity.notFound().build();
        }

        repository.deleteById(idItemCaixa);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{idItemCaixa}")
    public ResponseEntity<ItemCaixa> atualizarItemCaixa(@PathVariable Long idItemCaixa, @RequestBody ItemCaixaDto dto) {
        return repository.findById(idItemCaixa)
                .map(itemCaixaExistente -> {
                    itemCaixaExistente.setIdItem(dto.idItem());
                    itemCaixaExistente.setQuantidadeItem(dto.quantidadeItem());
                    itemCaixaExistente.setValor(dto.valor());

                    ItemCaixa itemCaixaSalvo = repository.save(itemCaixaExistente);
                    return ResponseEntity.ok(itemCaixaSalvo);
                })
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

}
