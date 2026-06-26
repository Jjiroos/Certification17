package cert.d09io;

import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Path;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Exercices Domaine 9 — I/O & NIO.2. PRÉDIS sans lancer, puis mvn test.
 * 🟢 N1   🟡 N2   🔴 N3
 */
class D09IoExercises {

    @Test // 🟢 N1
    void fileName() {
        String reponse = "?"; // TODO
        assertEquals(reponse, Path.of("/a/b/c.txt").getFileName().toString());
    }

    @Test // 🟢 N1
    void nameCount() {
        int reponse = -1; // TODO
        assertEquals(reponse, Path.of("/a/b/c.txt").getNameCount());
    }

    @Test // 🟡 N2
    void resolveRelatif() {
        String reponse = "?"; // TODO
        assertEquals(reponse, Path.of("/home").resolve("user/file").toString());
    }

    @Test // 🟡 N2 — resolve avec un chemin ABSOLU
    void resolveAbsolu() {
        String reponse = "?"; // TODO
        assertEquals(reponse, Path.of("/home/user").resolve("/etc/x").toString());
    }

    @Test // 🟡 N2
    void relativize() {
        String reponse = "?"; // TODO
        assertEquals(reponse, Path.of("/a/b").relativize(Path.of("/a/b/c/d")).toString());
    }

    @Test // 🔴 N3
    void normalize() {
        String reponse = "?"; // TODO
        assertEquals(reponse, Path.of("/a/b/../c/./d").normalize().toString());
    }

    @Test // 🟡 N2
    void parent() {
        String reponse = "?"; // TODO
        assertEquals(reponse, Path.of("/a/b/c").getParent().toString());
    }

    @Test // 🔴 N3 — un champ transient n'est pas restauré
    void transientNull() throws Exception {
        String reponse = "?"; // TODO : valeur du champ transient après round-trip
        assertEquals(reponse, roundTripTransient());
    }

    static class Box implements Serializable {
        private static final long serialVersionUID = 1L;
        String permanent;
        transient String temporaire;
    }

    static String roundTripTransient() throws Exception {
        Box b = new Box();
        b.permanent = "garde";
        b.temporaire = "perdu";
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try (ObjectOutputStream oos = new ObjectOutputStream(baos)) { oos.writeObject(b); }
        try (ObjectInputStream ois = new ObjectInputStream(
                new ByteArrayInputStream(baos.toByteArray()))) {
            Box r = (Box) ois.readObject();
            return r.temporaire; // transient -> non restauré
        }
    }
}
