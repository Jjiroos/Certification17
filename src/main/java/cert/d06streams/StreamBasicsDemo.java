package cert.d06streams;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Domaine 6 — Création, filter/map/collect, distinct, sorted, limit/skip, usage unique.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d06streams.StreamBasicsDemo
 */
public class StreamBasicsDemo {
    public static void main(String[] args) {
        List<String> mots = List.of("pomme", "poire", "kiwi", "pomme", "banane");

        // filter + map + collect
        List<String> result = mots.stream()
                .filter(s -> s.length() == 5)
                .map(String::toUpperCase)
                .collect(Collectors.toList());
        System.out.println(result); // [POMME, POIRE, POMME]

        // distinct + sorted
        List<String> uniques = mots.stream()
                .distinct()
                .sorted()
                .collect(Collectors.toList());
        System.out.println(uniques); // [banane, kiwi, poire, pomme]

        // count
        long n = mots.stream().filter(s -> s.startsWith("p")).count();
        System.out.println(n); // 3

        // iterate + limit + skip
        List<Integer> nums = Stream.iterate(1, x -> x + 1)
                .limit(10)
                .skip(7)
                .collect(Collectors.toList());
        System.out.println(nums); // [8, 9, 10]

        // un stream ne se consomme qu'UNE fois
        Stream<String> s = mots.stream();
        s.count();
        try {
            s.count(); // déjà consommé
        } catch (IllegalStateException e) {
            System.out.println("stream deja consomme");
        }
    }
}
