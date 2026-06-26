package cert.d02flow;

/**
 * Domaine 2 — if / else, dangling else, ternaire.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d02flow.IfElseDemo
 */
public class IfElseDemo {
    public static void main(String[] args) {
        int x = 5;

        // --- Dangling else : le else va avec le if le PLUS PROCHE ---
        if (x > 0)
            if (x > 10)
                System.out.println("grand");
            else
                System.out.println("petit positif"); // rattaché à "if (x > 10)"
        System.out.println("---");
        // petit positif
        // ---

        // --- Sans accolades : une SEULE instruction appartient au if ---
        int compteur = 0;
        if (x == 5)
            compteur++;
        // la ligne ci-dessous N'EST PAS dans le if (juste indentée)
        System.out.println(compteur); // 1

        // --- Ternaire ---
        String parite = (x % 2 == 0) ? "pair" : "impair";
        System.out.println(parite); // impair

        // --- La condition DOIT être boolean (pas un int comme en C) ---
        // if (x) { }  // NE COMPILE PAS
        boolean ok = (x == 5);
        if (ok) System.out.println("ok vaut true"); // ok vaut true
    }
}
