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
    @Column (name = "id_usuario")
    private int idUsuario;

    @Column (name = "nome_usuario")
    private String nomeUsuario;

    @Column (name = "cpf_usuario")
    private String cpfUsuario;

    @Column (name = "tel_usuario")
    private String telefone;

    private String senha;

    @Column(name = "nivel", insertable = false, updatable = false)
    private int nivel;

    @Column (name = "status_usuario")
    private Integer statusUsuario;

    @PrePersist
    public void prePersist() {
        if (statusUsuario == null) {
            statusUsuario = 1;
        }
    }

}
