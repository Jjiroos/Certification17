package cert.d03oop;

/**
 * Domaine 3 — Résolution de surcharge : élargissement > autoboxing > varargs.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d03oop.OverloadingDemo
 */
public class OverloadingDemo {

    static void f(long x)    { System.out.println("long"); }
    static void f(Integer x) { System.out.println("Integer"); }
    static void f(Object x)  { System.out.println("Object"); }
    static void f(int... x)  { System.out.println("varargs"); }

    static void g(int x)  { System.out.println("g int"); }
    static void g(long x) { System.out.println("g long"); }

    public static void main(String[] args) {
        int i = 5;
        // int -> long par ÉLARGISSEMENT, préféré à int->Integer (boxing) et à varargs
        f(i);            // long

        short s = 3;
        // short -> int (élargissement vers le plus proche) préféré à short -> long
        g(s);            // g int

        f(new Object());  // Object
        f(1, 2, 3);       // varargs  (aucune autre forme ne convient)
    }
}
