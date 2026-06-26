package cert.d01types;

/**
 * Domaine 1 — Promotion numérique, casting, opérateurs.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d01types.NumericPromotionAndOperatorsDemo
 */
public class NumericPromotionAndOperatorsDemo {
    public static void main(String[] args) {
        // --- Promotion : byte/short/char -> int dans une expression ---
        byte b1 = 10, b2 = 20;
        int somme = b1 + b2;            // b1 + b2 est un int
        // byte mauvais = b1 + b2;      // NE COMPILE PAS (int -> byte implicite interdit)
        System.out.println(somme);     // 30

        // --- Affectation composée : cast implicite inclus ---
        byte b = 10;
        b += 5;   // équivaut à b = (byte)(b + 5);  -> compile
        b *= 2;   // idem
        System.out.println(b);         // 30

        // --- Division entière tronquée vs flottante ---
        System.out.println(7 / 2);     // 3   (division entière)
        System.out.println(7 % 2);     // 1   (modulo)
        System.out.println(7.0 / 2);   // 3.5 (un opérande double -> double)

        // --- Division par zéro ---
        System.out.println(5.0 / 0);   // Infinity
        System.out.println(0.0 / 0);   // NaN
        try {
            System.out.println(5 / 0); // ArithmeticException: / by zero
        } catch (ArithmeticException e) {
            System.out.println("Exception: " + e.getMessage());
        }

        // --- Arithmétique sur char ---
        char ch = 'a';                 // 97
        System.out.println(ch + 1);    // 98  (int, PAS 'b' !)
        ch++;                          // 'b'
        System.out.println(ch);        // b

        // --- Pré / post incrément ---
        int i = 5;
        System.out.println(i++);       // 5 (affiche PUIS incrémente)
        System.out.println(++i);       // 7 (incrémente PUIS affiche)

        // --- Court-circuit avec && ---
        int[] arr = null;
        if (arr != null && arr.length > 0) { // && court-circuite -> pas de NPE
            System.out.println("non vide");
        } else {
            System.out.println("court-circuit ok");
        }
    }
}
