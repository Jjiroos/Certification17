package cert.d07modules;

/**
 * Domaine 7 — API réflexive des modules (java.lang.Module).
 *
 * Ce code tourne sur le CLASSPATH (pas de module-info) : il est donc dans le
 * « module sans nom » (unnamed module), tandis que les classes du JDK sont
 * dans des modules NOMMÉS.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d07modules.ModuleApiDemo
 */
public class ModuleApiDemo {
    public static void main(String[] args) {
        // Les classes du JDK appartiennent à des modules nommés
        System.out.println(String.class.getModule().getName());        // java.base
        System.out.println(java.util.List.class.getModule().getName()); // java.base

        // Notre code (classpath, sans module-info) -> module SANS NOM
        Module m = ModuleApiDemo.class.getModule();
        System.out.println(m.isNamed());  // false
        System.out.println(m.getName());  // null
    }
}
