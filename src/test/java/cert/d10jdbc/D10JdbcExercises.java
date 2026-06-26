package cert.d10jdbc;

import org.junit.jupiter.api.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Exercices Domaine 10 — JDBC (base H2 privée en mémoire). PRÉDIS sans lancer.
 * 🟢 N1   🟡 N2   🔴 N3
 */
class D10JdbcExercises {

    /** Connexion H2 privée avec une table 'item' pré-remplie : (1,a,10)(2,b,20)(3,c,30). */
    private Connection setup() throws SQLException {
        Connection con = DriverManager.getConnection("jdbc:h2:mem:");
        try (Statement st = con.createStatement()) {
            st.execute("CREATE TABLE item (id INT, nom VARCHAR(20), prix INT)");
            st.executeUpdate("INSERT INTO item VALUES (1,'a',10),(2,'b',20),(3,'c',30)");
        }
        return con;
    }

    @Test // 🟢 N1 — executeUpdate renvoie le nombre de lignes affectées
    void insertCount() throws SQLException {
        try (Connection con = setup(); Statement st = con.createStatement()) {
            int n = st.executeUpdate("INSERT INTO item VALUES (4,'d',40)");
            int reponse = -1; // TODO
            assertEquals(reponse, n);
        }
    }

    @Test // 🟢 N1
    void count() throws SQLException {
        try (Connection con = setup();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM item")) {
            rs.next();
            int reponse = -1; // TODO
            assertEquals(reponse, rs.getInt(1));
        }
    }

    @Test // 🟡 N2 — PreparedStatement + paramètre 1-based
    void selectWhere() throws SQLException {
        try (Connection con = setup();
             PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM item WHERE prix > ?")) {
            ps.setInt(1, 15);
            try (ResultSet rs = ps.executeQuery()) {
                rs.next();
                int reponse = -1; // TODO : combien d'items ont un prix > 15 ?
                assertEquals(reponse, rs.getInt(1));
            }
        }
    }

    @Test // 🟡 N2
    void updateCount() throws SQLException {
        try (Connection con = setup(); Statement st = con.createStatement()) {
            int n = st.executeUpdate("UPDATE item SET prix = prix * 2 WHERE prix >= 20");
            int reponse = -1; // TODO : combien de lignes mises à jour ?
            assertEquals(reponse, n);
        }
    }

    @Test // 🟡 N2 — getString par index (1-based)
    void getColonne() throws SQLException {
        try (Connection con = setup();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT nom FROM item WHERE id = 2")) {
            rs.next();
            String reponse = "?"; // TODO : rs.getString(1)
            assertEquals(reponse, rs.getString(1));
        }
    }

    @Test // 🔴 N3
    void sumPrix() throws SQLException {
        try (Connection con = setup();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery("SELECT SUM(prix) FROM item")) {
            rs.next();
            int reponse = -1; // TODO : 10 + 20 + 30
            assertEquals(reponse, rs.getInt(1));
        }
    }

    @Test // 🔴 N3
    void deleteCount() throws SQLException {
        try (Connection con = setup(); Statement st = con.createStatement()) {
            int n = st.executeUpdate("DELETE FROM item WHERE prix < 25");
            int reponse = -1; // TODO : combien de lignes supprimées ?
            assertEquals(reponse, n);
        }
    }
}
