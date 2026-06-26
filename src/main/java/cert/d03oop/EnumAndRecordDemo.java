package cert.d03oop;

/**
 * Domaine 3 — enum (constructeur, méthodes) & record (compact, accesseurs).
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d03oop.EnumAndRecordDemo
 */
public class EnumAndRecordDemo {

    enum Operation {
        PLUS  { public int apply(int a, int b) { return a + b; } },
        MOINS { public int apply(int a, int b) { return a - b; } };

        public abstract int apply(int a, int b); // chaque constante l'implémente
    }

    enum Niveau {
        BAS(1), HAUT(10);
        private final int poids;
        Niveau(int poids) { this.poids = poids; } // constructeur implicitement privé
        int poids() { return poids; }
    }

    // Record : champs final, accesseurs, equals/hashCode/toString générés
    record Point(int x, int y) {
        Point {                                   // constructeur compact
            if (x < 0 || y < 0) throw new IllegalArgumentException("negatif");
        }
        int somme() { return x + y; }
    }

    public static void main(String[] args) {
        System.out.println(Operation.PLUS.apply(3, 4));   // 7
        System.out.println(Operation.MOINS.apply(3, 4));  // -1
        System.out.println(Niveau.valueOf("HAUT").poids()); // 10
        System.out.println(Niveau.values().length);         // 2

        Point p = new Point(2, 3);
        System.out.println(p.x() + " " + p.y());      // 2 3  (accesseurs SANS get)
        System.out.println(p);                          // Point[x=2, y=3]
        System.out.println(p.equals(new Point(2, 3)));  // true
        System.out.println(p.somme());                  // 5
        try {
            new Point(-1, 0);
        } catch (IllegalArgumentException e) {
            System.out.println("rejete: " + e.getMessage()); // rejete: negatif
        }
    }
}
