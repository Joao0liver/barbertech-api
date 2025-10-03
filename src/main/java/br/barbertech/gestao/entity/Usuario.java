package br.barbertech.gestao.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
abstract class Usuario {

    private int id;
    private String nome;
    private String cpf;
    private String telefone;
    private String senha;

    public Usuario(int id, String nome, String cpf, String telefone, String senha) {
        this.id = id;
        this.nome = nome;
        this.cpf = cpf;
        this.telefone = telefone;
        this.senha = senha;
    }

}
