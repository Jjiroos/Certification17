package cert.d01types;

/**
 * Domaine 1 — Primitives, wrappers, autoboxing, littéraux.
 *
 * Lance avec :
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d01types.PrimitivesAndWrappersDemo
 */
public class PrimitivesAndWrappersDemo {
    public static void main(String[] args) {
        // --- Littéraux ---
        int million = 1_000_000;     // underscores autorisés ENTRE des chiffres
        int hexa = 0xFF;             // 255
        int binaire = 0b1010;        // 10
        int octal = 010;             // 8  (🪤 préfixe 0 = OCTAL, pas décimal !)
        System.out.println(million + " " + hexa + " " + binaire + " " + octal);
        // 1000000 255 10 8

        // --- Débordement silencieux : aucune exception ---
        int max = Integer.MAX_VALUE;  // 2147483647
        System.out.println(max + 1);  // -2147483648  (wrap-around)

        // --- Autoboxing + cache des Integer (valeurs -128..127) ---
        Integer a = 127, b = 127;
        Integer c = 128, d = 128;
        System.out.println(a == b);       // true  -> même objet (cache)
        System.out.println(c == d);       // false -> deux objets distincts
        System.out.println(c.equals(d));  // true  -> compare les valeurs

        // --- Parsing ---
        int n = Integer.parseInt("42");        // -> primitive int
        Integer boxed = Integer.valueOf("42"); // -> objet Integer
        System.out.println(n + " " + boxed);   // 42 42
        // Integer.parseInt("4.2"); // lèverait NumberFormatException

        // --- Unboxing d'un wrapper null -> NullPointerException ---
        Integer maybe = null;
        try {
            int x = maybe;   // unboxing de null
            System.out.println(x);
        } catch (NullPointerException e) {
            System.out.println("NPE à l'unboxing d'un Integer null");
        }
    }
}
