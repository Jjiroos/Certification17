package cert.d02flow;

/**
 * Domaine 2 — break / continue étiquetés (boucles imbriquées).
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d02flow.LabeledLoopsDemo
 */
public class LabeledLoopsDemo {
    public static void main(String[] args) {
        // --- break étiqueté : sort des DEUX boucles ---
        StringBuilder sb = new StringBuilder();
        outer:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (i + j == 3) {
                    break outer; // quitte la boucle "outer"
                }
                sb.append(i).append(j).append(' ');
            }
        }
        System.out.println(sb.toString().trim());
        // 00 01 02 10 11

        // --- continue étiqueté : reprend la boucle externe ---
        StringBuilder sb2 = new StringBuilder();
        loop:
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (j == 1) {
                    continue loop; // passe au i suivant
                }
                sb2.append(i).append(j).append(' ');
            }
        }
        System.out.println(sb2.toString().trim());
        // 00 10 20
    }
}
