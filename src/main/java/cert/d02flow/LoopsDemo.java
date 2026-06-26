package cert.d02flow;

import java.util.List;

/**
 * Domaine 2 — while, do-while, for, for-each, break/continue.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d02flow.LoopsDemo
 */
public class LoopsDemo {
    public static void main(String[] args) {
        // --- while : teste AVANT ---
        int i = 0;
        while (i < 3) {
            System.out.print(i);
            i++;
        }
        System.out.println(); // 012

        // --- do-while : s'exécute AU MOINS une fois ---
        int j = 5;
        do {
            System.out.print(j);
            j++;
        } while (j < 3);
        System.out.println(); // 5

        // --- for : plusieurs variables et plusieurs mises à jour ---
        for (int k = 0, max = 6; k < max; k += 2) {
            System.out.print(k);
        }
        System.out.println(); // 024

        // --- for-each ---
        for (var s : List.of("a", "b", "c")) {
            System.out.print(s);
        }
        System.out.println(); // abc

        // --- break / continue ---
        StringBuilder sb = new StringBuilder();
        for (int x = 0; x < 5; x++) {
            if (x == 2) continue; // saute la valeur 2
            if (x == 4) break;    // arrête avant 4
            sb.append(x);
        }
        System.out.println(sb); // 013
    }
}
