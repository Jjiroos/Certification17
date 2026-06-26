package cert.d05collections;

import java.util.Arrays;

/**
 * Domaine 5 — Tableaux (1D, multi-D, tri, recherche).
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d05collections.ArraysDemo
 */
public class ArraysDemo {
    public static void main(String[] args) {
        // --- Valeurs par défaut ---
        int[] nums = new int[3];
        System.out.println(Arrays.toString(nums));  // [0, 0, 0]
        String[] mots = new String[2];
        System.out.println(Arrays.toString(mots));  // [null, null]

        // length est un CHAMP (pas une méthode)
        int[] t = {5, 3, 1, 4, 2};
        System.out.println(t.length);                // 5

        // --- Tri + recherche binaire (le tableau DOIT être trié) ---
        Arrays.sort(t);
        System.out.println(Arrays.toString(t));      // [1, 2, 3, 4, 5]
        System.out.println(Arrays.binarySearch(t, 4)); // 3 (index de 4)

        // --- Multidimensionnel (jagged : tailles différentes possibles) ---
        int[][] grille = { {1, 2}, {3, 4, 5} };
        System.out.println(grille.length);            // 2
        System.out.println(grille[1].length);         // 3
        System.out.println(Arrays.deepToString(grille)); // [[1, 2], [3, 4, 5]]

        // --- Syntaxes de déclaration ---
        int[] a = new int[]{1, 2};
        int b[] = new int[]{3};                       // style C autorisé
        System.out.println(a.length + " " + b.length); // 2 1
    }
}
