package cert.d09io;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.stream.Stream;

/**
 * Domaine 9 — API Files (lecture/écriture sur un fichier temporaire).
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d09io.FilesDemo
 */
public class FilesDemo {
    public static void main(String[] args) throws IOException {
        Path tmp = Files.createTempFile("cert", ".txt");

        // écrire des lignes
        Files.write(tmp, List.of("ligne1", "ligne2", "ligne3"));

        System.out.println(Files.exists(tmp));   // true
        System.out.println(Files.size(tmp) > 0);  // true

        // lire toutes les lignes
        List<String> lignes = Files.readAllLines(tmp);
        System.out.println(lignes);               // [ligne1, ligne2, ligne3]
        System.out.println(lignes.size());         // 3

        // Files.lines renvoie un Stream (à fermer en try-with-resources)
        try (Stream<String> s = Files.lines(tmp)) {
            long n = s.filter(l -> l.contains("2")).count();
            System.out.println(n);                 // 1
        }

        Files.delete(tmp);
        System.out.println(Files.exists(tmp));     // false
    }
}
