# 🃏 Flashcards de révision — Java SE 17 (1Z0-829)

> **Sur ton téléphone** : ouvre ce fichier (GitHub mobile, Obsidian, ou un viewer Markdown).
> Lis la question, **prédis la réponse de tête**, puis tape **« Réponse »** pour la révéler.
> ~110 cartes réparties sur les 11 domaines. Idéal en 5–10 min dans les transports.

---

## Domaine 1 — Types, texte, nombres, dates

**1.** Que vaut le littéral `010` ?
<details><summary>Réponse</summary>

**8** — un littéral préfixé par `0` est en **octal** (pas décimal).
</details>

**2.** Quelle est la plage du **cache des `Integer`** (où `==` peut valoir `true`) ?
<details><summary>Réponse</summary>

**−128 à 127**. En dehors, deux `Integer` autoboxés sont des objets distincts (`==` → `false`). Toujours comparer avec `.equals()`.
</details>

**3.** Sortie ?
```java
String s = "Bonjour";
s.toUpperCase();
System.out.println(s);
```
<details><summary>Réponse</summary>

`Bonjour` — `String` est **immuable** ; il faut **réaffecter** : `s = s.toUpperCase();`.
</details>

**4.** Sortie ?
```java
System.out.println(1 + 2 + "x");
System.out.println("x" + 1 + 2);
```
<details><summary>Réponse</summary>

`3x` puis `x12` — concaténation évaluée **de gauche à droite**.
</details>

**5.** `7 / 2` et `7.0 / 2` ?
<details><summary>Réponse</summary>

`3` (division **entière**) et `3.5` (un opérande `double`).
</details>

**6.** `5 / 0` vs `5.0 / 0` ?
<details><summary>Réponse</summary>

