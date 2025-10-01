package br.barbertech.gestao.domain.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Barbeiro extends Usuario {

    public Barbeiro(int id, String nome, String cpf, String telefone){
        super(id, nome, cpf, telefone);
    }

}
