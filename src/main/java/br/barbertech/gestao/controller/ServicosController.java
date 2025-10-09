package br.barbertech.gestao.controller;

import br.barbertech.gestao.dto.ServicoDAO;
import br.barbertech.gestao.entity.Servicos;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ServicosController {

    @GetMapping ("/servicos")
    public static void main(String[] args) throws Exception {
        Servicos servico1 = new Servicos();
        servico1.setNome_servico("Corte");
        servico1.setPreco_c_servico(10.00);
        servico1.setPreco_v_servico(25.00);
        servico1.setStatus_servico(true);

        new ServicoDAO().cadastrarServico(servico1);
    }
}
