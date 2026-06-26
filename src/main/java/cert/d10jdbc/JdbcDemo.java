package cert.d10jdbc;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Domaine 10 — JDBC (API standard java.sql) sur une base H2 en mémoire.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d10jdbc.JdbcDemo
 */
public class JdbcDemo {
    public static void main(String[] args) throws SQLException {
        String url = "jdbc:h2:mem:certdb;DB_CLOSE_DELAY=-1";

        try (Connection con = DriverManager.getConnection(url, "sa", "")) {

            // DDL + INSERT via Statement ; executeUpdate renvoie le nombre de lignes
            try (Statement st = con.createStatement()) {
                st.execute("CREATE TABLE personne (id INT PRIMARY KEY, nom VARCHAR(50), age INT)");
                int n = st.executeUpdate(
                        "INSERT INTO personne VALUES (1, 'Alice', 30), (2, 'Bob', 25)");
                System.out.println("lignes inserees: " + n); // 2
            }

            // SELECT via PreparedStatement (paramètres 1-based)
            try (PreparedStatement ps = con.prepareStatement(
                    "SELECT nom, age FROM personne WHERE age >= ? ORDER BY age")) {
                ps.setInt(1, 26);
                try (ResultSet rs = ps.executeQuery()) {
                    while (rs.next()) {
                        // colonnes accessibles par NOM ou par INDEX (1-based)
                        System.out.println(rs.getString("nom") + " " + rs.getInt(2));
                    }
                }
            }
            // Alice 30

            // UPDATE
            try (PreparedStatement ps = con.prepareStatement(
                    "UPDATE personne SET age = age + 1")) {
                System.out.println("maj: " + ps.executeUpdate()); // 2
            }

            // COUNT via ResultSet
            try (Statement st = con.createStatement();
                 ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM personne")) {
                rs.next();
                System.out.println("total: " + rs.getInt(1)); // 2
            }
        }
    }
}
