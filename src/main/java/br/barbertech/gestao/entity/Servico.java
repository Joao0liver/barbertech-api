package br.barbertech.gestao.entity;

import jakarta.persistence.*;
import lombok.*;

//@Getter
//@Setter
//public class Servicos {
//
//    private int id;
//    private String nome_servico;
//    private double preco_c_servico;
//    private double preco_v_servico;
//    private boolean status_servico;
//
////    public Servicos(int id, String nomeServico, Double preco) {
////        this.id = id;
////        this.nome_servico = nome_servico;
////        this.preco_c_servico = preco_c_servico;
////        this.preco_v_servico = preco_v_servico;
////        this.status_servico = status_servico;
////    }
//
//    public int getId() {
//        return id;
//    }
//
//    public void setId(int id) {
//        this.id = id;
//    }
//
//    public String getNome_servico() {
//        return nome_servico;
//    }
//
//    public void setNome_servico(String nome_servico) {
//        this.nome_servico = nome_servico;
//    }
//
//    public double getPreco_c_servico() {
//       return  preco_c_servico;
//    }
//
//    public void setPreco_c_servico(double preco_c_servico) {
//        this.preco_c_servico = preco_c_servico;
//    }
//
//    public double getPreco_v_servico() {
//        return preco_v_servico;
//    }
//
//    public void setPreco_v_servico(double preco_v_servico) {
//        this.preco_v_servico = preco_v_servico;
//    }
//
//    public boolean getStatus_servico() {
//        return status_servico;
//    }
//
//    public void setStatus_servico(boolean status_servico) {
//        this.status_servico = status_servico;
//    }
//}

@Entity
@Table(name = "servico") // Opcional: Especifica o nome exato da tabela
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Servico {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long idServico; // Tipo 'id' (usando Long para o ID)

    private String nomeServico; // Tipo 'String'

    @Column(name = "preco_c_servico")
    private double precoCusto; // Tipo 'double'

    @Column(name = "preco_v_servico")
    private double precoVenda; // Tipo 'double'

    @Column(name = "status_servico")
    private Boolean statusServico; // Tipo 'boolean'

    /**
        Fixa o valor TRUE (ativo) antes de a persistência ocorrer.
        Garante que o campo status_servico será TRUE (ativo) se não for enviado.
     */
    @PrePersist
    public void prePersist() {
        if (statusServico == null) {
            statusServico = true;
        }
    }

}