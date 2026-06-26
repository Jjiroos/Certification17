package cert.d09io;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

/**
 * Domaine 9 — Sérialisation et champ transient.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d09io.SerializationDemo
 */
public class SerializationDemo {

    static class Compte implements Serializable {
        private static final long serialVersionUID = 1L;
        String titulaire;
        transient String motDePasse;  // NON sérialisé
        int solde;
        Compte(String t, String mdp, int s) { titulaire = t; motDePasse = mdp; solde = s; }
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        Compte original = new Compte("Alice", "secret", 100);

        // sérialiser en mémoire
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) {
            oos.writeObject(original);
        }

        // désérialiser
        Compte copie;
        try (ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(baos.toByteArray()))) {
            copie = (Compte) ois.readObject();
        }

        System.out.println(copie.titulaire);  // Alice
        System.out.println(copie.solde);       // 100
        System.out.println(copie.motDePasse);  // null (transient non restauré)
    }
}
