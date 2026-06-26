package cert.d06streams;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

/**
 * Domaine 6 — reduce, min/max, match, findFirst (et le cas du stream vide).
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d06streams.ReduceAndMatchDemo
 */
public class ReduceAndMatchDemo {
    public static void main(String[] args) {
        List<Integer> nums = List.of(3, 1, 4, 1, 5, 9, 2);

        // reduce avec identité -> valeur
        int somme = nums.stream().reduce(0, Integer::sum);
        System.out.println(somme); // 25

        // reduce sans identité -> Optional
        Optional<Integer> max = nums.stream().reduce(Integer::max);
        System.out.println(max.get()); // 9

        // min / max
        System.out.println(nums.stream().min(Integer::compareTo).get()); // 1
        System.out.println(nums.stream().max(Integer::compareTo).get()); // 9

        // anyMatch / allMatch / noneMatch
        System.out.println(nums.stream().anyMatch(x -> x > 8));   // true
        System.out.println(nums.stream().allMatch(x -> x > 0));   // true
        System.out.println(nums.stream().noneMatch(x -> x > 10)); // true

        // findFirst (court-circuit)
        System.out.println(nums.stream().filter(x -> x % 2 == 0).findFirst().get()); // 4

        // stream VIDE : allMatch renvoie true (vacuité)
        System.out.println(Stream.<Integer>of().allMatch(x -> x > 100)); // true
        System.out.println(Stream.<Integer>of().anyMatch(x -> x > 100)); // false
    }
}
