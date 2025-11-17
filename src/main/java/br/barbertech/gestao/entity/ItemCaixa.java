package br.barbertech.gestao.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "item_caixa")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ItemCaixa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    @Column(name = "id_itemcaixa")
    private int idItemCaixa;

    @Column(name = "id_item")
    private int idItem;

    @Column(name = "quantidade_item")
    private int quantidadeItem;

    private double valor;

}
