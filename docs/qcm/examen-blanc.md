# Examen blanc — 50 questions (conditions réelles)

> **Chronomètre-toi : 90 minutes.** Réponds **sans lancer le code**, sans notes.
> Score de passage réel : **68 %** (≈ 34/50). Vise ≥ 34. Corrigés tout en bas.
> ⚠️ « (2 réponses) » = choisir exactement deux options.

---

**Q1.** `System.out.println(0x10 + 010);`
- A) 24  B) 20  C) 18  D) 110

**Q2.** `System.out.println(Math.round(2.5) + Math.round(-2.5));`
- A) 0  B) 1  C) -1  D) 5

**Q3.**
```java
String s = "abc";
s.concat("d");
System.out.println(s);
```
- A) abcd  B) abc  C) d  D) Exception

**Q4.** `System.out.println(7 / 2 + 7 % 2);`
- A) 3  B) 4  C) 3.5  D) 7

**Q5.**
```java
int x = 5;
x += 2.5;
System.out.println(x);
```
- A) 7  B) 7.5  C) 8  D) Ne compile pas

**Q6.**
```java
int x = 1;
switch (x) {
    case 1: System.out.print("a");
    case 2: System.out.print("b"); break;
    default: System.out.print("c");
}
```
- A) a  B) ab  C) abc  D) ac

**Q7.**
```java
String r = switch (2) { case 1 -> "a"; case 2 -> "b"; default -> "c"; };
System.out.println(r);
```
- A) a  B) b  C) c  D) Ne compile pas

**Q8.**
```java
int i = 0;
for (; i < 3; i++);
System.out.println(i);
```
- A) 2  B) 3  C) 0  D) Boucle infinie

**Q9.**
```java
while (false) { System.out.println("x"); }
```
- A) x  B) (rien)  C) Ne compile pas  D) Boucle infinie

**Q10.**
```java
class A { int v = 1; }
class B extends A { int v = 2; }
A a = new B();
System.out.println(a.v);
```
- A) 1  B) 2  C) Ne compile pas  D) Exception

**Q11.**
```java
class A { String m() { return "A"; } }
class B extends A { String m() { return "B"; } }
System.out.println(((A) new B()).m());
```
- A) A  B) B  C) Ne compile pas  D) Exception

**Q12.** `record P(int x) {}` — quelle affirmation est vraie ?
- A) `P` peut `extends` une classe
- B) `new P(5).equals(new P(5))` vaut `true`
- C) L'accesseur s'appelle `getX()`
- D) `P` est mutable

**Q13.**
```java
enum E { A, B, C }
System.out.println(E.C.ordinal());
```
- A) 0  B) 1  C) 2  D) C

**Q14.**
```java
interface X { default String m() { return "X"; } }
interface Y { default String m() { return "Y"; } }
class Z implements X, Y { }
```
- A) Compile, "X"  B) Compile, "Y"  C) Ne compile pas  D) Exception

**Q15.**
```java
class A { static String s() { return "A"; } }
class B extends A { static String s() { return "B"; } }
A a = new B();
System.out.println(a.s());
```
- A) A  B) B  C) Ne compile pas  D) Exception

**Q16.** (2 réponses) Modificateurs **interdits** sur une méthode `abstract` :
- A) `public`  B) `final`  C) `private`  D) `protected`

**Q17.**
```java
sealed interface S permits A {}
final class A implements S {}
non-sealed class B implements S {}
```
- A) Compile  B) Ne compile pas (B non listé dans `permits`)  C) Exception  D) Avertissement

**Q18.**
```java
try {
    System.out.print("a");
    throw new RuntimeException();
} catch (Exception e) {
    System.out.print("b");
} finally {
    System.out.print("c");
}
```
- A) ab  B) abc  C) ac  D) a

**Q19.**
```java
static int m() {
    try { return 1; }
    finally { return 2; }
}
System.out.println(m());
```
- A) 1  B) 2  C) Ne compile pas  D) Exception

**Q20.**
```java
class R implements AutoCloseable {
    public void close() { System.out.print("close "); }
}
try (R r = new R()) {
    throw new RuntimeException("x");
} catch (Exception e) {
    System.out.print("catch");
}
```
- A) catch close  B) close catch  C) catch  D) close

**Q21.** (2 réponses) Lesquelles **ne compilent pas** ?
- A) `catch (IOException | FileNotFoundException e)`
- B) `catch (RuntimeException | IOException e)`
- C) `catch (Exception e) {} catch (IOException e) {}`
- D) `catch (IOException e) {} catch (Exception e) {}`

**Q22.**
```java
List<Integer> l = new ArrayList<>(List.of(10, 20, 30));
l.remove(1);
System.out.println(l);
```
- A) [20, 30]  B) [10, 30]  C) [10, 20]  D) Exception

