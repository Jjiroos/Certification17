package cert.d01types;

import java.util.ArrayList;
import java.util.List;

/**
 * Domaine 1 — Inférence de type local avec var.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d01types.VarDemo
 */
public class VarDemo {
    public static void main(String[] args) {
        var message = "bonjour";              // inféré String
        var nombre = 42;                       // inféré int (PAS Integer)
        var liste = new ArrayList<String>();   // ArrayList<String>
        liste.add("a");
        System.out.println(message + " " + nombre + " " + liste);
        // bonjour 42 [a]

        // var dans une boucle classique
        for (var i = 0; i < 3; i++) {
            System.out.print(i);
        }
        System.out.println();                  // 012

        // var dans un for-each
        List<String> mots = List.of("x", "y");
        for (var mot : mots) {
            System.out.print(mot);
        }
        System.out.println();                  // xy

        // 'var' n'est PAS un mot-clé réservé : utilisable comme nom de variable
        int var = 5;
        System.out.println(var);               // 5

        // INTERDITS (ne compilent pas) — décommente pour voir l'erreur :
        // var x;             // pas d'initialiseur
        // var y = null;      // type indéterminable
        // var z = {1, 2};    // initialiseur de tableau sans type
        // var p = 1, q = 2;  // déclarations multiples
    }
}
