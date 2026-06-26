package cert.d02flow;

/**
 * Domaine 2 — switch classique (forme « : »), fall-through.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d02flow.SwitchStatementDemo
 */
public class SwitchStatementDemo {
    public static void main(String[] args) {
        // --- Fall-through : sans break, on continue dans les cas suivants ---
        int n = 2;
        switch (n) {
            case 1:
                System.out.println("un");
            case 2:
                System.out.println("deux");   // entrée ici
            case 3:
                System.out.println("trois");  // fall-through (pas de break avant)
                break;
            case 4:
                System.out.println("quatre");
        }
        // deux
        // trois

        // --- default n'est pas forcément en dernier ---
        char c = 'x';
        switch (c) {
            default:
                System.out.println("defaut"); // exécuté : aucun case ne matche
                break;
            case 'a':
                System.out.println("a");
        }
        // defaut

        // --- switch sur String (forme classique) ---
        String jour = "LUNDI";
        switch (jour) {
            case "LUNDI":
            case "MARDI":
                System.out.println("début de semaine");
                break;
            default:
                System.out.println("autre");
        }
        // début de semaine
    }
}