**Q23.**
```java
List<Integer> l = List.of(1, 2, 3);
l.add(4);
```
- A) [1, 2, 3, 4]  B) UnsupportedOperationException  C) Ne compile pas  D) Ignoré

**Q24.**
```java
int[] t = {1, 3, 5, 7};
System.out.println(Arrays.binarySearch(t, 6));
```
- A) -4  B) -3  C) 3  D) -1

**Q25.**
```java
Set<String> s = new TreeSet<>(List.of("c", "a", "b"));
System.out.println(s);
```
- A) [c, a, b]  B) [a, b, c]  C) [c, b, a]  D) ordre indéterminé

**Q26.** `var m = Map.of("a", 1, "a", 2);`
- A) {a=2}  B) {a=1}  C) IllegalArgumentException  D) NullPointerException

**Q27.**
```java
long n = Stream.of("a", "bb", "ccc").filter(x -> x.length() > 1).count();
System.out.println(n);
```
- A) 1  B) 2  C) 3  D) 0

**Q28.** `System.out.println(IntStream.rangeClosed(1, 4).sum());`
- A) 6  B) 10  C) 4  D) 24

**Q29.**
```java
Optional<Integer> o = Stream.of(1, 2, 3).filter(x -> x > 5).findFirst();
System.out.println(o.orElse(-1));
```
- A) 0  B) -1  C) Exception  D) null

**Q30.**
```java
System.out.println(Stream.<Integer>empty().allMatch(x -> x > 0));
```
- A) true  B) false  C) Exception  D) 0

**Q31.**
```java
Stream<Integer> s = Stream.of(1, 2, 3);
s.count();
s.count();
```
- A) 0  B) 3  C) IllegalStateException  D) 6

**Q32.**
```java
var m = Stream.of("aa", "b", "cc").collect(Collectors.groupingBy(String::length));
System.out.println(m.get(2));
```
- A) [aa, cc]  B) [b]  C) 2  D) {2=[aa, cc]}

**Q33.**
```java
int r = CompletableFuture.supplyAsync(() -> 3).thenApply(x -> x * 4).join();
System.out.println(r);
```
- A) 3  B) 12  C) 7  D) Exception

**Q34.**
```java
String r = Stream.of("a", "b", "c").collect(Collectors.joining("-", "[", "]"));
System.out.println(r);
```
- A) a-b-c  B) [a-b-c]  C) [a, b, c]  D) abc

**Q35.** Quelle directive de module ouvre un package à la **réflexion runtime** ?
- A) `exports`  B) `opens`  C) `requires`  D) `provides`

**Q36.** Un JAR **sans** `module-info` placé sur le **module-path** est…
- A) ignoré  B) un module automatique  C) le module sans nom  D) une erreur

**Q37.** Quel module est **toujours** requis implicitement ?
- A) `java.lang`  B) `java.base`  C) `java.se`  D) `java.util`

**Q38.**
```java
Thread t = new Thread(() -> System.out.print("X"));
t.run();
```
- A) Nouveau thread imprime X  B) X dans le thread courant  C) Ne compile pas  D) Rien

**Q39.**
```java
AtomicInteger a = new AtomicInteger(5);
System.out.println(a.getAndIncrement() + " " + a.get());
```
- A) 5 6  B) 6 6  C) 5 5  D) 6 5

**Q40.** (2 réponses) Collections **thread-safe** :
- A) `ArrayList`  B) `ConcurrentHashMap`  C) `HashMap`  D) `CopyOnWriteArrayList`

**Q41.**
```java
ConcurrentHashMap<String,String> m = new ConcurrentHashMap<>();
m.put("k", null);
```
- A) {k=null}  B) NullPointerException  C) Ne compile pas  D) Ignoré

**Q42.** `System.out.println(Path.of("/a/b").relativize(Path.of("/a/b/c/d")));`
- A) /a/b/c/d  B) c/d  C) ../../c/d  D) Exception

**Q43.** `System.out.println(Path.of("/home/user").resolve("/etc/x"));`
- A) /home/user/etc/x  B) /etc/x  C) /home/user  D) Exception

**Q44.**
```java
class C implements Serializable {
    transient int n = 5;
    String s = "hi";
}
// après sérialisation/désérialisation :
```
- A) n=5, s=hi  B) n=0, s=hi  C) n=5, s=null  D) n=0, s=null

**Q45.** `Files.lines(path)` renvoie…
- A) `List<String>`  B) `Stream<String>` (à fermer)  C) `String`  D) `byte[]`

**Q46.** Quelle méthode JDBC exécute un `SELECT` et renvoie un `ResultSet` ?
- A) `executeUpdate`  B) `executeQuery`  C) `execute` (renvoie un `int`)  D) `runQuery`

