package cert.d06streams;

import java.util.NoSuchElementException;
import java.util.Optional;

/**
 * Domaine 6 — Optional : création, orElse vs orElseGet, map, filter.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d06streams.OptionalDemo
 */
public class OptionalDemo {
    public static void main(String[] args) {
        Optional<String> present = Optional.of("hello");
        Optional<String> vide = Optional.empty();

        System.out.println(present.isPresent()); // true
        System.out.println(vide.isEmpty());        // true

        // orElse (valeur toujours évaluée) vs orElseGet (lazy)
        System.out.println(vide.orElse("defaut"));          // defaut
        System.out.println(vide.orElseGet(() -> "calcule")); // calcule

        // map + filter sur Optional
        System.out.println(present.map(String::length).get());          // 5
        System.out.println(present.filter(s -> s.length() > 10).isPresent()); // false

        // ofNullable accepte null
        String n = null;
        System.out.println(Optional.ofNullable(n).orElse("null gere")); // null gere

        // Optional.of(null) -> NullPointerException
        try {
            Optional.of(n);
        } catch (NullPointerException e) {
            System.out.println("Optional.of(null) interdit");
        }

        // orElseThrow sur vide -> NoSuchElementException
        try {
            vide.orElseThrow();
        } catch (NoSuchElementException e) {
            System.out.println("orElseThrow sur vide");
        }
    }
}
