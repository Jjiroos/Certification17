package cert.d01types;

/**
 * Domaine 1 — StringBuilder : mutable et chaînable.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d01types.StringBuilderDemo
 */
public class StringBuilderDemo {
    public static void main(String[] args) {
        // --- Mutable : modifie l'objet ET renvoie le MÊME objet ---
        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" World");           // "Hello World"
        sb.insert(0, ">> ");           // ">> Hello World"
        System.out.println(sb);        // >> Hello World

        // --- Chaînage (chaque méthode renvoie le StringBuilder) ---
        StringBuilder sb2 = new StringBuilder();
        sb2.append("a").append(1).append(true);
        System.out.println(sb2);       // a1true

        // --- delete / deleteCharAt / replace (indice de fin EXCLU) ---
        StringBuilder sb3 = new StringBuilder("0123456789");
        sb3.delete(2, 5);              // supprime indices 2,3,4 -> "0156789"
        System.out.println(sb3);       // 0156789
        sb3.deleteCharAt(0);           // "156789"
        System.out.println(sb3);       // 156789
        sb3.replace(0, 2, "XX");       // remplace indices 0,1 -> "XX6789"
        System.out.println(sb3);       // XX6789

        // --- reverse ---
        System.out.println(new StringBuilder("abc").reverse()); // cba

        // --- Égalité : StringBuilder n'override PAS equals() ---
        StringBuilder x = new StringBuilder("hi");
        StringBuilder y = new StringBuilder("hi");
        System.out.println(x.equals(y));                       // false (référence)
        System.out.println(x.toString().equals(y.toString())); // true

        System.out.println(sb3.length()); // 6
    }
}
