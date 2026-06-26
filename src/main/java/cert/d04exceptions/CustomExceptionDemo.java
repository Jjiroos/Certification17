package cert.d04exceptions;

/**
 * Domaine 4 — exceptions personnalisées (checked vs unchecked).
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d04exceptions.CustomExceptionDemo
 */
public class CustomExceptionDemo {

    // CHECKED : étend Exception -> doit être déclarée (throws) ou attrapée
    static class SoldeInsuffisantException extends Exception {
        SoldeInsuffisantException(String msg) { super(msg); }
    }

    // UNCHECKED : étend RuntimeException -> aucune obligation
    static class MontantInvalideException extends RuntimeException {
        MontantInvalideException(String msg) { super(msg); }
    }

    static void retirer(int solde, int montant) throws SoldeInsuffisantException {
        if (montant < 0) throw new MontantInvalideException("montant negatif");
        if (montant > solde) throw new SoldeInsuffisantException("solde " + solde + " < " + montant);
        System.out.println("retrait de " + montant + " ok");
    }

    // main DOIT gérer/déclarer l'exception CHECKED, même si l'appel ci-dessous
    // lèvera en pratique l'UNCHECKED : le compilateur raisonne sur le 'throws'.
    public static void main(String[] args) throws SoldeInsuffisantException {
        try {
            retirer(100, 30);   // ok
            retirer(100, 150);  // lève l'exception checked
        } catch (SoldeInsuffisantException e) {
            System.out.println("checked: " + e.getMessage());
        }
        // retrait de 30 ok
        // checked: solde 100 < 150

        try {
            retirer(100, -5);   // lève l'exception unchecked
        } catch (MontantInvalideException e) {
            System.out.println("unchecked: " + e.getMessage());
        }
        // unchecked: montant negatif
    }
}
