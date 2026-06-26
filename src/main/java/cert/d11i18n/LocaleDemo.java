package cert.d11i18n;

import java.util.Locale;

/**
 * Domaine 11 — Locale (langue + pays).
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d11i18n.LocaleDemo
 */
public class LocaleDemo {
    public static void main(String[] args) {
        Locale fr = Locale.FRANCE;
        System.out.println(fr.getLanguage()); // fr  (langue en minuscules)
        System.out.println(fr.getCountry());  // FR  (pays en majuscules)

        // En Java 17 : constructeur new Locale(...) (Locale.of n'existe qu'en 19+)
        Locale es = new Locale("es", "ES");
        System.out.println(es.getLanguage() + "_" + es.getCountry()); // es_ES

        // Locale par langue seule : pas de pays
        Locale en = Locale.ENGLISH;
        System.out.println(en.getCountry().isEmpty()); // true

        // Constantes
        System.out.println(Locale.US.toString());  // en_US
        System.out.println(Locale.CANADA_FRENCH);   // fr_CA
    }
}
