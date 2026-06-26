package cert.d01types;

/**
 * Domaine 1 — String : immutabilité, pool, méthodes.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d01types.StringDemo
 */
public class StringDemo {
    public static void main(String[] args) {
        // --- Immutabilité : les méthodes renvoient une NOUVELLE chaîne ---
        String s = "Bonjour";
        s.toUpperCase();               // résultat ignoré -> s inchangée
        System.out.println(s);         // Bonjour
        s = s.toUpperCase();           // il faut réaffecter
        System.out.println(s);         // BONJOUR

        // --- Pool de chaînes et == ---
        String a = "java";
        String b = "java";               // même littéral -> même objet du pool
        String c = new String("java");   // new -> nouvel objet HORS pool
        System.out.println(a == b);          // true
        System.out.println(a == c);          // false
        System.out.println(a.equals(c));     // true
        System.out.println(a == c.intern()); // true (intern() ramène au pool)

        // --- Concaténation : évaluée de gauche à droite ---
        System.out.println(1 + 2 + "x");   // 3x
        System.out.println("x" + 1 + 2);   // x12

        // --- Méthodes courantes (indices à partir de 0) ---
        String t = "Hello World";
        System.out.println(t.length());          // 11
        System.out.println(t.charAt(0));         // H
        System.out.println(t.indexOf("o"));      // 4
        System.out.println(t.indexOf("o", 5));   // 7
        System.out.println(t.substring(6));      // World
        System.out.println(t.substring(0, 5));   // Hello   (indice de fin EXCLU)
        System.out.println(t.replace("l", "L")); // HeLLo WorLd
        System.out.println("  trim  ".strip());  // trim
        System.out.println(t.contains("World")); // true

        // --- substring hors limites -> exception ---
        try {
            t.substring(20);
        } catch (StringIndexOutOfBoundsException e) {
            System.out.println("substring hors limites");
        }

        // --- Bloc de texte (Java 15+) ---
        String json = """
                {
                  "k": "v"
                }""";
        System.out.println(json);
        // {
        //   "k": "v"
        // }
    }
}
