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

        var barbeiro1 = new Barbeiro(1, "Júnior", "12345678901", "1186532764", "teste");
        var barbeiro2 = new Barbeiro(2, "Felipe", "12345678910", "35985267492", "teste");

        return Arrays.asList(barbeiro1, barbeiro2);

    }

}
