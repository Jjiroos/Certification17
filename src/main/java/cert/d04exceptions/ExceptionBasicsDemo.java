package cert.d04exceptions;

/**
 * Domaine 4 — try / catch / finally, ordre des catch.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d04exceptions.ExceptionBasicsDemo
 */
public class ExceptionBasicsDemo {
    public static void main(String[] args) {
        // --- finally s'exécute TOUJOURS, même avec return dans le try ---
        System.out.println(avecFinally());
        // finally
        // 2

        // --- un return dans finally ÉCRASE celui du try ---
        System.out.println(returnDansFinally()); // 99

        // --- ordre des catch : du plus spécifique au plus général ---
        try {
            String s = null;
            s.length(); // NullPointerException
        } catch (NullPointerException e) {
            System.out.println("NPE attrapee");
        } catch (RuntimeException e) {
            System.out.println("RuntimeException");
        }
        // NPE attrapee

        // --- exception attrapée par son type exact ---
        try {
            Integer.parseInt("abc");
        } catch (NumberFormatException e) {
            System.out.println("NFE: " + e.getMessage());
        }
        // NFE: For input string: "abc"
    }

    static int avecFinally() {
        try {
            return 2;
        } finally {
            System.out.println("finally"); // s'exécute avant le return effectif
        }
    }

    static int returnDansFinally() {
        try {
            return 1;
        } finally {
            return 99; // écrase le return du try (mauvaise pratique)
        }
    }
}
