# QCM 03 — Approche orientée objet

> Réponds **sans lancer le code**. Corrigés en bas.
> 🟢 N1 fondamentaux · 🟡 N2 intermédiaire · 🔴 N3 pièges & format examen
> ⚠️ Certaines questions ont **plusieurs** bonnes réponses.

---

## 🟢 Niveau 1 — Fondamentaux

**Q1.**
```java
class A { String m() { return "A"; } }
class B extends A { String m() { return "B"; } }
A x = new B();
System.out.println(x.m());
```
- A) A  B) B  C) Compilation fails  D) Exception

**Q2.** Quelles affirmations sur un `record Point(int x, int y) {}` sont vraies ? (Choose two)
- A) Il génère des accesseurs `getX()`/`getY()`
- B) Il génère des accesseurs `x()`/`y()`
- C) Il est implicitement `final`
- D) Il peut `extends` une autre classe

**Q3.**
```java
enum Couleur { ROUGE, VERT, BLEU }
System.out.println(Couleur.VERT.ordinal());
```
- A) 0  B) 1  C) 2  D) VERT

**Q4.** Combien de méthodes **abstraites** possède une interface fonctionnelle valide ?
- A) 0  B) exactement 1  C) au moins 1  D) autant que voulu

**Q5.**
```java
interface Saluer { default String hi() { return "hi"; } }
class Fr implements Saluer {}
System.out.println(new Fr().hi());
```
- A) hi  B) null  C) Compilation fails  D) Exception

## 🟡 Niveau 2 — Intermédiaire

**Q6.**
```java
class A { int v = 1; int get() { return v; } }
class B extends A { int v = 2; }
A a = new B();
System.out.println(a.v + " " + a.get());
```
- A) 2 2  B) 1 1  C) 2 1  D) 1 2

**Q7.**
```java
static void f(Object o) { System.out.print("O"); }
static void f(int... i)  { System.out.print("V"); }
static void f(long l)    { System.out.print("L"); }
f(5);
```
- A) O  B) V  C) L  D) Compilation fails (ambigu)

**Q8.**
```java
record P(int x) {
    P { if (x < 0) throw new IllegalArgumentException(); }
}
System.out.println(new P(5).x());
```
- A) 5  B) 0  C) Compilation fails  D) Exception

**Q9.**
```java
enum Op {
    PLUS { int a(int x, int y) { return x + y; } },
    FOIS { int a(int x, int y) { return x * y; } };
    abstract int a(int x, int y);
}
System.out.println(Op.FOIS.a(3, 4));
```
- A) 7  B) 12  C) Compilation fails  D) Exception

**Q10.**
```java
class A {
    A(int n) { System.out.print("A"); }
}
class B extends A {
    B() { System.out.print("B"); }
}
new B();
```
- A) A  B) AB  C) BA  D) Compilation fails

**Q11.** (Choose two) Quels modificateurs sont **interdits** sur une méthode `abstract` ?
- A) `public`  B) `final`  C) `protected`  D) `private`

**Q12.**
```java
class Compteur {
    static int n;
    Compteur() { n++; }
}
new Compteur(); new Compteur(); new Compteur();
System.out.println(Compteur.n);
```
- A) 0  B) 1  C) 3  D) Compilation fails

## 🔴 Niveau 3 — Pièges & format examen

**Q13.**
```java
class A { static String s() { return "A"; } }
class B extends A { static String s() { return "B"; } }
A a = new B();
System.out.println(a.s());
```
- A) A  B) B  C) Compilation fails  D) Exception

**Q14.**
```java
class A {
    { System.out.print("1"); }
    A() { System.out.print("2"); }
    A(int x) { this(); System.out.print("3"); }
}
new A(9);
```
- A) 123  B) 12  C) 213  D) 132

**Q15.**
```java
sealed interface S permits Impl {}
class Impl implements S {}
```
Pourquoi ce code **ne compile pas** tel quel ?
- A) Une interface ne peut pas être `sealed`
- B) `Impl` doit être `final`, `sealed` ou `non-sealed`
- C) `permits` est interdit sur une interface
- D) Il compile sans problème

**Q16.**
```java
Object o = "texte";
if (o instanceof String s) {
    System.out.println(s.length());
}
System.out.println(s);   // ligne X
```
- A) Affiche 5 puis texte
- B) Compilation fails à la ligne X (s hors portée)
- C) Affiche 5 puis null
- D) Exception

**Q17.**
```java
interface A { default String m() { return "A"; } }
interface B { default String m() { return "B"; } }
class C implements A, B { }
```
- A) Compile, `m()` renvoie "A"
- B) Compile, `m()` renvoie "B"
- C) Compilation fails (conflit de default à résoudre)
- D) Exception à l'exécution

**Q18.**
```java
enum E { X, Y }
E e = E.valueOf("Z");
```
- A) `e` vaut null  B) Compilation fails  C) IllegalArgumentException  D) `e` vaut X

