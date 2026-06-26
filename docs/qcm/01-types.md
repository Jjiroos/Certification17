# QCM 01 — Types, texte, nombres, dates

> Réponds **sans lancer le code** ni regarder les corrigés (en bas). Note ton score dans `docs/plan-revision.md`.
> Une seule bonne réponse sauf mention contraire. Les Q1–Q15 sont 🟢/🟡 ; la section finale ajoute des 🔴 (pièges & format examen).

---

**Q1.** Que s'affiche-t-il ?
```java
int x = 0b101 + 010;
System.out.println(x);
```
- A) 13
- B) 15
- C) 111
- D) Ne compile pas

**Q2.**
```java
Integer a = 1000;
Integer b = 1000;
System.out.println(a == b);
System.out.println(a.equals(b));
```
- A) `true` / `true`
- B) `false` / `true`
- C) `true` / `false`
- D) `false` / `false`

**Q3.** Quelle(s) ligne(s) **ne compile(nt) pas** ?
```java
short s = 10;
s = s + 5;  // L1
s += 5;     // L2
```
- A) L1 uniquement
- B) L2 uniquement
- C) L1 et L2
- D) Aucune

**Q4.**
```java
char c = 'A';
c += 1;
System.out.println(c);
```
- A) 66
- B) B
- C) A1
- D) Ne compile pas

**Q5.**
```java
String s = "hello";
s.concat(" world");
s.toUpperCase();
System.out.println(s);
```
- A) hello
- B) hello world
- C) HELLO
- D) HELLO WORLD

**Q6.**
```java
String a = "x" + "y";
String b = "xy";
System.out.println(a == b);
```
- A) true
- B) false
- C) Ne compile pas
- D) NullPointerException

**Q7.**
```java
System.out.println(1 + 2 + "=" + 1 + 2);
```
- A) 3=3
- B) 3=12
- C) 1212=
- D) 33=

**Q8.**
```java
System.out.println(7 / 2 * 2);
```
- A) 7
- B) 6
- C) 6.0
- D) 8

**Q9.**
```java
System.out.println(1.0 / 0);
```
- A) ArithmeticException
- B) Infinity
- C) NaN
- D) 0.0

**Q10.**
```java
LocalDate d = LocalDate.of(2026, 3, 15);
d.plusDays(10);
System.out.println(d.getDayOfMonth());
```
- A) 25
- B) 15
- C) 10
- D) Ne compile pas

**Q11.**
```java
LocalDate d = LocalDate.of(2026, 1, 31);
System.out.println(d.plusMonths(1));
```
- A) 2026-02-31
- B) 2026-03-03
- C) 2026-02-28
- D) DateTimeException

**Q12.**
```java
var x = 10;
var y;
y = 20;
System.out.println(x + y);
```
- A) 30
- B) Ne compile pas
- C) Erreur à l'exécution
- D) 1020

**Q13.**
```java
StringBuilder sb = new StringBuilder("123");
sb.append("45").insert(0, "0");
System.out.println(sb);
```
- A) 12345
- B) 012345
- C) 045123
- D) 123450

**Q14.**
```java
System.out.println(Period.ofWeeks(2));
```
- A) P2W
- B) P14D
- C) PT14D
- D) P0Y0M14D

**Q15.**
```java
long resultat = Math.round(2.5);
System.out.println(resultat);
```
- A) 2
- B) 3
- C) 2.5
- D) Ne compile pas (round renvoie un int)

## 🔴 Niveau 3 — Pièges & format examen

**Q16.**
```java
String a = "java";
String b = "ja";
String c = b + "va";
System.out.println(a == c);
```
- A) true  B) false  C) Ne compile pas  D) Exception

**Q17.**
```java
char c = 'X';
int i = 0;
System.out.println(true ? c : i);
```
- A) X  B) 88  C) 0  D) Ne compile pas

**Q18.**
```java
int x = 5;
x += 2.5;
System.out.println(x);
```
- A) 7  B) 7.5  C) Ne compile pas  D) 8