`ArithmeticException` (entière) vs `Infinity` (flottante, pas d'exception). `0.0/0` → `NaN`.
</details>

**7.** Ce code compile-t-il ?
```java
byte b = 10;
b += 5;
```
<details><summary>Réponse</summary>

**Oui** — l'affectation composée inclut un **cast implicite** : `b = (byte)(b + 5)`. En revanche `byte c = b + 5;` ne compile **pas**.
</details>

**8.** Type et valeur de `Math.round(2.5)` ? Et `Math.round(-2.5)` ?
<details><summary>Réponse</summary>

`Math.round(double)` renvoie un **`long`** ; `round(2.5)` = `3`, `round(-2.5)` = `-2` (arrondi `floor(x+0.5)`).
</details>

**9.** Pourquoi `var x;` et `var y = null;` ne compilent-ils pas ?
<details><summary>Réponse</summary>

`var` exige un **initialiseur** dont le type est inférable. `x` n'en a pas ; `null` n'a pas de type déterminable.
</details>

**10.** `Period.ofWeeks(2)` → quel `toString()` ?
<details><summary>Réponse</summary>

`P14D` — converti en jours (il n'existe pas de composante « semaines »).
</details>

**11.** `LocalDate.of(2026, 1, 31).plusMonths(1)` ?
<details><summary>Réponse</summary>

`2026-02-28` — le jour est ajusté au dernier jour valide du mois (2026 non bissextile).
</details>

**12.** `"x" + "y" == "xy"` → vrai ou faux ?
<details><summary>Réponse</summary>

**`true`** — la concaténation de **constantes de compilation** est repliée et internée dans le pool. (Avec une variable non `final`, ce serait `false`.)
</details>

---

## Domaine 2 — Contrôle de flux

**13.** Quels types sont **interdits** comme sélecteur de `switch` ?
<details><summary>Réponse</summary>

`long`, `float`, `double`, `boolean`. (Autorisés : `byte/short/char/int`, leurs wrappers, `String`, `enum`.)
</details>

**14.** Sortie ?
```java
int x = 1;
switch (x) {
    case 1: System.out.print("a");
    case 2: System.out.print("b"); break;
    default: System.out.print("c");
}
```
<details><summary>Réponse</summary>

`ab` — **fall-through** : sans `break`, on continue dans le `case` suivant.
</details>

**15.** Quelle est la différence entre `yield` et `return` dans un `switch` ?
<details><summary>Réponse</summary>

`yield` produit la **valeur** d'une *expression* switch (forme `->` avec bloc). `return` sort de la méthode.
</details>

**16.** Pourquoi `while (false) { ... }` ne compile-t-il pas ?
<details><summary>Réponse</summary>

Le corps est **inatteignable** (*unreachable statement*). À l'inverse, `if (false) { ... }` est toléré.
</details>

**17.** Sortie ?
```java
int i = 0;
while (i < 3) {
    if (i == 1) continue;
    System.out.print(i);
    i++;
}
```
<details><summary>Réponse</summary>

`0` puis **boucle infinie** — `continue` saute `i++`, donc `i` reste à 1.
</details>

**18.** Une expression `switch` sur un `int` sans `default` qui ne couvre pas tous les cas ?
<details><summary>Réponse</summary>

**Ne compile pas** — une *expression* switch doit être **exhaustive**.
</details>

**19.** Peut-on mélanger `case x ->` et `case x:` dans le même switch ?
<details><summary>Réponse</summary>

**Non** — erreur de compilation.
</details>

**20.** À quoi sert `break label;` ?
<details><summary>Réponse</summary>

À sortir de la **boucle étiquetée** `label` (utile pour quitter des boucles imbriquées). `continue label;` passe à l'itération suivante de cette boucle.
</details>

---

## Domaine 3 — POO

**21.** Que devient le constructeur par défaut quand on déclare un constructeur ?
<details><summary>Réponse</summary>

Il **disparaît** — le compilateur ne fournit plus de constructeur sans argument.
</details>

**22.** Quand `super(args)` est-il **obligatoire** ?
<details><summary>Réponse</summary>

Quand la classe parente n'a **pas** de constructeur sans argument (le `super()` implicite échouerait).
</details>

**23.** Ordre d'exécution à chaque `new` ?
<details><summary>Réponse</summary>

`super(...)` → **initialiseurs d'instance + champs** (ordre textuel) → corps du constructeur. (Les blocs `static` : une seule fois, au chargement.)
</details>

**24.** Sortie ?
```java
class A { int v = 1; }
class B extends A { int v = 2; }
A a = new B();
System.out.println(a.v);
```
<details><summary>Réponse</summary>

`1` — un **champ** suit le **type de la référence** (`A`), pas l'objet réel (pas de polymorphisme sur les champs).
</details>

**25.** Et pour une **méthode** redéfinie, `a.m()` appelle quelle version ?
<details><summary>Réponse</summary>

Celle de l'**objet réel** (`B`) — liaison **dynamique**.
</details>

**26.** Une méthode `static` est-elle redéfinie ou masquée ?
<details><summary>Réponse</summary>

**Masquée** (hiding) → résolue selon le **type de la référence**, pas l'objet.
</details>

**27.** Ordre de priorité pour la résolution de surcharge ?
<details><summary>Réponse</summary>

**Élargissement** (`int→long`) > **autoboxing** (`int→Integer`) > **varargs** (`int...`).
</details>

**28.** Que génère `record Point(int x, int y) {}` ?
<details><summary>Réponse</summary>

Champs `private final`, accesseurs **`x()`/`y()`** (sans `get`), `equals`/`hashCode`/`toString`, constructeur canonique. La classe est **implicitement `final`**.
</details>

**29.** À quoi sert le **constructeur compact** d'un record ?
```java
record P(int x) { P { if (x < 0) throw new IllegalArgumentException(); } }
```
<details><summary>Réponse</summary>

Valider/normaliser les composants. **Pas besoin** d'affecter les champs (fait automatiquement).
</details>

**30.** Un `record` peut-il `extends` une classe ?
<details><summary>Réponse</summary>

**Non** (il est déjà `final` et étend `Record`). Il peut en revanche `implements` des interfaces.
</details>

**31.** Une sous-classe d'un type `sealed` doit être…
<details><summary>Réponse</summary>

`final`, `sealed` (avec son propre `permits`), ou **`non-sealed`**.
</details>

**32.** Sortie ?
```java
interface A { default String m() { return "A"; } }
interface B { default String m() { return "B"; } }
class C implements A, B { }
```
<details><summary>Réponse</summary>

**Ne compile pas** — conflit de `default` : `C` doit redéfinir `m()` (et peut appeler `A.super.m()`).
</details>

**33.** Quels modificateurs sont **interdits** sur une méthode `abstract` ?
<details><summary>Réponse</summary>

`final`, `private`, `static` (incompatibles avec « doit être redéfinie »).
</details>

**34.** Le constructeur d'un `enum` est de quelle visibilité ?
<details><summary>Réponse</summary>

**`private`** (implicitement). On ne peut pas le déclarer `public`.
</details>

**35.** Que lève `MonEnum.valueOf("INCONNU")` ?
<details><summary>Réponse</summary>

`IllegalArgumentException`.
</details>

**36.** Comment instancier une classe **interne** (non statique) `Outer.Inner` ?
<details><summary>Réponse</summary>

`outer.new Inner()` — elle nécessite une instance de l'englobante. (Une classe `static` nested : `new Outer.Nested()`.)
</details>

**37.** Une lambda peut capturer quelles variables locales ?
<details><summary>Réponse</summary>

Uniquement les variables **`final` ou effectively final** (non réaffectées).
</details>

**38.** Combien de méthodes abstraites a une **interface fonctionnelle** ?
<details><summary>Réponse</summary>

**Exactement une** (les `default`/`static`/`private` ne comptent pas).
</details>

---

## Domaine 4 — Exceptions

**39.** `RuntimeException` est checked ou unchecked ?
<details><summary>Réponse</summary>

**Unchecked** (comme `Error`). Les autres `Exception` sont **checked** (vérifiées à la compilation).
</details>

**40.** Sortie ?
```java
static int m() {
    try { return 1; }
    finally { return 2; }
}
```
<details><summary>Réponse</summary>

`2` — un `return` dans le **`finally`** écrase celui du `try`.
</details>

**41.** `catch (Exception e)` avant `catch (IOException e)` ?
<details><summary>Réponse</summary>

**Ne compile pas** — le second catch est inatteignable. Ordre : du **plus spécifique au plus général**.
</details>

**42.** Dans quel ordre les ressources d'un try-with-resources sont-elles fermées ?
<details><summary>Réponse</summary>

**Ordre inverse** de leur déclaration, et **avant** le `catch`/`finally`.
</details>

**43.** Ce multi-catch compile-t-il ? `catch (IOException | FileNotFoundException e)`
<details><summary>Réponse</summary>

**Non** — les types ne doivent pas être liés par héritage (`FileNotFoundException` ⊂ `IOException`).
</details>

**44.** Quel est le type de `e` dans un multi-catch ?
<details><summary>Réponse</summary>

Le **supertype commun**, et `e` est implicitement **`final`**.
</details>

**45.** Une méthode redéfinie peut-elle déclarer une exception **checked** plus large que l'originale ?
<details><summary>Réponse</summary>

**Non** — elle peut en déclarer moins, ou des sous-types. (Elle peut toujours ajouter des unchecked.)
</details>

**46.** Quelle interface une ressource doit-elle implémenter pour try-with-resources ?
<details><summary>Réponse</summary>

`AutoCloseable` (ou `Closeable`).
</details>

---

## Domaine 5 — Tableaux & collections

**47.** `t.length`, `s.length()`, `list.size()` — lequel pour quoi ?
<details><summary>Réponse</summary>

`length` (champ) → **tableau** ; `length()` (méthode) → **String** ; `size()` → **collection**.
</details>

**48.** Sortie ?
```java
List<Integer> l = new ArrayList<>(List.of(10, 20, 30));
l.remove(1);
System.out.println(l);
```
<details><summary>Réponse</summary>

`[10, 30]` — `remove(int)` retire par **index**. `remove(Integer.valueOf(20))` retirerait par **valeur**.
</details>

**49.** `Arrays.binarySearch` sur un élément **absent** renvoie quoi ?
<details><summary>Réponse</summary>

`-(point d'insertion) - 1` (négatif). Le tableau doit être **trié** au préalable.
</details>

**50.** Ordre d'itération de `HashSet`, `LinkedHashSet`, `TreeSet` ?
<details><summary>Réponse</summary>

`HashSet` : **non garanti** ; `LinkedHashSet` : **ordre d'insertion** ; `TreeSet` : **trié**.
</details>

**51.** Que lève une modification de `List.of(1, 2, 3)` ?
<details><summary>Réponse</summary>

`UnsupportedOperationException` (collection **immuable**). Le code compile.
</details>

**52.** `List.of("a", null)` ?
<details><summary>Réponse</summary>

`NullPointerException` — les fabriques `of` n'acceptent **pas** `null`.
</details>

**53.** `Map.of("a", 1, "a", 2)` ?
<details><summary>Réponse</summary>

`IllegalArgumentException` — clé **dupliquée**.
</details>

**54.** Règle PECS pour `List<? extends Number>` ?
<details><summary>Réponse</summary>

**Producer Extends** : on peut **lire** (en `Number`), on ne peut **pas `add`** (sauf `null`).
</details>

**55.** Et `List<? super Integer>` ?
<details><summary>Réponse</summary>

**Consumer Super** : on peut **ajouter** des `Integer` ; lecture en `Object`.
</details>

**56.** Que renvoie `Comparator` / `compareTo` quand `a < b` ?
<details><summary>Réponse</summary>

Une valeur **négative** (`0` si égaux, **positive** si `a > b`).
</details>

**57.** Que se passe-t-il si on modifie une `ArrayList` pendant un for-each ?
<details><summary>Réponse</summary>

`ConcurrentModificationException` (sauf via `Iterator.remove()` ou `removeIf`).
</details>

---

## Domaine 6 — Streams & lambdas

**58.** Que se passe-t-il si un pipeline de stream n'a **pas** d'opération terminale ?
<details><summary>Réponse</summary>

**Rien ne s'exécute** — les opérations intermédiaires sont **paresseuses** (`peek` seul n'imprime rien).
</details>

**59.** Réutiliser un stream déjà consommé ?
<details><summary>Réponse</summary>

`IllegalStateException` — un stream est à **usage unique**.
</details>

**60.** `reduce(identity, acc)` vs `reduce(acc)` — types de retour ?
<details><summary>Réponse</summary>

Le 1er renvoie une **valeur** ; le 2nd renvoie un **`Optional`**.
</details>

**61.** Sur un stream **vide** : `allMatch` ? `anyMatch` ?
<details><summary>Réponse</summary>

`allMatch` → **`true`** (vacuité) ; `anyMatch` → **`false`**.
</details>

**62.** `IntStream.range(1, 5)` vs `rangeClosed(1, 5)` ?
<details><summary>Réponse</summary>

`range` exclut la borne sup (1,2,3,4) ; `rangeClosed` l'inclut (1..5).
</details>

**63.** `orElse(x)` vs `orElseGet(supplier)` ?
<details><summary>Réponse</summary>

`orElse` évalue `x` **toujours** ; `orElseGet` n'appelle le supplier **que si** l'Optional est vide (lazy).
</details>

**64.** `Optional.of(null)` ?
<details><summary>Réponse</summary>

`NullPointerException`. Utiliser `Optional.ofNullable(...)` si la valeur peut être `null`.
</details>

**65.** À quoi sert `flatMap` ?
<details><summary>Réponse</summary>

À **aplatir** : transformer un `Stream<List<T>>` (ou `Stream<Stream<T>>`) en `Stream<T>`.
</details>

**66.** Sortie ?
```java
var m = Stream.of("aa", "b", "cc")
        .collect(Collectors.groupingBy(String::length));
System.out.println(m.get(2));
```
<details><summary>Réponse</summary>

`[aa, cc]` — regroupement par longueur (clé 2).
</details>

**67.** Que renvoie `partitioningBy(predicate)` ?
<details><summary>Réponse</summary>

Une `Map<Boolean, List<T>>` avec **toujours** les clés `true` **et** `false`.
</details>

**68.** `Collectors.toMap` avec deux clés identiques (sans fonction de fusion) ?
<details><summary>Réponse</summary>

`IllegalStateException`.
</details>

**69.** `average()` sur un `IntStream` renvoie quel type ?
<details><summary>Réponse</summary>

`OptionalDouble` (`.getAsDouble()`).
</details>

**70.** Méthodes des interfaces fonctionnelles courantes ?
<details><summary>Réponse</summary>

`Supplier.get`, `Consumer.accept`, `Function.apply`, `Predicate.test`.
</details>

**71.** Sortie ?
```java
Stream.of("a", "b").map(String::toUpperCase)
      .collect(Collectors.joining("-", "[", "]"));
```
<details><summary>Réponse</summary>

`[A-B]` — `joining(sep, prefix, suffix)`.
</details>

---

## Domaine 7 — Modules (JPMS)

**72.** Différence entre `exports` et `opens` ?
<details><summary>Réponse</summary>

`exports` = accès **compilation/API** ; `opens` = accès **réflexif au runtime** (`setAccessible`).
</details>

**73.** Que fait `requires transitive M;` ?
<details><summary>Réponse</summary>

Tout module qui requiert le mien lit **aussi** `M` (lisibilité implicite).
</details>

**74.** Et `requires static M;` ?
<details><summary>Réponse</summary>

`M` est requis à la **compilation**, **optionnel** à l'exécution.
</details>

**75.** Un JAR sans `module-info` sur le **module-path** ?
<details><summary>Réponse</summary>

Un **module automatique** (nom dérivé du fichier, exporte tout, lit tout).
</details>

**76.** Quel module est toujours requis implicitement ?
<details><summary>Réponse</summary>

`java.base`.
</details>

**77.** Un package `public` mais non `exports` est-il visible d'un autre module ?
<details><summary>Réponse</summary>

**Non** — sans `exports`, il reste interne au module.
</details>

**78.** Quelles directives gèrent les **services** (`ServiceLoader`) ?
<details><summary>Réponse</summary>

`uses S;` (consommer) et `provides S with Impl;` (fournir).
</details>

**79.** À quoi servent `jdeps` et `jlink` ?
<details><summary>Réponse</summary>

`jdeps` : analyse les **dépendances** (migration). `jlink` : crée une **image d'exécution minimale**.
</details>

---

## Domaine 8 — Concurrence

**80.** `start()` vs `run()` sur un `Thread` ?
<details><summary>Réponse</summary>

`start()` lance un **nouveau thread** ; `run()` exécute dans le **thread courant** (pas de parallélisme).
</details>

**81.** Appeler `start()` deux fois ?
<details><summary>Réponse</summary>

`IllegalThreadStateException`.
</details>

**82.** `Runnable` vs `Callable<V>` ?
<details><summary>Réponse</summary>

`Callable` **renvoie une valeur** et peut lever une exception **checked** ; `Runnable` non.
</details>

**83.** Quelle méthode d'`ExecutorService` renvoie un `Future` ?
<details><summary>Réponse</summary>

`submit(...)`. (`execute` renvoie `void`.)
</details>

**84.** Que se passe-t-il si on oublie `shutdown()` ?
<details><summary>Réponse</summary>

La JVM peut **ne pas se terminer** (threads non-démons du pool encore actifs).
</details>

**85.** `ConcurrentHashMap` accepte-t-il les `null` ?
<details><summary>Réponse</summary>

**Non** (ni clé ni valeur) — `NullPointerException`. (`HashMap` les accepte.)
</details>

**86.** `volatile` garantit quoi (et quoi non) ?
<details><summary>Réponse</summary>

La **visibilité** entre threads, **pas** l'atomicité (`i++` reste non atomique).
</details>

**87.** Sortie ?
```java
AtomicInteger a = new AtomicInteger(5);
System.out.println(a.getAndIncrement());
```
<details><summary>Réponse</summary>

`5` — `getAndIncrement` renvoie l'**ancienne** valeur (puis l'atomique vaut 6).
</details>

