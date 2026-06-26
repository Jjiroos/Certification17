package cert.d03oop;

import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Exercices Domaine 3 — POO. PRÉDIS sans lancer, remplace le TODO, puis mvn test.
 * 🟢 N1 fondamental   🟡 N2 intermédiaire   🔴 N3 piège / format examen
 */
class D03OopExercises {

    // ---- types d'appui ----
    static class Base { String nom() { return "base"; } }
    static class Derivee extends Base { @Override String nom() { return "derivee"; } }

    static class P { int v = 1; }
    static class E extends P { int v = 2; }

    static String f(long x)    { return "long"; }
    static String f(Integer x) { return "Integer"; }
    static String f(int... x)  { return "varargs"; }

    enum Op { ADD, SUB }

    enum Calc {
        PLUS { int apply(int a, int b) { return a + b; } },
        FOIS { int apply(int a, int b) { return a * b; } };
        abstract int apply(int a, int b);
    }

    record Coord(int x, int y) {}

    interface I { default String hi() { return "I"; } }
    static class C implements I {}

    static class Niveau1 { int profondeur; Niveau1() { profondeur = 1; } }
    static class Niveau2 extends Niveau1 { Niveau2() { profondeur++; } }
    static class Niveau3 extends Niveau2 { Niveau3() { profondeur++; } }

    static class Tracer {
        static StringBuilder sb = new StringBuilder();
        static class Parent {
            { sb.append("ip"); }
            Parent() { sb.append("cp"); }
        }
        static class Enfant extends Parent {
            { sb.append("ie"); }
            Enfant() { sb.append("ce"); }
        }
        static String build() { sb.setLength(0); new Enfant(); return sb.toString(); }
    }

    static String check(Object o) {
        if (o instanceof String s && s.length() > 1) return "long:" + s.length();
        if (o instanceof String s) return "court";
        return "autre";
    }

    // ---- exercices ----

    // 🟢 N1
    @Test
    void overrideDynamique() {
        Base b = new Derivee();
        String reponse = "?"; // TODO : b.nom() — liaison dynamique
        assertEquals(reponse, b.nom());
    }

    // 🟢 N1
    @Test
    void enumOrdinal() {
        int reponse = -1; // TODO : Op.SUB.ordinal()
        assertEquals(reponse, Op.SUB.ordinal());
    }

    // 🟡 N2 — un champ suit le TYPE de la référence (pas l'objet)
    @Test
    void champMasque() {
        P p = new E();
        int reponse = -1; // TODO : p.v
        assertEquals(reponse, p.v);
    }

    // 🟡 N2 — élargissement vs autoboxing vs varargs
    @Test
    void surchargeElargissement() {
        int i = 7;
        String reponse = "?"; // TODO : f(i)
        assertEquals(reponse, f(i));
    }

    // 🟡 N2
    @Test
    void enumAbstraite() {
        int reponse = -1; // TODO : Calc.FOIS.apply(3, 4)
        assertEquals(reponse, Calc.FOIS.apply(3, 4));
    }

    // 🟡 N2
    @Test
    void recordEquals() {
        boolean reponse = false; // TODO : new Coord(1,2).equals(new Coord(1,2))
        assertEquals(reponse, new Coord(1, 2).equals(new Coord(1, 2)));
    }

    // 🟡 N2
    @Test
    void recordToString() {
        String reponse = "?"; // TODO : new Coord(1,2).toString()
        assertEquals(reponse, new Coord(1, 2).toString());
    }

    // 🟡 N2
    @Test
    void interfaceDefault() {
        String reponse = "?"; // TODO : new C().hi()
        assertEquals(reponse, new C().hi());
    }

    // 🟡 N2
    @Test
    void lambdaAndThen() {
        Function<Integer, Integer> doubler = n -> n * 2;
        int reponse = -1; // TODO : doubler.andThen(n -> n + 1).apply(5)
        assertEquals(reponse, doubler.andThen(n -> n + 1).apply(5));
    }

    // 🔴 N3 — ordre d'initialisation (init parent, ctor parent, init enfant, ctor enfant)
    @Test
    void ordreInit() {
        String reponse = "?"; // TODO : trace produite par new Enfant()
        assertEquals(reponse, Tracer.build());
    }

    // 🔴 N3 — instanceof pattern avec garde
    @Test
    void instanceofPattern() {
        String reponse = "?"; // TODO : check("hi")
        assertEquals(reponse, check("hi"));
    }

    // 🔴 N3 — chaînage de constructeurs via super()
    @Test
    void superChain() {
        int reponse = -1; // TODO : new Niveau3().profondeur
        assertEquals(reponse, new Niveau3().profondeur);
    }
}
