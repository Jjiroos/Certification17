package cert.d04exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Exercices Domaine 4 — Exceptions. PRÉDIS sans lancer, remplace le TODO, puis mvn test.
 * 🟢 N1   🟡 N2   🔴 N3
 *
 * Pour les assertThrows : remplace IOException.class par la classe d'exception PRÉCISE.
 */
class D04ExceptionsExercises {

    static class R implements AutoCloseable {
        final StringBuilder log; final String n;
        R(StringBuilder log, String n) { this.log = log; this.n = n; }
        public void close() { log.append("c").append(n); }
    }

    static String fermetureOrdre() {
        StringBuilder log = new StringBuilder();
        try (R a = new R(log, "A"); R b = new R(log, "B")) {
            log.append("body");
        }
        return log.toString();
    }

    static int returnFinally() {
        try { return 1; }
        finally { return 2; }
    }

    static String finallyToujours() {
        StringBuilder sb = new StringBuilder();
        try {
            sb.append("t");
            throw new RuntimeException();
        } catch (RuntimeException e) {
            sb.append("c");
        } finally {
            sb.append("f");
        }
        return sb.toString();
    }

    static String multiCatch(int cas) {
        try {
            if (cas == 0) throw new IllegalArgumentException("a");
            throw new NullPointerException("n");
        } catch (IllegalArgumentException | NullPointerException e) {
            return e.getClass().getSimpleName();
        }
    }

    static String catchSpecifique() {
        try {
            Object o = null;
            o.toString();
        } catch (NullPointerException e) {
            return "NPE";
        } catch (RuntimeException e) {
            return "RE";
        }
        return "none";
    }

    static String tryImbrique() {
        StringBuilder sb = new StringBuilder();
        try {
            try {
                sb.append("1");
                throw new RuntimeException();
            } finally {
                sb.append("2");
            }
        } catch (RuntimeException e) {
            sb.append("3");
        }
        return sb.toString();
    }

    // ---- exercices ----

    @Test // 🟢 N1
    void finallyExecuteToujours() {
        String reponse = "?"; // TODO : finallyToujours()
        assertEquals(reponse, finallyToujours());
    }

    @Test // 🟢 N1
    void catchLePlusSpecifique() {
        String reponse = "?"; // TODO : catchSpecifique()
        assertEquals(reponse, catchSpecifique());
    }

    @Test // 🟡 N2
    void returnDansFinally() {
        int reponse = -1; // TODO : returnFinally()
        assertEquals(reponse, returnFinally());
    }

    @Test // 🟡 N2
    void ordreFermeture() {
        String reponse = "?"; // TODO : fermetureOrdre()
        assertEquals(reponse, fermetureOrdre());
    }

    @Test // 🟡 N2
    void multiCatchType() {
        String reponse = "?"; // TODO : multiCatch(1)
        assertEquals(reponse, multiCatch(1));
    }

    @Test // 🔴 N3
    void finallyDansTryImbrique() {
        String reponse = "?"; // TODO : tryImbrique()
        assertEquals(reponse, tryImbrique());
    }

    @Test // 🟢 N1
    void divisionParZero() {
        assertThrows(java.io.IOException.class, // TODO : quelle exception ?
                () -> { int x = 5 / 0; });
    }

    @Test // 🟡 N2
    void indexHorsTableau() {
        int[] t = new int[3];
        assertThrows(java.io.IOException.class, // TODO
                () -> { int x = t[5]; });
    }

    @Test // 🟡 N2
    void castInvalide() {
        Object o = "texte";
        assertThrows(java.io.IOException.class, // TODO
                () -> { Integer i = (Integer) o; });
    }
}
