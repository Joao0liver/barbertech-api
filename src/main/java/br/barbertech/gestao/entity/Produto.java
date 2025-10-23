package br.barbertech.gestao.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;

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

    private Integer status_produto;

    @PrePersist // Fixa o valor número 1 antes de a persistência ocorrer, garantindo que o campo status_produto será inserido como 1 mesmo sem enviá-lo por POST
    public void prePersist() {
        if (status_produto == null) {
            status_produto = 1;
        }
    }

}
