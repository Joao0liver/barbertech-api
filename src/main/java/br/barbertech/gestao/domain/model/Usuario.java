package br.barbertech.gestao.domain.model;

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

    public void teste(){
        System.out.println("FUNCIONANDO");
    }
}
