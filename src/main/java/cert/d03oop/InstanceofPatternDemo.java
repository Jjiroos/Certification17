package cert.d03oop;

/**
 * Domaine 3 — Pattern matching pour instanceof (standard en Java 17).
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d03oop.InstanceofPatternDemo
 */
public class InstanceofPatternDemo {

    sealed interface Shape permits Circle, Square {}
    record Circle(double r) implements Shape {}
    record Square(double cote) implements Shape {}

    static String describe(Object o) {
        // Le test lie une variable utilisable dans la même expression / branche
        if (o instanceof String s && !s.isEmpty()) {
            return "String de longueur " + s.length();
        } else if (o instanceof Integer n) {
            return "Integer = " + n;
        }
        return "inconnu";
    }

    static double area(Shape shape) {
        if (shape instanceof Circle c) {
            return Math.PI * c.r() * c.r();
        } else if (shape instanceof Square sq) {
            return sq.cote() * sq.cote();
        }
        return 0;
    }

    public static void main(String[] args) {
        System.out.println(describe("hello"));   // String de longueur 5
        System.out.println(describe(42));         // Integer = 42
        System.out.println(describe(3.14));       // inconnu
        System.out.println(area(new Square(4)));  // 16.0
    }
}
