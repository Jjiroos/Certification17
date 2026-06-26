package cert.d06streams;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Domaine 6 — Collectors : joining, groupingBy, partitioningBy, toMap.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d06streams.CollectorsDemo
 */
public class CollectorsDemo {
    public static void main(String[] args) {
        List<String> mots = List.of("pomme", "poire", "kiwi", "banane", "abricot");

        // joining avec préfixe / suffixe
        String joint = mots.stream().collect(Collectors.joining(", ", "[", "]"));
        System.out.println(joint); // [pomme, poire, kiwi, banane, abricot]

        // groupingBy par longueur
        Map<Integer, List<String>> parLongueur = mots.stream()
                .collect(Collectors.groupingBy(String::length));
        System.out.println(parLongueur);
        // {4=[kiwi], 5=[pomme, poire], 6=[banane], 7=[abricot]}

        // groupingBy + counting (downstream)
        Map<Integer, Long> compte = mots.stream()
                .collect(Collectors.groupingBy(String::length, Collectors.counting()));
        System.out.println(compte); // {4=1, 5=2, 6=1, 7=1}

        // partitioningBy : toujours les clés true ET false
        Map<Boolean, List<String>> partition = mots.stream()
                .collect(Collectors.partitioningBy(s -> s.length() > 5));
        System.out.println(partition);
        // {false=[pomme, poire, kiwi], true=[banane, abricot]}

        // toMap
        Map<String, Integer> longueurs = mots.stream()
                .collect(Collectors.toMap(s -> s, String::length));
        System.out.println(longueurs.get("kiwi")); // 4
    }
}
