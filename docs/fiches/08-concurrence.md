# Fiche 08 — Concurrence

> Objectif d'examen : *Managing concurrent code execution.*

## 1. Threads

- Deux façons : implémenter **`Runnable`** (recommandé) ou étendre **`Thread`**.
- 🪤 `start()` lance un **nouveau thread** ; `run()` exécute **dans le thread courant** (pas de parallélisme).
- 🪤 Appeler `start()` **deux fois** sur le même `Thread` → `IllegalThreadStateException`.
- `Runnable` : `void run()` (pas de retour, pas d'exception checked). `Callable<V>` : `V call() throws Exception`.

## 2. `ExecutorService` (préféré aux threads bruts)

- Fabriques (`Executors`) : `newFixedThreadPool(n)`, `newSingleThreadExecutor()`, `newCachedThreadPool()`, `newScheduledThreadPool(n)`, `newVirtualThreadPerTaskExecutor()` (21+, hors 17).
- `submit(Runnable|Callable)` → renvoie un **`Future`** ; `execute(Runnable)` → rien.
- `invokeAll(tasks)` → `List<Future>` (attend tout) ; `invokeAny(tasks)` → un résultat.
- **Arrêt** : `shutdown()` (finit les tâches en cours, refuse les nouvelles) ; `shutdownNow()` (tente d'interrompre) ; `awaitTermination(timeout, unit)`.
- 🪤 Oublier `shutdown()` → la JVM **ne se termine pas** (threads non-démons).

## 3. `Future`

- `get()` : **bloque** jusqu'au résultat (peut lever `InterruptedException`, `ExecutionException`).
- `get(timeout, unit)` : `TimeoutException` si dépassé. `isDone()`, `cancel(boolean)`.

## 4. `CompletableFuture`

- `supplyAsync(supplier)` (renvoie une valeur), `runAsync(runnable)`.
- Enchaînements : `thenApply(fn)` (transforme), `thenAccept(consumer)`, `thenRun(runnable)`,
  `thenCompose(fn)` (enchaîne un autre CF), `thenCombine(autre, biFn)` (combine deux CF).
- Récupération : `join()` (non checked) ou `get()` (checked).

## 5. Collections concurrentes & synchronisation

- `ConcurrentHashMap`, `CopyOnWriteArrayList`, `ConcurrentLinkedQueue`, `BlockingQueue` (`ArrayBlockingQueue`, `LinkedBlockingQueue`).
- 🪤 `ConcurrentHashMap` **n'accepte pas** les clés/valeurs `null` (contrairement à `HashMap`).
- `synchronized` (méthode ou bloc) : verrou d'exclusion mutuelle.
- `volatile` : garantit la **visibilité** d'une variable entre threads (pas l'atomicité d'un `i++`).

## 6. Variables atomiques

- `AtomicInteger`, `AtomicLong`, `AtomicReference` : opérations atomiques sans verrou.
- `incrementAndGet()` (renvoie la **nouvelle**), `getAndIncrement()` (renvoie l'**ancienne**), `addAndGet(n)`, `compareAndSet(attendu, nouveau)`.
- 🪤 `count++` sur un `int` partagé = **race condition** (lecture+écriture non atomique) → utiliser `AtomicInteger` ou `synchronized`.

## 7. Streams parallèles

- `collection.parallelStream()` ou `stream().parallel()`.
- 🪤 L'ordre de traitement n'est **pas garanti** ; `forEach` peut s'exécuter dans le désordre (utiliser `forEachOrdered` pour préserver l'ordre).
- Une opération de réduction doit être **associative** et **sans état partagé** pour un résultat correct. `sum`, `reduce` associatif → résultat déterministe.

## 8. Problèmes classiques

- **Race condition** : accès concurrent non synchronisé à un état mutable.
- **Deadlock** : deux threads s'attendent mutuellement sur des verrous.
- **Livelock** : threads actifs mais sans progression. **Starvation** : un thread n'obtient jamais le verrou.

---

### 🪤 Récap des pièges
1. `start()` (nouveau thread) vs `run()` (thread courant) ; double `start()` → exception.
2. `Callable` renvoie une valeur + peut lever une exception checked ; `Runnable` non.
3. `submit` renvoie un `Future` ; `Future.get()` bloque.
4. Oublier `shutdown()` empêche l'arrêt de la JVM.
5. `ConcurrentHashMap` refuse `null`.
6. `count++` non atomique → race condition ; `volatile` ≠ atomicité.
7. Stream parallèle : ordre non garanti, réduction doit être associative.
8. `join()` (non checked) vs `get()` (checked) sur `CompletableFuture`.
