package org.lessons.java.nations;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/nations";
        String user = "root";
        String password = "password";

        Connection connection = null;
        try { connection = DriverManager.getConnection(url,user,password)
            //  stampo il catalogo del DB
            System.out.print(connection.getCatalog());
//            preparo lo statement da eseguire
            String sql = "";
            try(PreparedStatement ps = connection.PrepareStatement(sql)){
                try(ResultSet rs = ps.executeQuery()){
                    // itero sulle righe del resulset
                    while(rs.next()){
                        // prendo i valori dalle singole colonne
                        String code = rs.getString("");
                        String cane = rs.getString("");


                    }
                }
            }
        }catch(SQLException e){
            System.out.print("enable to connect db");
            e.printStackTrace();
        }
    }
}
