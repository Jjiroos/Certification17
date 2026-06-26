package cert.d02flow;

/**
 * Domaine 2 — switch expression (forme « -> »), yield, exhaustivité.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d02flow.SwitchExpressionDemo
 */
public class SwitchExpressionDemo {

    enum Day { LUN, MAR, MER, JEU, VEN, SAM, DIM }

    public static void main(String[] args) {
        // --- Forme flèche : renvoie une valeur, PAS de fall-through ---
        int n = 3;
        String texte = switch (n) {
            case 1 -> "un";
            case 2, 3 -> "deux ou trois"; // plusieurs étiquettes
            default -> "autre";
        };
        System.out.println(texte); // deux ou trois

        // --- yield : produit la valeur depuis un bloc ---
        int jours = switch (4) {
            case 1, 3, 5, 7, 8, 10, 12 -> 31;
            case 4, 6, 9, 11 -> 30;
            case 2 -> {
                int annee = 2026;
                yield (annee % 4 == 0) ? 29 : 28; // yield, PAS return
            }
            default -> throw new IllegalArgumentException("mois invalide");
        };
        System.out.println(jours); // 30

        // --- Exhaustivité avec enum : default facultatif si tous les cas couverts ---
        Day d = Day.SAM;
        boolean weekend = switch (d) {
            case SAM, DIM -> true;
            case LUN, MAR, MER, JEU, VEN -> false;
        };
        System.out.println(weekend); // true
    }
}
