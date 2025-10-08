package br.barbertech.gestao.controller;

import br.barbertech.gestao.entity.Barbeiro;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class BarbeiroController {

    @GetMapping ("/barbeiros")
    public List<Barbeiro> listar(){

        return null;

    }

}
