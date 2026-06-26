package cert.d05collections;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Exercices Domaine 5 — Tableaux & collections. PRÉDIS sans lancer, puis mvn test.
 * 🟢 N1   🟡 N2   🔴 N3
 * Pour les assertThrows : remplace Error.class par la classe d'exception PRÉCISE.
 */
class D05CollectionsExercises {

    @Test // 🟢 N1
    void longueurTableau() {
        int[] t = {10, 20, 30};
        int reponse = -1; // TODO : t.length
        assertEquals(reponse, t.length);
    }

    @Test // 🟢 N1
    void setSansDoublon() {
        Set<Integer> s = new HashSet<>(List.of(1, 1, 2, 3, 3, 3));
        int reponse = -1; // TODO : s.size()
        assertEquals(reponse, s.size());
    }

    @Test // 🟢 N1
    void treeSetTrie() {
        TreeSet<Integer> s = new TreeSet<>(List.of(5, 1, 3));
        int reponse = -1; // TODO : s.first()
        assertEquals(reponse, s.first());
    }

    @Test // 🟡 N2 — remove(int) = par INDEX
    void removeParIndex() {
        List<Integer> list = new ArrayList<>(List.of(10, 20, 30));
        list.remove(1);
        String reponse = "?"; // TODO : list.toString()
        assertEquals(reponse, list.toString());
    }

    @Test // 🟡 N2 — remove(Object) = par VALEUR
    void removeParValeur() {
        List<Integer> list = new ArrayList<>(List.of(10, 20, 30));
        list.remove(Integer.valueOf(20));
        String reponse = "?"; // TODO : list.toString()
        assertEquals(reponse, list.toString());
    }

    @Test // 🟡 N2
    void mapMerge() {
        Map<String, Integer> m = new HashMap<>();
        m.put("x", 3);
        m.merge("x", 7, Integer::sum);
        int reponse = -1; // TODO : m.get("x")
        assertEquals(reponse, m.get("x"));
    }

    @Test // 🟡 N2
    void getOrDefault() {
        Map<String, Integer> m = new HashMap<>();
        m.put("a", 1);
        int reponse = -1; // TODO : m.getOrDefault("b", 42)
        assertEquals(reponse, m.getOrDefault("b", 42));
    }

    @Test // 🟡 N2
    void comparatorParLongueur() {
        List<String> mots = new ArrayList<>(List.of("bb", "a", "ccc"));
        mots.sort(Comparator.comparingInt(String::length));
        String reponse = "?"; // TODO : mots.get(0)
        assertEquals(reponse, mots.get(0));
    }

    @Test // 🔴 N3 — binarySearch d'un absent
    void binarySearchAbsent() {
        int[] t = {1, 3, 5, 7};
        int reponse = 0; // TODO : Arrays.binarySearch(t, 4)
        assertEquals(reponse, Arrays.binarySearch(t, 4));
    }

    @Test // 🔴 N3 — modification d'une collection immuable
    void modifierImmuable() {
        List<Integer> l = List.of(1, 2, 3);
        assertThrows(Error.class, () -> l.add(4)); // TODO : quelle exception ?
    }

    @Test // 🔴 N3
    void computeIfAbsent() {
        Map<String, List<Integer>> m = new HashMap<>();
        m.computeIfAbsent("k", x -> new ArrayList<>()).add(1);
        m.computeIfAbsent("k", x -> new ArrayList<>()).add(2);
        int reponse = -1; // TODO : m.get("k").size()
        assertEquals(reponse, m.get("k").size());
    }

    @Test // 🔴 N3 — supprimer pendant un for-each
    void modificationPendantIteration() {
        List<Integer> l = new ArrayList<>(List.of(10, 20, 30, 40));
        assertThrows(Error.class, () -> {     // TODO : quelle exception ?
            for (Integer x : l) {
                if (x == 10) l.remove(x);
            }
        });
    }
}
