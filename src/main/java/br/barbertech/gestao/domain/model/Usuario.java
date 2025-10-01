package br.barbertech.gestao.domain.model;

import lombok.Getter;

@Getter
abstract class Usuario {

    private int id;
    private String nome;
    private String cpf;
    private String telefone;

    public Usuario(int id, String nome, String cpf, String telefone) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
    }

}