**Q19.**
```java
class Outer {
    class Inner { }
}
// Comment créer une instance de Inner ?
```
- A) `new Outer.Inner()`
- B) `new Inner()`
- C) `new Outer().new Inner()`
- D) `Outer.new Inner()`

**Q20.**
```java
int facteur = 2;
java.util.function.Function<Integer,Integer> f = x -> x * facteur;
facteur = 3;            // ligne X
System.out.println(f.apply(10));
```
- A) Compilation fails (à cause de la ligne X)
- B) 30
- C) 20
- D) Exception

**Q21.**
```java
class A {
    String type() { return "A"; }
    String info() { return "info-" + type(); }
}
class B extends A {
    String type() { return "B"; }
}
System.out.println(new B().info());
```
- A) info-A  B) info-B  C) Compilation fails  D) info-null

**Q22.**
```java
record R(int a, int b) {
    R(int a) { this(a, a * 2); }   // constructeur supplémentaire
}
R r = new R(3);
System.out.println(r.a() + "," + r.b());
```
- A) 3,6  B) 3,0  C) Compilation fails  D) 3,3

---
---

## Corrigés

**Q1 → B (B).** Méthode d'instance redéfinie → liaison **dynamique** : la version de l'objet réel (`B`).

**Q2 → B et C.** Les accesseurs d'un record sont `x()`/`y()` (sans `get`). Un record est implicitement `final` et ne peut **pas** `extends`.

**Q3 → B (1).** `ordinal()` renvoie l'index (base 0) : ROUGE=0, VERT=1.

**Q4 → B (exactement 1).** Une interface fonctionnelle a **exactement une** méthode abstraite (les `default`/`static`/`private` ne comptent pas).

**Q5 → A (hi).** La méthode `default` est héritée et appelable sur l'instance.

**Q6 → B (1 1).** `a.v` : un **champ** suit le **type de la référence** (`A`) → 1. `a.get()` est défini dans `A` (non redéfini) ; à l'intérieur, `v` désigne le champ de `A` → 1. Sortie : `1 1`.

**Q7 → C (L).** Phase 1 (sans boxing ni varargs) : `int` → `long` par **élargissement** est applicable → `f(long)` gagne sur `f(Object)` (boxing) et `f(int...)`.

**Q8 → A (5).** Le constructeur compact valide puis l'accesseur `x()` renvoie 5.

**Q9 → B (12).** Chaque constante implémente `a` ; `FOIS` fait la multiplication → 12.

**Q10 → D (Compilation fails).** `A` n'a pas de constructeur sans argument ; `B()` n'appelle pas `super(int)` → le `super()` implicite échoue.

**Q11 → B et D.** `abstract` est incompatible avec `final` (jamais redéfinissable) et `private` (jamais héritée). `public`/`protected` sont permis.

**Q12 → C (3).** `n` est `static` (partagé) ; 3 constructions → 3.

**Q13 → A (A).** Méthode **statique** : masquée, pas redéfinie → résolue selon le **type de la référence** (`A`).

**Q14 → A (123).** `new A(9)` appelle `A(int)`, dont la 1re instruction est `this()`. Dans `A()`, après le `super()` implicite, l'**initialiseur d'instance** s'exécute (`1`) puis le corps de `A()` (`2`). De retour dans `A(int)`, le corps imprime `3`. Ordre : `123`. (L'init d'instance ne se joue **qu'une fois**, dans le constructeur qui chaîne vers `super`.)

**Q15 → B.** Tout sous-type d'un type `sealed` doit être explicitement `final`, `sealed` ou `non-sealed`. Ici `Impl` n'a aucun de ces modificateurs.

**Q16 → B (Compilation fails à la ligne X).** La variable liée `s` n'est dans la portée que là où le test est garanti vrai (le bloc `if`). À la ligne X, `s` n'existe pas.

**Q17 → C (Compilation fails).** Conflit de méthodes `default` héritées de deux interfaces : `C` **doit** redéfinir `m()` (et peut appeler `A.super.m()`).

**Q18 → C (IllegalArgumentException).** `valueOf` avec un nom inconnu lève `IllegalArgumentException` à l'exécution (compile sans souci).

**Q19 → C (`new Outer().new Inner()`).** Une classe **interne** (non statique) nécessite une instance de l'englobante.

**Q20 → A (Compilation fails).** Une lambda ne peut capturer qu'une variable locale **`final` ou effectively final**. La réaffectation `facteur = 3` (ligne X) casse cette propriété → erreur de compilation.

**Q21 → B (info-B).** `info()` (dans `A`) appelle `type()` : liaison **dynamique** sur l'objet `B` → `"B"`. Résultat `info-B`.

**Q22 → A (3,6).** Le constructeur surchargé `R(int a)` délègue au canonique via `this(a, a*2)` → a=3, b=6.
