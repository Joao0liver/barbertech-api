package br.barbertech.gestao.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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
    @Column(name = "id_produto")
    private int idProduto;

    @NotBlank
    @Size(min = 1, max = 255)
    @Column(name = "nome_produto")
    private String nomeProduto;

    @NotNull
    @DecimalMin(value = "0.01", message = "O preço de custo do produto deve ser maior ou igual a 0.01!")
    @DecimalMax(value = "10000.00", message = "O preço de custo do produto deve ser menor ou igual a 10000.00!")
    @Column(name = "preco_c_produto")
    private double precoCusto;

    @NotNull
    @DecimalMin(value = "0.01", message = "O preço de venda do produto deve ser maior ou igual a 0.01!")
    @DecimalMax(value = "10000.00", message = "O preço de venda do produto deve ser menor ou igual a 10000.00!")
    @Column(name = "preco_v_produto")
    private double precoVenda;

    @NotNull
    private int quantidade;

    @Column(name = "status_produto")
    private Integer statusProduto;

    @PrePersist // Fixa o valor número 1 antes de a persistência ocorrer, garantindo que o campo status_produto será inserido como 1 mesmo sem enviá-lo por POST
    public void prePersist() {
        if (statusProduto == null) {
            statusProduto = 1;
        }
    }

}
