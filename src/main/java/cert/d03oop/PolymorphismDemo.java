package cert.d03oop;

/**
 * Domaine 3 — Polymorphisme : méthodes (dynamique) vs champs/statics (statique).
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d03oop.PolymorphismDemo
 */
public class PolymorphismDemo {

    static class A {
        String name = "A";
        String who() { return "A"; }
        static String stat() { return "A.stat"; }
    }

    static class B extends A {
        String name = "B";                          // MASQUE le champ de A
        @Override String who() { return "B"; }       // REDÉFINIT (override)
        static String stat() { return "B.stat"; }    // MASQUE la méthode statique
    }

    public static void main(String[] args) {
        A ref = new B();

        // Méthode d'instance : liaison DYNAMIQUE -> version de l'objet réel (B)
        System.out.println(ref.who());   // B

        // Champ : liaison STATIQUE -> selon le TYPE de la référence (A)
        System.out.println(ref.name);    // A

        // Méthode statique : liaison STATIQUE -> selon le type de la référence (A)
        System.out.println(ref.stat());  // A.stat

        // Avec le vrai type B :
        B b = new B();
        System.out.println(b.name);      // B
        System.out.println(b.who());     // B
    }
}
