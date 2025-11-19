package br.barbertech.gestao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
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

    @NotNull
    @Column(name = "id_item")
    private int idItem;

    @NotNull
    @Column(name = "quantidade_item")
    private int quantidadeItem;

    @NotNull
    private double valor;

}
