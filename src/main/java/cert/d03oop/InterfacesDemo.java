package cert.d03oop;

/**
 * Domaine 3 — Interfaces : default / static / private, constantes.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d03oop.InterfacesDemo
 */
public class InterfacesDemo {

    interface Greeter {
        String NAME = "Greeter";        // public static final implicite

        String greet();                 // abstraite (public implicite)

        default String politeGreet() {  // méthode default
            return prefix() + greet();
        }
        static String info() {          // méthode statique (appelée via Greeter.info())
            return "interface " + NAME;
        }
        private String prefix() {       // méthode privée (Java 9+) : factorisation
            return ">> ";
        }
    }

    static class Fr implements Greeter {
        public String greet() { return "Bonjour"; } // public obligatoire
    }

    public static void main(String[] args) {
        Greeter g = new Fr();
        System.out.println(g.greet());        // Bonjour
        System.out.println(g.politeGreet());  // >> Bonjour
        System.out.println(Greeter.info());   // interface Greeter
        System.out.println(Greeter.NAME);     // Greeter
    }
}