**Q19.**
```java
Integer a = 100, b = 100;
Integer c = 200, d = 200;
System.out.println((a == b) + " " + (c == d));
```
- A) true true  B) false false  C) true false  D) false true

**Q20.**
```java
var d = java.time.LocalDate.of(2026, 2, 28).plusDays(1);
System.out.println(d.getMonthValue());
```
- A) 2  B) 3  C) 29  D) Exception

---
---

## Corrigés

**Q1 → A (13).** `0b101` = 5, `010` est **octal** = 8. 5 + 8 = 13. Piège : `010` n'est pas 10.

**Q2 → B (`false` / `true`).** 1000 est **hors** du cache `Integer` (−128..127) : deux objets distincts donc `==` est `false`. `equals` compare les valeurs → `true`. (Avec 100, `==` aurait donné `true`.)

**Q3 → A (L1 uniquement).** `s + 5` est promu en `int` ; l'affecter à un `short` sans cast échoue. En revanche `s += 5` contient un **cast implicite** (`s = (short)(s + 5)`) et compile.

**Q4 → B (B).** `c += 1` contient un cast implicite vers `char` (pas d'erreur de compilation). `'A'` (65) + 1 = 66 → caractère `'B'`. Imprimer un `char` affiche le caractère, pas le code.

**Q5 → A (hello).** `String` est **immuable** : `concat` et `toUpperCase` renvoient de nouvelles chaînes **ignorées**. `s` reste `"hello"`.

**Q6 → A (true).** `"x" + "y"` est une concaténation de **constantes de compilation** : repliée en `"xy"` et **internée** dans le pool. `a` et `b` désignent donc le même objet.

**Q7 → B (3=12).** Évaluation de gauche à droite : `1+2` = 3 (int), puis `+"="` → `"3="` (String), puis `+1` → `"3=1"`, puis `+2` → `"3=12"`.

**Q8 → B (6).** Même priorité, gauche à droite : `7/2` = 3 (division **entière**), `3*2` = 6.

**Q9 → B (Infinity).** Division **flottante** par zéro → `Infinity` (pas d'exception). Seule la division **entière** par zéro lève `ArithmeticException`. `0.0/0` donnerait `NaN`.

**Q10 → B (15).** `LocalDate` est **immuable** : `d.plusDays(10)` renvoie une nouvelle date **ignorée**. `d` reste au 15.

**Q11 → C (2026-02-28).** `plusMonths` ajuste le jour au dernier jour valide du mois cible. 2026 n'est pas bissextile → 28 février.

**Q12 → B (Ne compile pas).** `var y;` est interdit : `var` exige un **initialiseur** sur la même ligne.

**Q13 → B (012345).** `"123"`.append(`"45"`) → `"12345"` (chaînage : renvoie le même builder), puis `.insert(0, "0")` → `"012345"`.

**Q14 → B (P14D).** `Period.ofWeeks(2)` stocke **0 an, 0 mois, 14 jours**. Son `toString` ISO-8601 omet les composantes nulles → `P14D` (il n'existe pas de composante « semaines »).

**Q15 → B (3).** `Math.round(double)` renvoie un **`long`** (donc `long resultat` compile) ; `2.5` arrondit vers le haut → 3.

**Q16 → B (false).** `b + "va"` n'est **pas** une constante de compilation (`b` est une variable) : concaténation à l'exécution → **nouvel** objet hors pool. `a == c` est `false`. (Si `b` était `final`, ce serait `true`.)

**Q17 → B (88).** Expression conditionnelle avec `char` et `int` (non constant) → **promotion numérique** vers `int`. `'X'` vaut 88 → affiché `88`, pas `X`.

**Q18 → A (7).** `x += 2.5` équivaut à `x = (int)(x + 2.5)` = `(int) 7.5` = **7** (cast implicite de l'affectation composée).

**Q19 → C (true false).** 100 est dans le cache `Integer` (−128..127) → `==` vrai ; 200 est hors cache → `==` faux.

**Q20 → B (3).** 2026 n'est pas bissextile (février = 28 j). 28 février + 1 jour = 1er mars → `getMonthValue()` = 3.
