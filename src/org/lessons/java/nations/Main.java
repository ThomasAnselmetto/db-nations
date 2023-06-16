package org.lessons.java.nations;

import java.sql.*;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        String url = "jdbc:mysql://localhost:3306/nations";
        String user = "root";
        String password = "";


        try (Connection connection = DriverManager.getConnection(url,user,password)){
            //  stampo il catalogo del DB
            System.out.println(connection.getCatalog());
//            preparo lo statement da eseguire
            Scanner scanner = new Scanner(System.in);


            String query = """
                            SELECT c.name AS country_name, c.country_id, r.name AS region_name, cn.name AS continent_name
                            FROM countries c
                            JOIN regions r ON c.region_id = r.region_id
                            JOIN continents cn ON r.continent_id = cn.continent_id
                            WHERE c.name LIKE ?
                            ORDER BY c.name
                            """;

            try(PreparedStatement ps = connection.prepareStatement(query)){
                System.out.println("che ti sto chiedendo?");

                String inpututente = scanner.nextLine();
                ps.setString(1,"%" + inpututente + "%");

                try(ResultSet rs = ps.executeQuery()){
                    // itero sulle righe del resulset
                    while(rs.next()){
                        // prendo i valori dalle singole colonne
                        String name = rs.getString("country_name");
                        String country_id = rs.getString("country_id");
                        String region_name = rs.getString("region_name");
                        String continent_name = rs.getString("continent_name");

                        System.out.println("Nome Nation: " + name);
                        System.out.println("ID Nation: " + country_id);
                        System.out.println("Nome Region: " + region_name);
                        System.out.println("Nome Continent: " + continent_name);
                        System.out.println("**************************");
                    }
                }
            }
        }catch(SQLException e){
            System.out.print("Enable to connect to DataBase 'Nations'");
            e.printStackTrace();
        }
    }
}
