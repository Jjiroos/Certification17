package cert.d11i18n;

import org.junit.jupiter.api.Test;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.MessageFormat;
import java.text.NumberFormat;
import java.util.Locale;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Exercices Domaine 11 — Localisation. PRÉDIS sans lancer, puis mvn test.
 * 🟢 N1   🟡 N2   🔴 N3
 */
class D11I18nExercises {

    @Test // 🟢 N1
    void localeCountry() {
        String reponse = "?"; // TODO : Locale.FRANCE.getCountry()
        assertEquals(reponse, Locale.FRANCE.getCountry());
    }

    @Test // 🟢 N1
    void numberUS() {
        String reponse = "?"; // TODO : 1234.5 formaté en US
        assertEquals(reponse, NumberFormat.getInstance(Locale.US).format(1234.5));
    }

    @Test // 🟡 N2 — getPercentInstance multiplie par 100
    void percent() {
        String reponse = "?"; // TODO : 0.5 en pourcentage US
        assertEquals(reponse, NumberFormat.getPercentInstance(Locale.US).format(0.5));
    }

    @Test // 🟡 N2
    void numberGermany() {
        String reponse = "?"; // TODO : 1234.5 formaté en Allemagne
        assertEquals(reponse, NumberFormat.getInstance(Locale.GERMANY).format(1234.5));
    }

    @Test // 🟡 N2 — 0 = chiffre obligatoire
    void decimalFormat() {
        DecimalFormat df = new DecimalFormat("000.00", DecimalFormatSymbols.getInstance(Locale.US));
        String reponse = "?"; // TODO : df.format(7.5)
        assertEquals(reponse, df.format(7.5));
    }

    @Test // 🟡 N2 — bundle français
    void bundleFr() {
        ResourceBundle b = ResourceBundle.getBundle("messages", Locale.FRENCH);
        String reponse = "?"; // TODO : b.getString("greeting")
        assertEquals(reponse, b.getString("greeting"));
    }

    @Test // 🔴 N3 — MessageFormat
    void messageFormat() {
        String reponse = "?"; // TODO
        assertEquals(reponse, MessageFormat.format("{0} sur {1}", 3, 10));
    }

    @Test // 🔴 N3 — clé absente
    void cleAbsente() {
        ResourceBundle b = ResourceBundle.getBundle("messages", Locale.FRENCH);
        assertThrows(Error.class, () -> b.getString("inexistante")); // TODO : quelle exception ?
    }
}
