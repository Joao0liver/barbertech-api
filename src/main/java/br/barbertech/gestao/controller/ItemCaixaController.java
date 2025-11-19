package br.barbertech.gestao.controller;

import br.barbertech.gestao.repository.ItemCaixaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/itemCaixa")
public class ItemCaixaController {

    @Autowired
    private ItemCaixaRepository repository;

}