**88.** `join()` vs `get()` sur un `CompletableFuture` ?
<details><summary>Réponse</summary>

`get()` déclare des exceptions **checked** ; `join()` lève une exception **non checked** (`CompletionException`).
</details>

---

## Domaine 9 — I/O & NIO.2

**89.** `Reader`/`Writer` vs `InputStream`/`OutputStream` ?
<details><summary>Réponse</summary>

`Reader`/`Writer` = **caractères (texte)** ; `InputStream`/`OutputStream` = **octets (binaire)**.
</details>

**90.** `Path` vs `Files` ?
<details><summary>Réponse</summary>

`Path` manipule des chemins **sans toucher au disque** ; `Files` effectue les **opérations disque** (lèvent `IOException`).
</details>

**91.** `Path.of("/home/user").resolve("/etc/x")` ?
<details><summary>Réponse</summary>

`/etc/x` — un argument **absolu** remplace tout.
</details>

**92.** `Path.of("/a/b").relativize(Path.of("/a/b/c/d"))` ?
<details><summary>Réponse</summary>

`c/d`.
</details>

**93.** Que renvoie `Files.lines(path)` et que faut-il en faire ?
<details><summary>Réponse</summary>

Un `Stream<String>` **à fermer** (try-with-resources).
</details>

**94.** Un champ `transient` après sérialisation/désérialisation ?
<details><summary>Réponse</summary>

