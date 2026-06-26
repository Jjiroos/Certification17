package cert.d11i18n;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Locale;

/**
 * Domaine 11 — NumberFormat & DecimalFormat (locales explicites pour un résultat stable).
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d11i18n.NumberFormatAndDecimalDemo
 */
public class NumberFormatAndDecimalDemo {
    public static void main(String[] args) throws ParseException {
        double valeur = 1234.5;

        // Séparateurs selon la locale
        System.out.println(NumberFormat.getInstance(Locale.US).format(valeur));      // 1,234.5
        System.out.println(NumberFormat.getInstance(Locale.GERMANY).format(valeur)); // 1.234,5

        // Devise et pourcentage
        System.out.println(NumberFormat.getCurrencyInstance(Locale.US).format(valeur)); // $1,234.50
        System.out.println(NumberFormat.getPercentInstance(Locale.US).format(0.25));    // 25%

        // DecimalFormat avec symboles US explicites
        DecimalFormatSymbols us = DecimalFormatSymbols.getInstance(Locale.US);
        System.out.println(new DecimalFormat("#,##0.00", us).format(1234.5)); // 1,234.50
        System.out.println(new DecimalFormat("000.0", us).format(45.678));    // 045.7

        // parse renvoie un Number
        Number n = NumberFormat.getInstance(Locale.US).parse("1,234.5");
        System.out.println(n); // 1234.5
    }
}
