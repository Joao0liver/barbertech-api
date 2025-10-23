package br.barbertech.gestao.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@RestController
public class conexaoController {
    @GetMapping("/conexao")
    public String testarConexao() {
        // Dados da conexão
        String url = "jdbc:mysql://localhost:3306/bt_database";
        String usuario = "root";
        String senha = "";

        try {
            Connection conexao = DriverManager.getConnection(url, usuario, senha);
            conexao.close();
            return "Conectado";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Erro: " + e.getMessage();
        }
    }
}
