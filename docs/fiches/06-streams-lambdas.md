# Fiche 06 — Streams & lambdas

> Objectif d'examen : *Working with Streams and Lambda expressions.* **Domaine le plus représenté.**

## 1. Cycle de vie d'un stream

`source → opérations intermédiaires (lazy) → opération terminale (eager)`

- 🪤 Un stream est **paresseux** : les intermédiaires ne s'exécutent **qu'au déclenchement** de l'opération terminale.
- 🪤 Un stream ne peut être **consommé qu'une fois** : le réutiliser → `IllegalStateException`.
- 🪤 Sans opération terminale, **rien** ne s'exécute (`peek` seul n'affiche rien).

## 2. Création

- `Stream.of(a, b, c)`, `collection.stream()`, `Arrays.stream(tab)`.
- Infinis (à borner avec `limit`) : `Stream.iterate(seed, f)`, `Stream.generate(supplier)`.
  - `Stream.iterate(seed, hasNext, next)` (3 args) est bornable sans `limit`.
- Primitifs : `IntStream.range(a, b)` (**b exclu**), `IntStream.rangeClosed(a, b)` (**b inclus**).

## 3. Opérations intermédiaires (renvoient un Stream)

`filter`, `map`, `mapToInt/Long/Double`, `flatMap`, `distinct`, `sorted` (naturel ou `Comparator`), `peek`, `limit(n)`, `skip(n)`, `boxed` (primitif → objet).

- `flatMap` : aplatit un `Stream<Stream<T>>` / `Stream<List<T>>` en `Stream<T>`.

## 4. Opérations terminales

- `forEach`, `count`, `collect`, `reduce`, `min`/`max`, `toArray`.
- `anyMatch` / `allMatch` / `noneMatch`, `findFirst` / `findAny`.
  - 🪤 Sur un stream **vide** : `allMatch` et `noneMatch` renvoient **`true`** ; `anyMatch` renvoie **`false`**.
- Court-circuit : `findFirst`, `anyMatch`, `limit` arrêtent dès que possible.

### `reduce`
- `reduce(identity, accumulator)` → renvoie **T**.
- `reduce(accumulator)` → renvoie **`Optional<T>`** (pas d'identité).

## 5. `Optional`

- Création : `Optional.of(v)` (🪤 `null` → NPE), `Optional.ofNullable(v)`, `Optional.empty()`.
- Lecture : `isPresent()`, `isEmpty()`, `get()` (🪤 `NoSuchElementException` si vide).
- 🪤 `orElse(v)` : la valeur par défaut est **toujours évaluée** ; `orElseGet(supplier)` : évaluée **seulement si vide** (lazy).
- `orElseThrow()`, `map`, `filter`, `ifPresent(consumer)`, `ifPresentOrElse`.
- Variantes primitives : `OptionalInt`, `OptionalDouble` (`getAsInt()`, `getAsDouble()`).

## 6. Streams primitifs

- `IntStream`/`LongStream`/`DoubleStream` : `sum()`, `average()` (→ `OptionalDouble`), `max()`/`min()` (→ `OptionalInt`), `summaryStatistics()`.
- `mapToObj`, `boxed` pour repasser en `Stream<Integer>`.

## 7. `Collectors`

| Collector | Résultat |
|-----------|----------|
| `toList()`, `toSet()` | `List` / `Set` |
| `toMap(kFn, vFn)` | `Map` (🪤 clé en double → `IllegalStateException`, sauf fonction de fusion) |
| `joining(sep, prefix, suffix)` | `String` |
| `groupingBy(classifier)` | `Map<K, List<T>>` |
| `groupingBy(classifier, downstream)` | regroupe + agrège (`counting()`, `summingInt`, `mapping`, `toSet`…) |
| `partitioningBy(predicate)` | `Map<Boolean, List<T>>` (toujours les clés `true` **et** `false`) |
| `counting`, `summingInt`, `averagingInt`, `mapping`, `reducing` | agrégations |

## 8. Lambdas & interfaces fonctionnelles

- `Supplier<T>` → `get()` ; `Consumer<T>` → `accept(t)` ; `Function<T,R>` → `apply(t)` ;
  `Predicate<T>` → `test(t)` ; `BiFunction`, `UnaryOperator<T>`, `BinaryOperator<T>`, `BiConsumer`, `BiPredicate`.
- Primitives : `IntFunction`, `ToIntFunction`, `IntPredicate`, `IntUnaryOperator`…
- Composition : `Function.andThen` / `compose`, `Predicate.and` / `or` / `negate`, `Consumer.andThen`.
- 🪤 Une lambda capture des variables locales **`final` ou effectively final**.
- Références de méthode : `Type::staticM`, `instance::m`, `Type::instanceM`, `Type::new`.

---

### 🪤 Récap des pièges
1. Streams **paresseux** : sans terminale, rien ne s'exécute.
2. Stream **à usage unique** → `IllegalStateException` au 2ᵉ usage.
3. `range` exclut la borne sup ; `rangeClosed` l'inclut.
4. Stream **vide** : `allMatch`/`noneMatch` = `true`, `anyMatch` = `false`.
5. `reduce(acc)` → `Optional` ; `reduce(id, acc)` → valeur.
6. `Optional.of(null)` → NPE ; `orElse` toujours évalué vs `orElseGet` paresseux.
7. `toMap` avec clés dupliquées → `IllegalStateException`.
8. `findFirst`/`anyMatch`/`limit` court-circuitent.