Restauré à sa **valeur par défaut** (`null`, `0`, `false`) — il n'est **pas** sérialisé.
</details>

**95.** La désérialisation appelle-t-elle le constructeur de la classe sérialisable ?
<details><summary>Réponse</summary>

**Non** (mais celui du premier ancêtre **non** sérialisable est appelé).
</details>

**96.** Que renvoie `BufferedReader.readLine()` en fin de flux ?
<details><summary>Réponse</summary>

`null`.
</details>

---

## Domaine 10 — JDBC

**97.** Quelle méthode pour un `SELECT` ? Pour un `INSERT/UPDATE/DELETE` ?
<details><summary>Réponse</summary>

`executeQuery` → `ResultSet` ; `executeUpdate` → `int` (nb de lignes affectées).
</details>

**98.** Les colonnes d'un `ResultSet` et les `?` d'un `PreparedStatement` sont indexés à partir de…
<details><summary>Réponse</summary>

**1** (1-based, jamais 0).
</details>

**99.** Lire `rs.getString(1)` sans appeler `rs.next()` avant ?
<details><summary>Réponse</summary>

`SQLException` — le curseur est **avant** la première ligne.
</details>

**100.** Que renvoie `rs.next()` ?
<details><summary>Réponse</summary>

Un **`boolean`** (`true` s'il y a une ligne) et avance le curseur.
</details>

**101.** Avantages d'un `PreparedStatement` ?
<details><summary>Réponse</summary>

Protection contre l'**injection SQL**, réutilisable avec paramètres, précompilé.
</details>

**102.** En Java 17, faut-il `Class.forName(driver)` ?
<details><summary>Réponse</summary>

**Non** — le pilote est chargé automatiquement (ServiceLoader) s'il est sur le classpath.
</details>

**103.** Quelle exception (checked) caractérise JDBC ?
<details><summary>Réponse</summary>

`SQLException`.
</details>

---

## Domaine 11 — Localisation

**104.** En Java 17, comment créer un `Locale("fr","FR")` ?
<details><summary>Réponse</summary>

`new Locale("fr", "FR")` — `Locale.of(...)` n'existe qu'à partir de Java **19**.
</details>

**105.** Fichier de **base** d'un `ResourceBundle("messages", ...)` ?
<details><summary>Réponse</summary>

`messages.properties` (sans suffixe de locale).
</details>

**106.** Ordre de résolution d'un bundle pour `fr_FR` ?
<details><summary>Réponse</summary>

`messages_fr_FR` → `messages_fr` → **bundle de la locale par défaut** → `messages` (base).
</details>

**107.** Que lève `bundle.getString("cle_absente")` ?
<details><summary>Réponse</summary>

`MissingResourceException`.
</details>

**108.** `NumberFormat.getPercentInstance(...).format(0.25)` ?
<details><summary>Réponse</summary>

`25%` — le pourcentage **multiplie par 100**.
</details>

**109.** Dans un motif `DecimalFormat`, `0` vs `#` ?
<details><summary>Réponse</summary>

`0` = chiffre **obligatoire** (zéro de remplissage) ; `#` = chiffre **optionnel**.
</details>

**110.** `MessageFormat.format("{0} sur {1}", 3, 10)` ?
<details><summary>Réponse</summary>

`3 sur 10`.
</details>

---

> 💡 **Conseil de révision** : passe les cartes une 1re fois en lisant tout, puis une 2e fois en **cachant la réponse**. Marque mentalement (ou dans `plan-revision.md`) les domaines où tu hésites.
