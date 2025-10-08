package br.barbertech.gestao.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.annotation.processing.Generated;

@Entity
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
@Getter
@Setter
public class Produto {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_produto;

    private String nome_produto;

    private double preco_custo;

    private double preco_venda;

    private int quantidade;

    public Produto(int id_produto, String nome_produto, double preco_custo, double preco_venda, int quantidade) {
        this.id_produto = id_produto;
        this.nome_produto = nome_produto;
        this.preco_custo = preco_custo;
        this.preco_venda = preco_venda;
        this.quantidade = quantidade;
    }

}
