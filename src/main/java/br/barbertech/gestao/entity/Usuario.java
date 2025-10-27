package br.barbertech.gestao.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "nivel", discriminatorType = DiscriminatorType.INTEGER)
@EqualsAndHashCode (onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
abstract class Usuario {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private int id_usuario;

    private String nomeUsuario;

    private String cpf_usuario;

    @Column (name = "tel_usuario")
    private String telefone;

    private String senha;

    @Column(name = "nivel", insertable = false, updatable = false)
    private int nivel;

    private Integer status_usuario;

    @PrePersist
    public void prePersist() {
        if (status_usuario == null) {
            status_usuario = 1;
        }
    }

}
