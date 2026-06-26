package cert.d07modules;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Exercices Domaine 7 — API Module (runtime).
 * ⚠️ La maîtrise des DIRECTIVES (requires/exports/opens/provides...) se travaille
 *    surtout dans docs/qcm/07-modules-jpms.md — ici on teste l'API réflexive.
 * 🟢 N1   🟡 N2   🔴 N3
 */
class D07ModulesExercises {

    @Test // 🟢 N1
    void moduleDeString() {
        String reponse = "?"; // TODO : String.class.getModule().getName()
        assertEquals(reponse, String.class.getModule().getName());
    }

    @Test // 🟢 N1
    void moduleDeHashMap() {
        String reponse = "?"; // TODO : HashMap.class.getModule().getName()
        assertEquals(reponse, HashMap.class.getModule().getName());
    }

    @Test // 🟡 N2 — notre code tourne sur le classpath
    void moduleSansNomEstNamed() {
        boolean reponse = true; // TODO : ce test est-il dans un module nommé ?
        assertEquals(reponse, D07ModulesExercises.class.getModule().isNamed());
    }

    @Test // 🟡 N2
    void nomDuModuleSansNom() {
        // getName() d'un module sans nom renvoie... ?  (null attendu)
        String reponse = "unnamed"; // TODO
        assertEquals(reponse, D07ModulesExercises.class.getModule().getName());
    }

    @Test // 🔴 N3 — les tableaux et primitifs appartiennent à java.base
    void moduleDuTableau() {
        String reponse = "?"; // TODO : int[].class.getModule().getName()
        assertEquals(reponse, int[].class.getModule().getName());
    }
}
