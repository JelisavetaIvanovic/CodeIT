package com.example.demo;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class CreateTables {
   static final String DB_URL = "jdbc:postgresql://localhost:5432/codeIT";
   static final String USER = "postgres";
   static final String PASS = "baza12";

   public static void main(String[] args) {

    try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
         Statement stmt = conn.createStatement();) {

        // Table game
        String sql1 = "CREATE TABLE IF NOT EXISTS GAME " +
                "(id uuid PRIMARY KEY, " +
                " creation_date TIMESTAMP WITHOUT TIME ZONE, " +
                " number_of_armies INTEGER, " +
                " status VARCHAR(255) NOT NULL, " +
                " last_attack_id INTEGER)";

        stmt.executeUpdate(sql1);
        System.out.println("Created game tabel in the codeIT database");

        // Table army
        String sql2 = "CREATE TABLE IF NOT EXISTS ARMY " +
                "(id uuid PRIMARY KEY, " +
                " name VARCHAR(255) NOT NULL, " +
                " strategy VARCHAR(255) NOT NULL, " +
                " initial_units INTEGER, " +
                " current_units INTEGER, " +
                " available_attack_date TIMESTAMP WITHOUT TIME ZONE, " +
                " attack_id INTEGER, " +
                " preinitialized BOOLEAN NOT NULL, " +
                " game_id uuid, " +
                " FOREIGN KEY (game_id) REFERENCES GAME(id))";

        stmt.executeUpdate(sql2);
        System.out.println("Created army tabel in the codeIT database");

        // Table game_log
        String sql3 = "CREATE TABLE IF NOT EXISTS GAME_LOG " +
                "(id uuid PRIMARY KEY, " +
                " text TEXT, " +
                " game_id uuid, " +
                " FOREIGN KEY (game_id) REFERENCES GAME(id))";

        stmt.executeUpdate(sql3);
        System.out.println("Created game_log tabel in the codeIT database");
    } catch (SQLException e) {
        e.printStackTrace();
    }
   }
}
