package cert.d06streams;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Exercices Domaine 6 — Streams & lambdas. PRÉDIS sans lancer, puis mvn test.
 * 🟢 N1   🟡 N2   🔴 N3
 * Pour assertThrows : remplace Error.class par la classe d'exception PRÉCISE.
 */
class D06StreamsExercises {

    @Test // 🟢 N1
    void filterCount() {
        long reponse = -1; // TODO : nombre de pairs dans 1..10
        assertEquals(reponse, IntStream.rangeClosed(1, 10).filter(x -> x % 2 == 0).count());
    }

    @Test // 🟢 N1
    void rangeSum() {
        int reponse = -1; // TODO : IntStream.range(1, 5).sum()
        assertEquals(reponse, IntStream.range(1, 5).sum());
    }

    @Test // 🟢 N1
    void reduceSum() {
        int reponse = -1; // TODO : somme de [2, 4, 6]
        assertEquals(reponse, Stream.of(2, 4, 6).reduce(0, Integer::sum));
    }

    @Test // 🟡 N2
    void mapJoining() {
        String reponse = "?"; // TODO
        assertEquals(reponse, Stream.of("a", "b", "c")
                .map(String::toUpperCase)
                .collect(Collectors.joining("-")));
    }

    @Test // 🟡 N2
    void distinctSorted() {
        List<Integer> r = Stream.of(3, 1, 2, 3, 1).distinct().sorted().collect(Collectors.toList());
        String reponse = "?"; // TODO : r.toString()
        assertEquals(reponse, r.toString());
    }

    @Test // 🟡 N2
    void findFirstOrElse() {
        int reponse = 0; // TODO : aucun élément > 5
        assertEquals(reponse, Stream.of(1, 2, 3).filter(x -> x > 5).findFirst().orElse(-99));
    }

    @Test // 🟡 N2
    void groupingByCount() {
        Map<Integer, Long> m = Stream.of("a", "bb", "cc", "ddd")
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));
        long reponse = -1; // TODO : m.get(2)
        assertEquals(reponse, m.get(2));
    }

    @Test // 🟡 N2
    void flatMapCount() {
        long reponse = -1; // TODO
        assertEquals(reponse, Stream.of(List.of(1, 2), List.of(3, 4, 5))
                .flatMap(List::stream).count());
    }

    @Test // 🔴 N3 — stream vide
    void allMatchVide() {
        boolean reponse = false; // TODO : allMatch sur un stream vide
        assertEquals(reponse, Stream.<Integer>empty().allMatch(x -> x > 0));
    }

    @Test // 🔴 N3 — stream infini borné
    void iterateLimit() {
        List<Integer> r = Stream.iterate(2, x -> x * 2).limit(4).collect(Collectors.toList());
        String reponse = "?"; // TODO : r.toString()
        assertEquals(reponse, r.toString());
    }

    @Test // 🔴 N3 — usage unique
    void streamUsageUnique() {
        Stream<Integer> s = Stream.of(1, 2, 3);
        s.count();
        assertThrows(Error.class, () -> s.count()); // TODO : quelle exception ?
    }

    @Test // 🔴 N3 — partitioningBy
    void partitioning() {
        Map<Boolean, List<Integer>> m = Stream.of(1, 2, 3, 4)
                .collect(Collectors.partitioningBy(x -> x % 2 == 0));
        String reponse = "?"; // TODO : m.get(true).toString()
        assertEquals(reponse, m.get(true).toString());
    }
}
