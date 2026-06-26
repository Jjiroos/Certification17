package cert.d05collections;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Domaine 5 — Collections immuables & méthodes utiles de Map.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d05collections.MapMethodsAndImmutableDemo
 */
public class MapMethodsAndImmutableDemo {
    public static void main(String[] args) {
        // --- Collections immuables : modification -> UnsupportedOperationException ---
        List<Integer> immuable = List.of(1, 2, 3);
        try {
            immuable.add(4);
        } catch (UnsupportedOperationException e) {
            System.out.println("immuable: add interdit");
        }
        // List.of refuse null
        try {
            List.of("a", null);
        } catch (NullPointerException e) {
            System.out.println("List.of refuse null");
        }

        // --- Méthodes utiles de Map ---
        Map<String, Integer> stock = new HashMap<>();
        stock.put("pomme", 5);

        System.out.println(stock.getOrDefault("poire", 0)); // 0 (absente)
        stock.putIfAbsent("pomme", 99);                       // ignoré (déjà présente)
        System.out.println(stock.get("pomme"));               // 5
        stock.merge("pomme", 10, Integer::sum);               // 5 + 10
        System.out.println(stock.get("pomme"));               // 15

        // computeIfAbsent : initialise la valeur si la clé est absente
        Map<String, List<Integer>> multi = new HashMap<>();
        multi.computeIfAbsent("a", k -> new ArrayList<>()).add(1);
        multi.computeIfAbsent("a", k -> new ArrayList<>()).add(2);
        System.out.println(multi);  // {a=[1, 2]}

        // removeIf sur une List
        List<Integer> liste = new ArrayList<>(List.of(1, 2, 3, 4, 5));
        liste.removeIf(n -> n % 2 == 0);
        System.out.println(liste);  // [1, 3, 5]
    }
}
