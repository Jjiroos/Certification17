# QCM 05 — Tableaux & collections

> Réponds **sans lancer le code**. Corrigés en bas.
> 🟢 N1 · 🟡 N2 · 🔴 N3 · ⚠️ plusieurs réponses possibles sur certaines questions.

---

## 🟢 Niveau 1 — Fondamentaux

**Q1.**
```java
int[] t = new int[3];
System.out.println(t[0] + " " + t.length);
```
- A) null 3  B) 0 3  C) 0 0  D) Compilation fails

**Q2.**
```java
Set<String> s = new HashSet<>();
s.add("a"); s.add("a"); s.add("b");
System.out.println(s.size());
```
- A) 1  B) 2  C) 3  D) 0

**Q3.** (Choose two) Quelles collections **interdisent les doublons** ?
- A) `ArrayList`  B) `HashSet`  C) `TreeSet`  D) `LinkedList`

**Q4.**
```java
List<String> l = new ArrayList<>();
l.add("x"); l.add("y");
System.out.println(l.get(1));
```
- A) x  B) y  C) 1  D) Exception

**Q5.**
```java
Map<String,Integer> m = new HashMap<>();
m.put("a", 1);
m.put("a", 2);
System.out.println(m.size() + " " + m.get("a"));
```
- A) 2 1  B) 1 2  C) 2 2  D) 1 1

## 🟡 Niveau 2 — Intermédiaire

**Q6.**
```java
List<Integer> l = new ArrayList<>(List.of(10, 20, 30));
l.remove(1);
System.out.println(l);
```
- A) [20, 30]  B) [10, 30]  C) [10, 20]  D) Exception

**Q7.**
```java
List<Integer> l = new ArrayList<>(List.of(10, 20, 30));
l.remove(Integer.valueOf(10));
System.out.println(l);
```
- A) [20, 30]  B) [10, 30]  C) [10, 20]  D) Exception

**Q8.**
```java
TreeSet<String> s = new TreeSet<>();
s.add("banane"); s.add("abricot"); s.add("cerise");
System.out.println(s.first());
```
- A) banane  B) abricot  C) cerise  D) ordre indéterminé

**Q9.**
```java
Map<String,Integer> m = new HashMap<>();
m.put("x", 5);
m.merge("x", 3, Integer::sum);
m.merge("y", 9, Integer::sum);
System.out.println(m.get("x") + " " + m.get("y"));
```
- A) 5 9  B) 8 9  C) 8 null  D) 5 null

**Q10.** (Choose two) Avec `List<? extends Number> l` :
- A) On peut faire `l.add(3)`
- B) On peut faire `Number n = l.get(0)`
- C) On peut faire `l.add(null)`
- D) On peut lire la taille avec `l.size()`

**Q11.**
```java
List<String> l = new ArrayList<>(List.of("a","b","c","d"));
l.removeIf(s -> s.compareTo("b") <= 0);
System.out.println(l);
```
- A) [a, b]  B) [c, d]  C) [b, c, d]  D) [a, b, c, d]

## 🔴 Niveau 3 — Pièges & format examen

**Q12.**
```java
int[] t = {1, 3, 5, 7};
System.out.println(Arrays.binarySearch(t, 4));
```
- A) 2  B) -2  C) -3  D) -1

**Q13.**
```java
List<Integer> l = List.of(1, 2, 3);
l.add(4);
```
- A) [1, 2, 3, 4]  B) Compilation fails  C) UnsupportedOperationException  D) Exception ignorée

**Q14.**
```java
var m = Map.of("a", 1, "b", 2, "a", 3);
```
- A) {a=3, b=2}  B) {a=1, b=2}  C) IllegalArgumentException  D) Compile et écrase

**Q15.**
```java
List<Integer> l = new ArrayList<>(List.of(1, 2, 3, 4));
for (Integer x : l) {
    if (x == 1) l.remove(x);
}
```
- A) [2, 3, 4]  B) ConcurrentModificationException  C) [1, 2, 3, 4]  D) IndexOutOfBoundsException

**Q16.**
```java
String[] noms = {"Bob", "Alice", "Carl"};
Arrays.sort(noms);
System.out.println(noms[0]);
```
- A) Bob  B) Alice  C) Carl  D) ordre inchangé

**Q17.**
```java
List<String> l = List.of("a", null);
```
- A) [a, null]  B) NullPointerException  C) Compilation fails  D) [a]

**Q18.**
```java
Deque<Integer> d = new ArrayDeque<>();
d.offer(1); d.offer(2); d.offer(3);
System.out.println(d.poll() + " " + d.peek());
```
- A) 1 2  B) 3 2  C) 1 1  D) 3 3

---
---

## Corrigés

**Q1 → B (0 3).** Valeur par défaut d'un `int` = 0 ; `t.length` = 3.

**Q2 → B (2).** `HashSet` rejette le doublon `"a"` → {a, b}.

**Q3 → B et C.** `HashSet` et `TreeSet` interdisent les doublons ; `ArrayList`/`LinkedList` les acceptent.

**Q4 → B (y).** Index 1 = deuxième élément.

**Q5 → B (1 2).** Même clé `"a"` → taille 1, la 2ᵉ valeur (2) **écrase** la première.

**Q6 → B ([10, 30]).** `remove(1)` (argument `int`) retire l'élément à l'**index** 1 (= 20).

**Q7 → A ([20, 30]).** `remove(Integer.valueOf(10))` retire la **valeur** 10.

**Q8 → B (abricot).** `TreeSet` trie par ordre naturel ; `first()` = plus petit.

**Q9 → B (8 9).** `merge("x",3,sum)` → 5+3=8 (clé présente) ; `merge("y",9,sum)` → 9 (clé absente : valeur initiale).

**Q10 → B et D.** `? extends Number` : lecture en `Number` et `size()` OK ; **pas** d'`add` (même `null` n'est utile que comme exception théorique, et `add(3)` est interdit).

**Q11 → B ([c, d]).** `removeIf` retire "a" et "b" (≤ "b") → reste [c, d].

**Q12 → C (-3).** 4 est absent ; point d'insertion = index 2 → résultat `-(2) - 1 = -3`.

**Q13 → C (UnsupportedOperationException).** `List.of(...)` est **immuable** ; `add` lève cette exception à l'exécution (le code **compile**).

**Q14 → C (IllegalArgumentException).** `Map.of` avec une **clé dupliquée** (`"a"`) lève `IllegalArgumentException`.

**Q15 → B (ConcurrentModificationException).** Retirer un élément d'une `ArrayList` pendant un for-each modifie le `modCount` ; au tour suivant, `next()` détecte l'incohérence et lève `ConcurrentModificationException`. (⚠️ Cas piège connexe : si on retire l'**avant-dernier** élément, `hasNext()` renvoie `false` avant la vérification et **aucune** exception n'est levée.)

**Q16 → B (Alice).** Tri naturel des `String` (ordre lexicographique) → "Alice" en premier.

**Q17 → B (NullPointerException).** `List.of` **n'accepte pas `null`**.

**Q18 → A (1 2).** `offer` ajoute en queue (FIFO via `poll`) : `poll()` retire la tête (1), `peek()` lit la nouvelle tête (2).
