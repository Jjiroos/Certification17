package cert.d02flow;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Exercices Domaine 2 — Contrôle de flux.
 * PRÉDIS la valeur (sans lancer), remplace le TODO, puis :  mvn -q test -Dtest=D02FlowExercises
 * Niveaux :  🟢 N1 fondamental   🟡 N2 intermédiaire   🔴 N3 piège / format examen
 */
class D02FlowExercises {

    // 🟢 N1
    @Test
    void ternaire() {
        int a = 7, b = 3;
        int reponse = 0; // TODO : (a > b) ? a - b : b - a
        assertEquals(reponse, (a > b) ? a - b : b - a);
    }

    // 🟢 N1
    @Test
    void sommeWhile() {
        int somme = 0, i = 1;
        while (i <= 5) { somme += i; i++; }
        int reponse = 0; // TODO : valeur finale de somme
        assertEquals(reponse, somme);
    }

    // 🟢 N1
    @Test
    void doWhileAuMoinsUneFois() {
        int compteur = 0, j = 10;
        do { compteur++; j++; } while (j < 5);
        int reponse = -1; // TODO : combien de fois le corps s'exécute-t-il ?
        assertEquals(reponse, compteur);
    }

    // 🟡 N2
    @Test
    void fallThrough() {
        // Sans break, le switch « tombe » dans les cas suivants.
        String reponse = "?"; // TODO : classifie(2)
        assertEquals(reponse, classifie(2));
    }

    // 🟡 N2
    @Test
    void switchExpression() {
        int reponse = -1; // TODO : codeLettre('e')
        assertEquals(reponse, codeLettre('e'));
    }

    // 🟡 N2
    @Test
    void continueDansFor() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            if (i % 2 == 0) continue;
            sb.append(i);
        }
        String reponse = "?"; // TODO : contenu de sb
        assertEquals(reponse, sb.toString());
    }

    // 🟡 N2
    @Test
    void forMultiVariables() {
        int produit = 1;
        for (int i = 1, j = 4; i < j; i++, j--) {
            produit *= i;
        }
        int reponse = 0; // TODO : valeur de produit
        assertEquals(reponse, produit);
    }

    // 🔴 N3
    @Test
    void breakEtiquete() {
        int reponse = -1; // TODO : combien d'incréments avant break outer ?
        assertEquals(reponse, compteBreakEtiquete());
    }

    // 🔴 N3
    @Test
    void continueEtiquete() {
        int reponse = -1; // TODO
        assertEquals(reponse, compteContinueEtiquete());
    }

    // 🔴 N3
    @Test
    void yieldDansSwitch() {
        int reponse = -1; // TODO : joursDansMois(2, 2024) — 2024 est-elle bissextile ?
        assertEquals(reponse, joursDansMois(2, 2024));
    }

    // 🔴 N3
    @Test
    void switchStringArrow() {
        String reponse = "?"; // TODO : categorie("MARDI")
        assertEquals(reponse, categorie("MARDI"));
    }

    // ----- Helpers (le code à « tracer mentalement ») -----

    private String classifie(int n) {
        StringBuilder sb = new StringBuilder();
        switch (n) {
            case 1: sb.append("un");
            case 2: sb.append("deux");
            case 3: sb.append("trois"); break;
            default: sb.append("autre");
        }
        return sb.toString();
    }

    private int codeLettre(char c) {
        return switch (c) {
            case 'a', 'e', 'i', 'o', 'u', 'y' -> 1;
            default -> 0;
        };
    }

    private int compteBreakEtiquete() {
        int count = 0;
        outer:
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                if (i + k == 3) break outer;
                count++;
            }
        }
        return count;
    }

    private int compteContinueEtiquete() {
        int count = 0;
        loop:
        for (int i = 0; i < 3; i++) {
            for (int k = 0; k < 3; k++) {
                if (k == 1) continue loop;
                count++;
            }
        }
        return count;
    }

    private int joursDansMois(int mois, int annee) {
        return switch (mois) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> {
                boolean bissextile = (annee % 4 == 0 && annee % 100 != 0) || annee % 400 == 0;
                yield bissextile ? 29 : 28;
            }
            default -> -1;
        };
    }

    private String categorie(String jour) {
        return switch (jour) {
            case "SAMEDI", "DIMANCHE" -> "weekend";
            case "LUNDI", "MARDI", "MERCREDI", "JEUDI", "VENDREDI" -> "semaine";
            default -> "inconnu";
        };
    }
}
