package cert.d06streams;

import java.util.List;
import java.util.OptionalDouble;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * Domaine 6 — Streams primitifs, flatMap, paresse & court-circuit.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d06streams.PrimitiveStreamAndFlatMapDemo
 */
public class PrimitiveStreamAndFlatMapDemo {
    public static void main(String[] args) {
        // range (borne sup EXCLUE) vs rangeClosed (incluse)
        System.out.println(IntStream.range(1, 5).sum());       // 10  (1+2+3+4)
        System.out.println(IntStream.rangeClosed(1, 5).sum()); // 15  (1+...+5)

        // average -> OptionalDouble
        OptionalDouble moy = IntStream.rangeClosed(1, 4).average();
        System.out.println(moy.getAsDouble()); // 2.5

        // mapToInt depuis des objets
        List<String> mots = List.of("a", "bb", "ccc");
        System.out.println(mots.stream().mapToInt(String::length).sum()); // 6

        // boxed : IntStream -> Stream<Integer>
        List<Integer> liste = IntStream.rangeClosed(1, 3).boxed().collect(Collectors.toList());
        System.out.println(liste); // [1, 2, 3]

        // flatMap : aplatir
        List<List<Integer>> imbrique = List.of(List.of(1, 2), List.of(3, 4), List.of(5));
        List<Integer> plat = imbrique.stream()
                .flatMap(List::stream)
                .collect(Collectors.toList());
        System.out.println(plat); // [1, 2, 3, 4, 5]

        // Paresse + court-circuit : peek ne voit que les éléments réellement évalués
        System.out.print("evalues: ");
        int premier = IntStream.rangeClosed(1, 100)
                .peek(x -> System.out.print(x + " "))
                .filter(x -> x % 3 == 0)
                .findFirst()
                .getAsInt();
        System.out.println("-> " + premier); // evalues: 1 2 3 -> 3
    }
}