**Q47.** Les colonnes d'un `ResultSet` et les paramètres d'un `PreparedStatement` sont indexés à partir de…
- A) 0  B) 1  C) -1  D) selon le pilote

**Q48.** `lire rs.getString(1)` **sans** appeler `rs.next()` au préalable…
- A) renvoie la 1re valeur  B) SQLException  C) renvoie null  D) Ne compile pas

**Q49.** `System.out.println(NumberFormat.getPercentInstance(Locale.US).format(0.2));`
- A) 0.2%  B) 20%  C) 0,2  D) 20.0

**Q50.** Ordre de résolution d'un `ResourceBundle` pour `new Locale("fr", "FR")` (du + prioritaire) ?
- A) base → fr → fr_FR
- B) fr_FR → fr → (locale par défaut) → base
- C) fr_FR uniquement
- D) base uniquement

---
---

## Corrigés

| Q | Rép | Pourquoi (résumé) |
|---|-----|-------------------|
| 1 | **A** | 0x10=16, 010 (octal)=8 → 24 |
| 2 | **B** | round(2.5)=3, round(-2.5)=-2 → 1 |
| 3 | **B** | String immuable : `concat` ignoré |
| 4 | **B** | 7/2=3, 7%2=1 → 4 |
| 5 | **A** | `x += 2.5` = `(int)(5+2.5)` = 7 |
| 6 | **B** | fall-through : a puis b (break) |
| 7 | **B** | switch expression `case 2 -> "b"` |
| 8 | **B** | corps vide ; i atteint 3 |
| 9 | **C** | `while(false)` → corps inatteignable |
| 10 | **A** | champ → type de la référence (A) |
| 11 | **B** | méthode redéfinie → dynamique (B) |
| 12 | **B** | record : `equals` par valeur ; final ; accesseur `x()` |
| 13 | **C** | ordinal de C = 2 |
| 14 | **C** | conflit de `default` à résoudre |
| 15 | **A** | méthode static → type de la référence (A) |
| 16 | **B, C** | `final` et `private` interdits avec `abstract` |
| 17 | **B** | `B` n'est pas dans `permits A` |
| 18 | **B** | a, catch b, finally c |
| 19 | **B** | `return` du finally écrase celui du try |
| 20 | **B** | `close()` avant le `catch` |
| 21 | **A, C** | A : sous-classe dans multi-catch ; C : catch large avant étroit |
| 22 | **B** | `remove(1)` = index 1 (20) |
| 23 | **B** | `List.of` immuable → exception |
| 24 | **A** | 6 absent ; point d'insertion = index 3 → -(3)-1 = **-4** |
| 25 | **B** | `TreeSet` trié |
| 26 | **C** | clé dupliquée dans `Map.of` |
| 27 | **B** | "bb", "ccc" |
| 28 | **B** | 1+2+3+4 = 10 |
| 29 | **B** | aucun > 5 → orElse(-1) |
| 30 | **A** | `allMatch` sur vide = true |
| 31 | **C** | stream réutilisé |
| 32 | **A** | groupe longueur 2 = [aa, cc] |
| 33 | **B** | 3 × 4 = 12 |
| 34 | **B** | joining avec préfixe/suffixe |
| 35 | **B** | `opens` |
| 36 | **B** | module automatique |
| 37 | **B** | `java.base` |
| 38 | **B** | `run()` = thread courant |
| 39 | **A** | getAndIncrement renvoie l'ancienne (5), puis 6 |
| 40 | **B, D** | `ConcurrentHashMap`, `CopyOnWriteArrayList` |
| 41 | **B** | `ConcurrentHashMap` refuse `null` |
| 42 | **B** | relatif = c/d |
| 43 | **B** | resolve avec absolu remplace |
| 44 | **B** | `transient` → 0 ; `s` restauré |
| 45 | **B** | `Stream<String>` à fermer |
| 46 | **B** | `executeQuery` |
| 47 | **B** | 1-based |
| 48 | **B** | curseur avant la 1re ligne → SQLException |
| 49 | **B** | percent ×100 → 20% |
| 50 | **B** | fr_FR → fr → défaut → base |

> ⚠️ **Q24 — rappel** : `binarySearch` d'un élément absent renvoie `-(point d'insertion) - 1`.
> Pour 6 dans `{1,3,5,7}`, le point d'insertion est l'index **3** (entre 5 et 7) → `-(3)-1` = **-4**.
> (Entraîne-toi à recalculer ce type de valeur : c'est un grand classique de l'examen.)

### Bilan
- **≥ 45/50** : excellent, tu es prêt.
- **34–44** : tu passes, mais consolide les domaines où tu as perdu des points.
- **< 34** : reprends les fiches et QCM des domaines faibles avant de réessayer.
