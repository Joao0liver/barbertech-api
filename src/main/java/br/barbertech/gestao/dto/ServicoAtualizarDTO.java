package br.barbertech.gestao.dto;

import lombok.Data;

@Data
public class ServicoAtualizarDTO {
    private String nomeServico;
    private double precoCusto;
    private double precoVenda;
    private boolean statusServico;
}
