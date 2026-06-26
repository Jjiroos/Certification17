# QCM 04 — Exceptions

> Réponds **sans lancer le code**. Corrigés en bas.
> 🟢 N1 · 🟡 N2 · 🔴 N3 · ⚠️ plusieurs réponses possibles sur certaines questions.

---

## 🟢 Niveau 1 — Fondamentaux

**Q1.** (Choose two) Quelles exceptions sont **unchecked** ?
- A) `IOException`  B) `NullPointerException`  C) `SQLException`  D) `ArithmeticException`

**Q2.**
```java
try {
    System.out.print("A");
    throw new RuntimeException();
} catch (RuntimeException e) {
    System.out.print("B");
} finally {
    System.out.print("C");
}
```
- A) AB  B) ABC  C) AC  D) A puis exception

**Q3.**
```java
System.out.println(5 / 0);
```
- A) 0  B) Infinity  C) ArithmeticException  D) Compilation fails

**Q4.** Un `try` est valide s'il est suivi de…
- A) uniquement `catch`
- B) uniquement `finally`
- C) `catch` et/ou `finally` (ou des ressources)
- D) rien

## 🟡 Niveau 2 — Intermédiaire

**Q5.**
```java
static int m() {
    try { return 1; }
    finally { return 2; }
}
System.out.println(m());
```
- A) 1  B) 2  C) 12  D) Compilation fails

**Q6.**
```java
try {
    String s = null;
    System.out.println(s.length());
} catch (Exception e) {
    System.out.print("X");
} catch (NullPointerException e) {
    System.out.print("Y");
}
```
- A) X  B) Y  C) XY  D) Compilation fails

**Q7.**
```java
class Res implements AutoCloseable {
    String n; Res(String n){this.n=n;}
    public void close(){ System.out.print("c"+n); }
}
try (Res a = new Res("1"); Res b = new Res("2")) {
    System.out.print("body");
}
```
- A) bodyc1c2  B) bodyc2c1  C) c1c2body  D) c2c1body

**Q8.**
```java
try {
    throw new IllegalStateException("x");
} catch (IllegalArgumentException | IllegalStateException e) {
    System.out.print(e.getMessage());
}
```
- A) x  B) (rien)  C) Compilation fails  D) Exception non attrapée

**Q9.** (Choose two) Lesquelles **ne compilent pas** ?
- A) `catch (IOException | FileNotFoundException e)`
- B) `catch (IOException | RuntimeException e)`
- C) `catch (Exception e) {} catch (IOException e) {}`
- D) `catch (IOException e) {} catch (Exception e) {}`

## 🔴 Niveau 3 — Pièges & format examen

**Q10.**
```java
class Res implements AutoCloseable {
    public void close() { System.out.print("close "); }
}
try (Res r = new Res()) {
    throw new RuntimeException("boom");
} catch (RuntimeException e) {
    System.out.print("catch " + e.getMessage());
}
```
- A) catch boom close  B) close catch boom  C) catch boom  D) close

**Q11.**
```java
static int m() {
    int x = 0;
    try {
        x = 1;
        return x;
    } finally {
        x = 99;
    }
}
System.out.println(m());
```
- A) 0  B) 1  C) 99  D) Compilation fails

**Q12.**
```java
class Parent {
    void m() throws java.io.IOException { }
}
class Enfant extends Parent {
    void m() throws Exception { }   // redéfinition
}
```
- A) Compile  B) Compilation fails (exception checked élargie)  C) Exception runtime  D) Avertissement seulement

**Q13.**
```java
static void m() {            // pas de throws
    throw new java.io.IOException();
}
```
- A) Compile  B) Compilation fails  C) Compile mais exception runtime  D) Rien

**Q14.**
```java
String resultat;
try {
    resultat = "ok";
    throw new RuntimeException();
} catch (RuntimeException e) {
    resultat = "catch";
} finally {
    resultat = "finally";
}
System.out.println(resultat);
```
- A) ok  B) catch  C) finally  D) Compilation fails

**Q15.**
```java
try {
    try {
        throw new RuntimeException("inner");
    } finally {
        System.out.print("F1 ");
    }
} catch (RuntimeException e) {
    System.out.print("C " + e.getMessage());
}
```
- A) C inner F1  B) F1 C inner  C) C inner  D) F1

**Q16.**
```java
Object o = Integer.valueOf(5);
String s = (String) o;
```
- A) Compile et s = "5"  B) Compilation fails  C) ClassCastException  D) NullPointerException

**Q17.** (Choose two) Vrai à propos de try-with-resources :
- A) La ressource doit implémenter `AutoCloseable`
- B) Les ressources sont fermées dans l'ordre de déclaration
- C) `close()` est appelé avant le `catch`
- D) On ne peut référencer qu'une variable déclarée dans le `try(...)`

---
---

## Corrigés

**Q1 → B et D.** `NullPointerException` et `ArithmeticException` étendent `RuntimeException` → unchecked. `IOException` et `SQLException` sont checked.

**Q2 → B (ABC).** `A` (try), exception attrapée → `B`, puis `finally` → `C`.

**Q3 → C (ArithmeticException).** Division **entière** par zéro lève `ArithmeticException` à l'exécution.

**Q4 → C.** Un `try` doit être suivi d'un `catch`, d'un `finally`, ou des deux (ou contenir des ressources).

**Q5 → B (2).** Le `return 2` du `finally` **écrase** le `return 1` du `try`.

**Q6 → D (Compilation fails).** `catch (Exception e)` masque `catch (NullPointerException e)` placé après → second catch **inatteignable** → erreur de compilation.

**Q7 → B (bodyc2c1).** Corps d'abord, puis fermeture en **ordre inverse** : `c2` puis `c1`.

**Q8 → A (x).** `IllegalStateException` fait partie du multi-catch → message `x`.

**Q9 → A et C.** A : `FileNotFoundException` est une sous-classe d'`IOException` → multi-catch invalide. C : `catch (Exception)` avant `catch (IOException)` → inatteignable. (B et D sont valides.)

**Q10 → B (close catch boom).** En try-with-resources, `close()` s'exécute **avant** le `catch`. D'où `close ` puis `catch boom`.

**Q11 → B (1).** La valeur de retour est **évaluée** au `return x` (x=1) avant le `finally`. Modifier `x` dans `finally` ne change pas la valeur déjà retenue (ce n'est pas un `return` dans le finally). Résultat `1`.

**Q12 → B (Compilation fails).** Une redéfinition ne peut pas déclarer une exception **checked plus large** : `Exception` est plus large qu'`IOException`.

**Q13 → B (Compilation fails).** `IOException` est checked et n'est ni attrapée ni déclarée → erreur de compilation.

**Q14 → C (finally).** Chaque bloc réaffecte `resultat` ; le `finally` s'exécute en dernier → `finally`.

**Q15 → B (F1 C inner).** Le `finally` interne s'exécute **pendant** la propagation, **avant** que le `catch` externe ne reçoive l'exception. Donc `F1 ` d'abord, puis `C inner`.

**Q16 → C (ClassCastException).** Le cast compile (types liés via `Object`) mais échoue à l'exécution : un `Integer` n'est pas un `String`.

**Q17 → A et C.** La ressource doit être `AutoCloseable` et `close()` est appelé avant le `catch`. (B est faux : ordre **inverse** ; D est faux depuis Java 9 : variable effectively final acceptée.)
