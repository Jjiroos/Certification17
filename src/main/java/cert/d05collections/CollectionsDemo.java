package cert.d05collections;

import java.util.ArrayList;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 * Domaine 5 — List / Set / Map / Deque et leurs ordres.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d05collections.CollectionsDemo
 */
public class CollectionsDemo {
    public static void main(String[] args) {
        // --- List : ordonnée, doublons autorisés ---
        List<String> list = new ArrayList<>(List.of("b", "a", "b"));
        list.add("c");
        System.out.println(list);            // [b, a, b, c]
        System.out.println(list.get(1));     // a
        System.out.println(list.indexOf("b")); // 0
        list.remove("b");                     // retire la 1re occurrence (par VALEUR)
        System.out.println(list);            // [a, b, c]
        list.remove(0);                       // retire par INDEX
        System.out.println(list);            // [b, c]

        // --- Set : pas de doublons ---
        Set<Integer> hash = new HashSet<>(List.of(3, 1, 2, 2));
        System.out.println(hash.size());      // 3
        TreeSet<Integer> tree = new TreeSet<>(List.of(3, 1, 2));
        System.out.println(tree);             // [1, 2, 3] (trié)
        LinkedHashSet<Integer> linked = new LinkedHashSet<>(List.of(3, 1, 2));
        System.out.println(linked);           // [3, 1, 2] (ordre d'insertion)

        // --- Map : clés uniques ---
        Map<String, Integer> map = new TreeMap<>();
        map.put("b", 2);
        map.put("a", 1);
        map.put("a", 10);                     // écrase la valeur précédente
        System.out.println(map);             // {a=10, b=2} (TreeMap trié par clé)
        System.out.println(map.get("a"));    // 10
        System.out.println(map.containsKey("c")); // false

        // --- Deque comme pile (LIFO) ---
        Deque<Integer> pile = new ArrayDeque<>();
        pile.push(1); pile.push(2); pile.push(3);
        System.out.println(pile.pop());       // 3
        System.out.println(pile.peek());      // 2
    }
}
