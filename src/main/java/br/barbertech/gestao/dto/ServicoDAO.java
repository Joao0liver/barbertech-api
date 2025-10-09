package br.barbertech.gestao.dto;

import br.barbertech.gestao.entity.Conexao;
import br.barbertech.gestao.entity.Servicos;

import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ServicoDAO {

    public void cadastrarServico(Servicos servico) {
        String sql = "INSERT INTO servico (nome_servico, preco_c_servico, preco_v_servico, status_servico) VALUES (?, ?, ?, ?)";

        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setString(1, servico.getNome_servico());
            ps.setDouble(2, servico.getPreco_c_servico());
            ps.setDouble(3, servico.getPreco_v_servico());
            ps.setBoolean(4, servico.getStatus_servico());

            ps.execute();
            ps.close();

        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public void deletarServicoPorId(int id) {
        String sql = "DELETE FROM servico WHERE id_servico = ?";
        PreparedStatement ps = null;

        try {
            ps = Conexao.getConexao().prepareStatement(sql);
            ps.setInt(1, id);

            int linhasAfetadas = ps.executeUpdate();
            if (linhasAfetadas > 0) {
                System.out.println("Serviço deletado!");
            } else {
                System.out.println("Sem ID.");
            }

            ps.execute();
            ps.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
