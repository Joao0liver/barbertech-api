package br.barbertech.gestao.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;

public record RegistroCaixaDTO(int idUsuario, int idItemCaixa, LocalDate dataCaixa) {

}