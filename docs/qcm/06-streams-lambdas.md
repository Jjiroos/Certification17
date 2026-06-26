# QCM 06 — Streams & lambdas

> Réponds **sans lancer le code**. Corrigés en bas.
> 🟢 N1 · 🟡 N2 · 🔴 N3 · ⚠️ plusieurs réponses possibles sur certaines questions.

---

## 🟢 Niveau 1 — Fondamentaux

**Q1.**
```java
long n = Stream.of("a", "bb", "ccc").filter(s -> s.length() > 1).count();
System.out.println(n);
```
- A) 1  B) 2  C) 3  D) 0

**Q2.**
```java
System.out.println(IntStream.range(1, 4).sum());
```
- A) 6  B) 10  C) 3  D) 4

**Q3.**
```java
int r = Stream.of(1, 2, 3, 4).reduce(0, Integer::sum);
System.out.println(r);
```
- A) 0  B) 10  C) 24  D) Optional[10]

**Q4.**
```java
List<String> l = Stream.of("c", "a", "b").sorted().collect(Collectors.toList());
System.out.println(l);
```
- A) [c, a, b]  B) [a, b, c]  C) [c, b, a]  D) Exception

**Q5.**
```java
System.out.println(Stream.of("x","y").map(String::toUpperCase).collect(Collectors.joining()));
```
- A) x y  B) XY  C) [X, Y]  D) X-Y

## 🟡 Niveau 2 — Intermédiaire

**Q6.**
```java
Optional<Integer> o = Stream.of(1, 2, 3).filter(x -> x > 5).findFirst();
System.out.println(o.orElse(-1));
```
- A) 0  B) -1  C) NoSuchElementException  D) null

**Q7.**
```java
System.out.println(IntStream.rangeClosed(1, 4).average().getAsDouble());
```
- A) 2  B) 2.5  C) 2.0  D) 10

**Q8.**
```java
var m = Stream.of("aa","b","cc","d")
        .collect(Collectors.groupingBy(String::length));
System.out.println(m);
```
- A) {1=[b, d], 2=[aa, cc]}  B) {2=[aa, cc], 1=[b, d]}  C) [aa, b, cc, d]  D) {b=1, aa=2}

**Q9.**
```java
long c = Stream.of(List.of(1,2), List.of(3,4,5)).flatMap(List::stream).count();
System.out.println(c);
```
- A) 2  B) 5  C) 3  D) 6

**Q10.** (Choose two) Lesquelles sont des opérations **terminales** ?
- A) `map`  B) `count`  C) `filter`  D) `collect`

**Q11.**
```java
List<Integer> l = Stream.iterate(1, x -> x + 2).limit(4).collect(Collectors.toList());
System.out.println(l);
```
- A) [1, 2, 3, 4]  B) [1, 3, 5, 7]  C) [2, 4, 6, 8]  D) boucle infinie

## 🔴 Niveau 3 — Pièges & format examen

**Q12.**
```java
Stream<Integer> s = Stream.of(1, 2, 3);
s.forEach(System.out::print);
s.forEach(System.out::print);
```
- A) 123123  B) 123 puis IllegalStateException  C) 123  D) Compilation fails

**Q13.**
```java
System.out.println(Stream.<Integer>empty().allMatch(x -> x > 10));
System.out.println(Stream.<Integer>empty().anyMatch(x -> x > 10));
```
- A) true / true  B) false / false  C) true / false  D) false / true

**Q14.**
```java
Stream.of("a", "b", "c").peek(System.out::print).filter(s -> false);
```
- A) abc  B) (rien)  C) Compilation fails  D) Exception

**Q15.**
```java
var m = Stream.of("alice", "bob", "anna")
        .collect(Collectors.toMap(s -> s.charAt(0), s -> s));
```
- A) {a=anna, b=bob}  B) {a=alice, b=bob}  C) IllegalStateException  D) {a=[alice, anna], b=[bob]}

**Q16.** Quelle différence entre `orElse` et `orElseGet` ?
- A) Aucune
- B) `orElse` évalue toujours son argument ; `orElseGet` seulement si l'Optional est vide
- C) `orElseGet` lève une exception si vide
- D) `orElse` n'accepte que des String

**Q17.**
```java
int somme = Stream.of("1", "2", "3").mapToInt(Integer::parseInt).sum();
System.out.println(somme);
```
- A) 123  B) 6  C) 0  D) Compilation fails

**Q18.**
```java
Optional<String> o = Optional.ofNullable(null);
System.out.println(o.map(String::toUpperCase).orElse("vide"));
```
- A) null  B) NullPointerException  C) vide  D) VIDE

**Q19.**
```java
List<Integer> l = Stream.of(5, 3, 8, 1)
        .sorted(Comparator.reverseOrder())
        .limit(2)
        .collect(Collectors.toList());
System.out.println(l);
```
- A) [1, 3]  B) [8, 5]  C) [5, 3]  D) [8, 5, 3, 1]

---
---

## Corrigés

**Q1 → B (2).** "bb" et "ccc" ont une longueur > 1.

**Q2 → A (6).** `range(1,4)` = 1,2,3 (4 exclu) → somme 6.

**Q3 → B (10).** `reduce(0, sum)` renvoie une **valeur** : 1+2+3+4 = 10.

**Q4 → B ([a, b, c]).** `sorted()` (ordre naturel).

**Q5 → B (XY).** `joining()` sans séparateur concatène.

**Q6 → B (-1).** Aucun élément > 5 → `Optional` vide → `orElse(-1)`.

**Q7 → B (2.5).** Moyenne de 1,2,3,4 = 2.5 (`OptionalDouble`).

**Q8 → A ({1=[b, d], 2=[aa, cc]}).** `groupingBy(length)` regroupe par longueur. Le `HashMap` retourné affiche les clés `Integer` 1 puis 2 (ordre des buckets).

**Q9 → B (5).** `flatMap` aplatit → 2 + 3 = 5 éléments.

**Q10 → B et D.** `count` et `collect` sont terminales ; `map`/`filter` sont intermédiaires.

**Q11 → B ([1, 3, 5, 7]).** `iterate(1, x->x+2)` : 1,3,5,7… `limit(4)` borne le stream infini.

**Q12 → B (123 puis IllegalStateException).** Le 2ᵉ `forEach` réutilise un stream **déjà consommé** → exception à l'exécution.

**Q13 → C (true / false).** Sur un stream **vide** : `allMatch` = `true` (vacuité), `anyMatch` = `false`.

**Q14 → B (rien).** **Aucune opération terminale** → le pipeline ne s'exécute pas, `peek` n'imprime rien.

**Q15 → C (IllegalStateException).** Deux clés `'a'` (alice, anna) en collision dans `toMap` sans fonction de fusion → exception.

**Q16 → B.** `orElse(x)` évalue `x` **systématiquement** ; `orElseGet(supplier)` n'appelle le supplier **que si** l'Optional est vide.

**Q17 → B (6).** `mapToInt(parseInt)` → 1,2,3 → `sum()` = 6.

**Q18 → C (vide).** L'Optional est vide ; `map` reste vide ; `orElse("vide")` → "vide".

**Q19 → B ([8, 5]).** Tri décroissant → 8,5,3,1 ; `limit(2)` → [8, 5].
