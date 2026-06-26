package cert.d04exceptions;

import java.io.IOException;

/**
 * Domaine 4 — multi-catch et propagation.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d04exceptions.MultiCatchDemo
 */
public class MultiCatchDemo {
    public static void main(String[] args) {
        // multi-catch : un seul bloc pour plusieurs types NON liés par héritage
        for (int cas = 0; cas < 2; cas++) {
            try {
                if (cas == 0) throw new IllegalArgumentException("IAE");
                else throw new IllegalStateException("ISE");
            } catch (IllegalArgumentException | IllegalStateException e) {
                // e est implicitement final ; type = supertype commun (RuntimeException)
                System.out.println("multi: " + e.getMessage());
            }
        }
        // multi: IAE
        // multi: ISE

        // exception checked propagée puis attrapée
        try {
            methodeQuiLance();
        } catch (IOException e) {
            System.out.println("IO: " + e.getMessage());
        }
        // IO: fichier
    }

    static void methodeQuiLance() throws IOException {
        throw new IOException("fichier");
    }
}
