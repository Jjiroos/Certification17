# QCM 02 — Contrôle de flux

> Réponds **sans lancer le code** (conditions d'examen). Corrigés en bas.
> 🟢 N1 fondamentaux · 🟡 N2 intermédiaire · 🔴 N3 pièges & format examen
> ⚠️ Certaines questions ont **plusieurs** bonnes réponses (« Choose two »).

---

## 🟢 Niveau 1 — Fondamentaux

**Q1.**
```java
int i = 0;
while (i < 3) { System.out.print(i); i++; }
```
- A) 012  B) 0123  C) 123  D) boucle infinie

**Q2.**
```java
int j = 10;
do { System.out.print(j); j++; } while (j < 5);
```
- A) (rien)  B) 10  C) 1011...  D) Ne compile pas

**Q3.**
```java
int x = 4;
String r = (x % 2 == 0) ? "pair" : "impair";
System.out.println(r);
```
- A) pair  B) impair  C) true  D) Ne compile pas

**Q4.**
```java
for (int k = 0; k < 6; k += 2) System.out.print(k);
```
- A) 0123456  B) 024  C) 0246  D) 12345

**Q5.**
```java
for (int x = 0; x < 5; x++) {
    if (x == 3) break;
    System.out.print(x);
}
```
- A) 012  B) 0123  C) 01234  D) 0124

## 🟡 Niveau 2 — Intermédiaire

**Q6.**
```java
int n = 1;
switch (n) {
    case 1: System.out.print("A");
    case 2: System.out.print("B");
    default: System.out.print("C");
}
```
- A) A  B) AB  C) ABC  D) AC

**Q7.**
```java
char c = 'b';
switch (c) {
    default: System.out.print("X");
    case 'a': System.out.print("Y"); break;
    case 'b': System.out.print("Z");
}
```
- A) Z  B) XY  C) XYZ  D) ZX

**Q8.**
```java
String s = switch (3) {
    case 1, 2 -> "petit";
    case 3, 4 -> "moyen";
    default -> "grand";
};
System.out.println(s);
```
- A) petit  B) moyen  C) grand  D) Ne compile pas

**Q9.**
```java
int produit = 1;
for (int i = 1, j = 4; i < j; i++, j--) produit *= i;
System.out.println(produit);
```
- A) 1  B) 2  C) 6  D) 24

**Q10.** (Choose two) Quels types sont **autorisés** comme sélecteur d'un `switch` ?
- A) `long`  B) `String`  C) `boolean`  D) `int`

**Q11.**
```java
int total = 0;
for (int i = 0; i < 4; i++)
    total += i;
    System.out.println(total);
```
- A) 0\n1\n3\n6  B) 6  C) Ne compile pas  D) 0123

## 🔴 Niveau 3 — Pièges & format examen

**Q12.**
```java
int i = 0;
while (i < 3) {
    if (i == 1) continue;
    System.out.print(i);
    i++;
}
```
- A) 02  B) 012  C) 0 puis boucle infinie  D) 0

**Q13.**
```java
long x = 2;
switch (x) {
    case 1: break;
    case 2: System.out.print("deux"); break;
}
```
- A) deux  B) (rien)  C) Compilation fails  D) Exception à l'exécution

**Q14.**
```java
int n = 1;
String s = switch (n) {
    case 1 -> "un";
    case 2 -> "deux";
};
```
- A) Compile et s vaut "un"  B) Compilation fails  C) Exception  D) s vaut null

**Q15.**
```java
int n = 2;
String s = switch (n) {
    case 1 -> "a";
    case 2: yield "b";
    default -> "c";
};
```
- A) b  B) c  C) Compilation fails  D) a

**Q16.**
```java
int x = 5;
if (x > 0)
    if (x > 10)
        System.out.print("A");
else
    System.out.print("B");
```
- A) A  B) B  C) (rien)  D) AB

**Q17.**
```java
String jour = switch (3) {
    case 1 -> "lun";
    case 2 -> "mar";
    case 3 -> { yield "mer"; }
    default -> "?";
};
System.out.println(jour);
```
- A) mer  B) ?  C) Compilation fails (yield interdit ici)  D) mer?

