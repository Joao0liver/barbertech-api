package br.barbertech.gestao.entity;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexao {

    private static final String url = "jdbc:mysql://localhost:3306/bt_database";
    private static final String user = "root";
    private static final String pass = "";

    private static Connection conn;

    public static Connection getConexao() throws SQLException {

        try{
            if(conn == null){
                conn = DriverManager.getConnection(url, user, pass);
                return conn;
            }else{
                return conn;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }


    }

}
