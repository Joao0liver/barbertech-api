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
    private int idProduto;

    private String nomeProduto;

    @Column(name = "preco_c_produto")
    private double precoCusto;

    @Column(name = "preco_v_produto")
    private double precoVenda;

    private int quantidade;

    private Integer statusProduto;

    @PrePersist // Fixa o valor número 1 antes de a persistência ocorrer, garantindo que o campo status_produto será inserido como 1 mesmo sem enviá-lo por POST
    public void prePersist() {
        if (statusProduto == null) {
            statusProduto = 1;
        }
    }

}
