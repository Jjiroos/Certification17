package cert.d05collections;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Domaine 5 — Comparable (ordre naturel) & Comparator (ordre externe).
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d05collections.ComparatorDemo
 */
public class ComparatorDemo {

    record Person(String name, int age) {}

    public static void main(String[] args) {
        List<Person> people = new ArrayList<>(List.of(
                new Person("Alice", 30),
                new Person("Bob", 25),
                new Person("Alice", 22)));

        // Trier par nom, puis par âge
        people.sort(Comparator.comparing(Person::name)
                              .thenComparingInt(Person::age));
        System.out.println(people);
        // [Person[name=Alice, age=22], Person[name=Alice, age=30], Person[name=Bob, age=25]]

        // Ordre décroissant par âge
        people.sort(Comparator.comparingInt(Person::age).reversed());
        System.out.println(people.get(0).age()); // 30

        // Comparable : ordre naturel des String
        List<String> mots = new ArrayList<>(List.of("banane", "abricot", "cerise"));
        Collections.sort(mots);
        System.out.println(mots); // [abricot, banane, cerise]

        // compareTo / compare : négatif, 0, positif
        System.out.println("a".compareTo("b"));   // -1
        System.out.println(Integer.compare(5, 3)); // 1
    }
}
