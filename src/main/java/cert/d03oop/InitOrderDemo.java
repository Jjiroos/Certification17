package cert.d03oop;

/**
 * Domaine 3 — Ordre d'initialisation, this()/super(), constructeurs.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d03oop.InitOrderDemo
 */
public class InitOrderDemo {

    static class Animal {
        static { System.out.println("static Animal"); }   // 1 fois, au chargement
        { System.out.println("init Animal"); }              // à chaque new, après super()
        Animal() { System.out.println("ctor Animal"); }
        Animal(String n) {
            this();                                         // chaîne vers Animal()
            System.out.println("ctor Animal(String)");
        }
    }

    static class Chien extends Animal {
        static { System.out.println("static Chien"); }
        { System.out.println("init Chien"); }
        Chien() {
            super("Rex");                                   // chaîne vers Animal(String)
            System.out.println("ctor Chien");
        }
    }

    public static void main(String[] args) {
        System.out.println("--- premier new ---");
        new Chien();
        System.out.println("--- deuxieme new ---");
        new Chien();
        // --- premier new ---
        // static Animal
        // static Chien
        // init Animal
        // ctor Animal
        // ctor Animal(String)
        // init Chien
        // ctor Chien
        // --- deuxieme new ---
        // init Animal
        // ctor Animal
        // ctor Animal(String)
        // init Chien
        // ctor Chien
    }
}
