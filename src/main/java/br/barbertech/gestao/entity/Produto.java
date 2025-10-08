package br.barbertech.gestao.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Produto {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_produto;

    private String nome_produto;

    @Column(name = "preco_c_produto")
    private double preco_custo;

    @Column(name = "preco_v_produto")
    private double preco_venda;

    private int quantidade;

    private int status_produto;

}