**Q18.**
```java
while (false) {
    System.out.println("ici");
}
```
- A) Compile et n'affiche rien  B) Compilation fails (unreachable)  C) Affiche "ici"  D) Boucle infinie

**Q19.**
```java
int count = 0;
outer:
for (int i = 0; i < 3; i++) {
    for (int j = 0; j < 3; j++) {
        if (j == 1) continue outer;
        count++;
    }
}
System.out.println(count);
```
- A) 3  B) 6  C) 9  D) 0

**Q20.**
```java
final int UN = 1;
int v = 1;
switch (v) {
    case UN: System.out.print("ok");
}
```
- A) ok  B) Compilation fails (UN non constant)  C) (rien)  D) Exception

**Q21.**
```java
int x = 5;
if (x = 0) System.out.println("zero");
```
- A) zero  B) (rien)  C) Compilation fails  D) Exception

---
---

## Corrigés

**Q1 → A (012).** `i` vaut 0,1,2 puis 3 arrête. Imprime `012`.

**Q2 → B (10).** `do-while` exécute le corps **au moins une fois** : imprime 10, `j` devient 11, `11 < 5` faux → stop.

**Q3 → A (pair).** `4 % 2 == 0` vrai → branche « pair ».

**Q4 → B (024).** `k` = 0, 2, 4 (à 6, `k < 6` faux). Imprime `024`.

**Q5 → A (012).** `break` à `x == 3` : imprime 0,1,2 puis sort.

**Q6 → C (ABC).** Pas de `break` → **fall-through** : entre en `case 1`, puis tombe dans 2 et `default`. `ABC`.

**Q7 → A (Z).** Le sélecteur vaut `'b'` → on entre **directement** au `case 'b'` (l'ordre du `default` ne change rien au point d'entrée). Imprime `Z`, puis fin du switch. `default` n'est exécuté que si **aucun** case ne matche.

**Q8 → B (moyen).** `case 3, 4 -> "moyen"`. Forme flèche, pas de fall-through.

**Q9 → B (2).** i=1,j=4 → `produit=1` ; i=2,j=3 → `produit=2` ; i=3,j=2 → `3 < 2` faux, stop. Résultat `2`.

**Q10 → B et D.** `String` et `int` sont autorisés. `long` et `boolean` **ne le sont pas**.

**Q11 → B (6).** Sans accolades, **seule** `total += i;` est dans le `for`. Le `println` s'exécute **une fois** après la boucle. `total` = 0+1+2+3 = `6`.

**Q12 → C (0 puis boucle infinie).** Quand `i == 1`, `continue` saute `i++` → `i` reste à 1 indéfiniment. Imprime `0` puis tourne sans fin.

**Q13 → C (Compilation fails).** `switch` n'accepte **pas** `long` comme sélecteur.

**Q14 → B (Compilation fails).** Une **expression** switch sur un `int` doit être **exhaustive** : sans `default`, le compilateur refuse (tous les `int` ne sont pas couverts).

**Q15 → C (Compilation fails).** On **ne mélange pas** les formes `->` et `:` (`yield`) dans le même switch.

**Q16 → C (rien).** **Dangling else** : le `else` se rattache à `if (x > 10)`. Comme `x > 10` est faux et `x > 0` vrai, aucune branche n'imprime → rien.

**Q17 → A (mer).** En forme flèche, un `case` peut avoir un **bloc** qui produit sa valeur via `yield`. `yield "mer"` → `mer`.

**Q18 → B (Compilation fails).** `while (false)` rend le corps **inatteignable** → erreur de compilation. (À l'inverse, `if (false) {}` est toléré.)

**Q19 → A (3).** Pour chaque `i`, à `j == 1` on fait `continue outer` : seul `j == 0` incrémente `count`. 3 itérations de `i` → `count = 3`.

**Q20 → A (ok).** Une variable `final` initialisée par une constante est une **constante de compilation** → utilisable comme étiquette `case`. Imprime `ok`.

**Q21 → C (Compilation fails).** `x = 0` est une **affectation** de type `int`, pas un `boolean`. La condition d'un `if` doit être `boolean`. (Avec un `boolean b`, `if (b = true)` compilerait.)
