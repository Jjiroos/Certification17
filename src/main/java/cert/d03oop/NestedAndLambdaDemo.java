package cert.d03oop;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

/**
 * Domaine 3 — Classes imbriquées & lambdas / interfaces fonctionnelles.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d03oop.NestedAndLambdaDemo
 */
public class NestedAndLambdaDemo {

    private final String champ = "instance";

    // Imbriquée STATIQUE : pas d'accès aux membres d'instance de l'englobante
    static class StaticNested {
        String hello() { return "static nested"; }
    }

    // INTERNE (non statique) : liée à une instance de l'englobante
    class Inner {
        String hello() { return "inner voit " + champ; }
    }

    void demoLocaleEtAnonyme() {
        class LocaleClass {                       // classe locale (dans une méthode)
            String hello() { return "locale"; }
        }
        System.out.println(new LocaleClass().hello()); // locale

        Runnable r = new Runnable() {              // classe anonyme
            public void run() { System.out.println("anonyme"); }
        };
        r.run();                                    // anonyme
    }

    public static void main(String[] args) {
        System.out.println(new StaticNested().hello());     // static nested

        NestedAndLambdaDemo ext = new NestedAndLambdaDemo();
        NestedAndLambdaDemo.Inner inner = ext.new Inner();  // syntaxe ext.new
        System.out.println(inner.hello());                  // inner voit instance

        ext.demoLocaleEtAnonyme();

        // --- Interfaces fonctionnelles standard ---
        Supplier<String> sup = () -> "fourni";
        Function<Integer, Integer> carre = x -> x * x;
        BiFunction<Integer, Integer, Integer> somme = (a, b) -> a + b;
        Predicate<String> vide = String::isEmpty;           // référence de méthode

        System.out.println(sup.get());                       // fourni
        System.out.println(carre.apply(5));                  // 25
        System.out.println(somme.apply(3, 4));               // 7
        System.out.println(vide.test(""));                   // true
        System.out.println(carre.andThen(x -> x + 1).apply(2)); // 5  (2*2=4 puis +1)
    }
}
