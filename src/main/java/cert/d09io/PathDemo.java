package cert.d09io;

import java.nio.file.Path;

/**
 * Domaine 9 — Manipulation de Path (sans accès au disque).
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d09io.PathDemo
 */
public class PathDemo {
    public static void main(String[] args) {
        Path p = Path.of("/home/user/docs/file.txt");

        System.out.println(p.getFileName());  // file.txt
        System.out.println(p.getParent());     // /home/user/docs
        System.out.println(p.getName(0));      // home  (index 0)
        System.out.println(p.getNameCount());  // 4

        // resolve : concatène un sous-chemin
        Path base = Path.of("/home/user");
        System.out.println(base.resolve("docs/a.txt")); // /home/user/docs/a.txt

        // resolve avec un chemin ABSOLU -> remplace tout
        System.out.println(base.resolve("/etc/conf"));   // /etc/conf

        // relativize : chemin relatif entre deux Path
        Path a = Path.of("/home/user");
        Path b = Path.of("/home/user/docs/a.txt");
        System.out.println(a.relativize(b));   // docs/a.txt

        // normalize : élimine . et ..
        System.out.println(Path.of("/home/user/../user/./docs").normalize()); // /home/user/docs
    }
}
