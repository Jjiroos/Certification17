package cert.d05collections;

import java.util.ArrayList;
import java.util.List;

/**
 * Domaine 5 — Génériques & wildcards bornés (règle PECS).
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d05collections.GenericsDemo
 */
public class GenericsDemo {

    // Producer Extends : on LIT depuis la liste
    static double somme(List<? extends Number> nums) {
        double total = 0;
        for (Number n : nums) total += n.doubleValue();
        return total;
    }

    // Consumer Super : on ÉCRIT dans la liste
    static void ajouterEntiers(List<? super Integer> dst) {
        dst.add(1);
        dst.add(2);
    }

    // Méthode générique
    static <T> T premier(List<T> list) { return list.get(0); }

    public static void main(String[] args) {
        System.out.println(somme(List.of(1, 2, 3)));  // 6.0
        System.out.println(somme(List.of(1.5, 2.5)));  // 4.0

        List<Number> nums = new ArrayList<>();
        ajouterEntiers(nums);            // List<Number> accepte ? super Integer
        System.out.println(nums);        // [1, 2]

        System.out.println(premier(List.of("x", "y"))); // x

        // ? extends => lecture seule (pas d'add, sauf null)
        List<? extends Number> ro = List.of(1, 2);
        // ro.add(3); // NE COMPILE PAS
        System.out.println(ro.size());   // 2
    }
}
