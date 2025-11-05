package br.barbertech.gestao.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDate;

@Entity
@Table(name = "caixa")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegistroCaixa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_caixa")
    private long idCaixa;

    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "id_itemcaixa")
    private Integer idItemCaixa;

    @Column(name = "data_caixa")
    private LocalDate dataCaixa;

    @PrePersist
    public void prePersist() {
        if (dataCaixa == null) {
            dataCaixa = LocalDate.now();
        }
    }
}
