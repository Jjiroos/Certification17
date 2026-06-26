package cert.d11i18n;

import java.text.MessageFormat;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Domaine 11 — ResourceBundle (.properties) & MessageFormat.
 *
 * Fichiers : src/main/resources/messages.properties (base) + messages_fr.properties
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d11i18n.ResourceBundleDemo
 */
public class ResourceBundleDemo {
    public static void main(String[] args) {
        // On fixe la locale par défaut pour rendre la résolution déterministe
        Locale.setDefault(Locale.ENGLISH);

        ResourceBundle fr = ResourceBundle.getBundle("messages", Locale.FRENCH);
        System.out.println(fr.getString("greeting")); // Bonjour

        ResourceBundle en = ResourceBundle.getBundle("messages", Locale.ENGLISH);
        System.out.println(en.getString("greeting")); // Hello

        // Locale inconnue -> retombe sur la locale par défaut (ENGLISH) -> base
        ResourceBundle ja = ResourceBundle.getBundle("messages", Locale.JAPANESE);
        System.out.println(ja.getString("greeting")); // Hello

        // MessageFormat pour les paramètres {0}
        System.out.println(MessageFormat.format(fr.getString("items"), 3)); // 3 articles
        System.out.println(MessageFormat.format(en.getString("items"), 3)); // 3 items
    }
}
