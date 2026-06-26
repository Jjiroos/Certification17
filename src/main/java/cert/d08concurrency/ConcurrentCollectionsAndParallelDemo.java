package cert.d08concurrency;

import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.IntStream;

/**
 * Domaine 8 — ConcurrentHashMap & streams parallèles (résultats déterministes).
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d08concurrency.ConcurrentCollectionsAndParallelDemo
 */
public class ConcurrentCollectionsAndParallelDemo {
    public static void main(String[] args) {
        // ConcurrentHashMap : merge atomique, sûr en parallèle
        ConcurrentHashMap<String, Integer> map = new ConcurrentHashMap<>();
        IntStream.rangeClosed(1, 100).parallel().forEach(i -> {
            map.merge("pair",   i % 2 == 0 ? 1 : 0, Integer::sum);
            map.merge("impair", i % 2 == 1 ? 1 : 0, Integer::sum);
        });
        System.out.println(map.get("pair"));   // 50
        System.out.println(map.get("impair")); // 50

        // réduction parallèle associative -> déterministe
        int somme = IntStream.rangeClosed(1, 100).parallel().sum();
        System.out.println(somme); // 5050

        // putIfAbsent : ignoré si la clé existe
        map.putIfAbsent("pair", 999);
        System.out.println(map.get("pair")); // 50
    }
}
