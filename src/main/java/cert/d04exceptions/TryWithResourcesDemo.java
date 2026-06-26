package cert.d04exceptions;

/**
 * Domaine 4 — try-with-resources : fermeture auto, ordre inverse.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d04exceptions.TryWithResourcesDemo
 */
public class TryWithResourcesDemo {

    static class Resource implements AutoCloseable {
        private final String nom;
        Resource(String nom) { this.nom = nom; System.out.println("ouvre " + nom); }
        void use() { System.out.println("utilise " + nom); }
        public void close() { System.out.println("ferme " + nom); }
    }

    public static void main(String[] args) {
        // Fermeture dans l'ORDRE INVERSE de l'ouverture
        try (Resource a = new Resource("A");
             Resource b = new Resource("B")) {
            a.use();
            b.use();
        }
        // ouvre A
        // ouvre B
        // utilise A
        // utilise B
        // ferme B
        // ferme A

        System.out.println("---");

        // close() est appelé AVANT le catch
        try (Resource r = new Resource("R")) {
            throw new RuntimeException("boom");
        } catch (RuntimeException e) {
            System.out.println("catch: " + e.getMessage());
        }
        // ouvre R
        // ferme R
        // catch: boom
    }
}
