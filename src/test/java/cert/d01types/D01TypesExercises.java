package cert.d01types;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.Period;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Exercices Domaine 1 — « prédis la sortie ».
 *
 * MODE D'EMPLOI :
 *   1. Pour chaque test, REMPLACE la valeur sentinelle (marquée TODO) par ta prédiction.
 *   2. Lance :  mvn -q test -Dtest=D01TypesExercises
 *   3. Vert = bonne réponse. Rouge = JUnit affiche la vraie valeur ("but was: ...").
 *
 * Les tests ÉCHOUENT volontairement tant que tu n'as pas mis la bonne valeur :
 * c'est le mécanisme d'auto-correction.
 *
 * Niveaux : les premiers exercices sont 🟢/🟡 ; la section finale ajoute des 🔴 (pièges).
 */
class D01TypesExercises {

    @Test
    void litteralOctal() {
        // Que vaut le littéral 010 (commence par un zéro) ?
        int reponse = -1; // TODO
        assertEquals(reponse, 010);
    }

    @Test
    void cacheDesInteger() {
        // Integer.valueOf(127) == Integer.valueOf(127) renvoie ... ?
        boolean reponse127 = false; // TODO
        assertEquals(reponse127, Integer.valueOf(127) == Integer.valueOf(127));

        // Et pour 128 ?
        boolean reponse128 = true; // TODO
        assertEquals(reponse128, Integer.valueOf(128) == Integer.valueOf(128));
    }

    @Test
    void debordement() {
        // Integer.MAX_VALUE + 1 vaut ... ?
        int reponse = 0; // TODO
        assertEquals(reponse, Integer.MAX_VALUE + 1);
    }

    @Test
    void concatenationGaucheADroite() {
        String r1 = "?"; // TODO : 1 + 2 + "x"
        assertEquals(r1, 1 + 2 + "x");

        String r2 = "?"; // TODO : "x" + 1 + 2
        assertEquals(r2, "x" + 1 + 2);
    }

    @Test
    void divisions() {
        int rEntiere = 0; // TODO : 7 / 2
        assertEquals(rEntiere, 7 / 2);

        double rFlottante = 0; // TODO : 7.0 / 2
        assertEquals(rFlottante, 7.0 / 2);
    }

    @Test
    void affectationComposee() {
        byte b = 10;
        b += 5; // cast implicite inclus
        int reponse = 0; // TODO : valeur de b
        assertEquals(reponse, b);
    }

    @Test
    void arithmetiqueChar() {
        // 'a' + 1 donne un int. Lequel ?
        int reponse = 0; // TODO
        assertEquals(reponse, 'a' + 1);
    }

    @Test
    void substring() {
        // "Hello World".substring(6) renvoie ... ?
        String reponse = "?"; // TODO
        assertEquals(reponse, "Hello World".substring(6));
    }

    @Test
    void ajoutDeMois() {
        // 31 janvier 2026 + 1 mois = ... ? (attention au nombre de jours de février)
        LocalDate reponse = LocalDate.of(2026, 1, 1); // TODO
        assertEquals(reponse, LocalDate.of(2026, 1, 31).plusMonths(1));
    }

    @Test
    void periodToString() {
        // Représentation textuelle ISO-8601 de Period.of(1, 2, 3) ?
        String reponse = "?"; // TODO
        assertEquals(reponse, Period.of(1, 2, 3).toString());
    }

    @Test
    void parsingInvalide() {
        // Quelle exception lève Integer.parseInt("4.2") ?
        // Remplace IllegalStateException.class par la classe PRÉCISE.
        assertThrows(IllegalStateException.class, // TODO
                () -> Integer.parseInt("4.2"));
    }

    // ----- 🔴 Niveau 3 — pièges avancés -----

    @Test // 🔴 N3 — concaténation avec une VARIABLE (pas une constante de compilation)
    void poolConcatVariable() {
        String a = "java";
        String b = "ja";
        String c = b + "va";
        boolean reponse = true; // TODO : a == c
        assertEquals(reponse, a == c);
    }

    @Test // 🔴 N3 — ternaire char/int : promotion numérique
    void ternairePromotion() {
        char c = 'X';
        int i = 0;
        int reponse = -1; // TODO : (true ? c : i)
        assertEquals(reponse, true ? c : i);
    }

    @Test // 🔴 N3 — affectation composée avec un double
    void affectationComposeeDouble() {
        int x = 5;
        x += 2.5; // cast implicite (int)(x + 2.5)
        int reponse = 0; // TODO : valeur de x
        assertEquals(reponse, x);
    }

    @Test // 🔴 N3 — passage de mois (2026 non bissextile)
    void datePassageDeMois() {
        int reponse = -1; // TODO : mois de (28 fév. 2026 + 1 jour)
        assertEquals(reponse, LocalDate.of(2026, 2, 28).plusDays(1).getMonthValue());
    }
}
