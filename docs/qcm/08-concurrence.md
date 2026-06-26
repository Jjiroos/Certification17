# QCM 08 — Concurrence

> Réponds **sans lancer le code**. Corrigés en bas.
> 🟢 N1 · 🟡 N2 · 🔴 N3 · ⚠️ plusieurs réponses possibles sur certaines questions.

---

## 🟢 Niveau 1 — Fondamentaux

**Q1.** Quelle différence entre `Runnable` et `Callable<V>` ?
- A) Aucune
- B) `Callable` renvoie une valeur et peut lever une exception checked ; `Runnable` non
- C) `Runnable` renvoie une valeur
- D) `Callable` ne s'utilise pas avec un ExecutorService

**Q2.**
```java
Thread t = new Thread(() -> System.out.print("X"));
t.run();
```
- A) Lance un nouveau thread
- B) Exécute "X" dans le thread courant (pas de nouveau thread)
- C) Compilation fails
- D) Ne fait rien

**Q3.** Quelle méthode d'`ExecutorService` renvoie un `Future` ?
- A) `execute`  B) `submit`  C) `run`  D) `start`

**Q4.** Que se passe-t-il si on n'appelle jamais `shutdown()` sur un pool ?
- A) Rien de spécial
- B) La JVM peut ne pas se terminer (threads non-démons actifs)
- C) Exception immédiate
- D) Le pool se ferme tout seul

**Q5.** (Choose two) Quelles classes sont des collections **thread-safe** ?
- A) `ArrayList`  B) `ConcurrentHashMap`  C) `HashMap`  D) `CopyOnWriteArrayList`

## 🟡 Niveau 2 — Intermédiaire

**Q6.**
```java
AtomicInteger a = new AtomicInteger(5);
System.out.println(a.getAndIncrement());
System.out.println(a.get());
```
- A) 5 puis 6  B) 6 puis 6  C) 5 puis 5  D) 6 puis 5

**Q7.**
```java
int r = CompletableFuture.supplyAsync(() -> 2)
        .thenApply(x -> x * 10)
        .join();
System.out.println(r);
```
- A) 2  B) 20  C) 12  D) Exception

**Q8.**
```java
Thread t = new Thread(() -> {});
t.start();
t.start();
```
- A) Rien  B) IllegalThreadStateException  C) Compilation fails  D) Deux threads

**Q9.**
```java
ConcurrentHashMap<String,String> m = new ConcurrentHashMap<>();
m.put("a", null);
```
- A) {a=null}  B) NullPointerException  C) Compilation fails  D) Ignoré

**Q10.**
```java
int somme = IntStream.rangeClosed(1, 5).parallel().sum();
System.out.println(somme);
```
- A) 15 (déterministe)  B) ordre/valeur indéterminés  C) 0  D) Exception

**Q11.** `volatile` garantit…
- A) l'atomicité de `i++`
- B) la **visibilité** d'une variable entre threads
- C) l'exclusion mutuelle
- D) que la variable ne change jamais

## 🔴 Niveau 3 — Pièges & format examen

**Q12.**
```java
List<Integer> l = new ArrayList<>();
IntStream.range(0, 1000).parallel().forEach(l::add);
System.out.println(l.size());
```
- A) Toujours 1000
- B) Taille imprévisible ou exception (ArrayList non thread-safe)
- C) 0
- D) Compilation fails

**Q13.**
```java
String r = CompletableFuture.supplyAsync(() -> "a")
        .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "b"))
        .join();
System.out.println(r);
```
- A) a  B) b  C) ab  D) CompletableFuture[ab]

**Q14.** Différence entre `join()` et `get()` sur un `CompletableFuture` ?
- A) Aucune
- B) `get()` déclare des exceptions checked, `join()` lève une exception non checked
- C) `join()` ne bloque pas
- D) `get()` ne bloque pas

**Q15.**
```java
int compteur = 0;
ExecutorService pool = Executors.newFixedThreadPool(8);
for (int i = 0; i < 10000; i++) pool.submit(() -> compteur++);
```
- A) Ne compile pas (compteur doit être effectively final)
- B) Donne toujours 10000
- C) Compile et donne 10000
- D) Aucun problème

**Q16.** (Choose two) Comment incrémenter correctement un compteur partagé entre threads ?
- A) `int` simple `count++`
- B) `AtomicInteger.incrementAndGet()`
- C) bloc `synchronized`
- D) `volatile int count; count++`

**Q17.** `Executors.newFixedThreadPool(2)` avec 5 tâches soumises…
- A) lève une exception (trop de tâches)
- B) exécute 2 tâches en parallèle, les autres attendent dans la file
- C) crée 5 threads
- D) ignore 3 tâches

---
---

## Corrigés

**Q1 → B.** `Callable<V>` : `V call() throws Exception`. `Runnable` : `void run()` sans exception checked.

**Q2 → B.** Appeler `run()` directement exécute le code dans le **thread courant**, sans parallélisme. Il faut `start()` pour un nouveau thread.

**Q3 → B (`submit`).** `submit` renvoie un `Future` ; `execute` renvoie `void`.

**Q4 → B.** Les threads d'un pool sont non-démons par défaut : sans `shutdown()`, la JVM peut rester active.

**Q5 → B et D.** `ConcurrentHashMap` et `CopyOnWriteArrayList` sont thread-safe ; `ArrayList`/`HashMap` non.

**Q6 → A (5 puis 6).** `getAndIncrement` renvoie l'**ancienne** valeur (5), puis l'atomique vaut 6.

**Q7 → B (20).** `supplyAsync(()->2).thenApply(*10)` = 20 ; `join()` récupère.

**Q8 → B.** Relancer un `Thread` déjà démarré → `IllegalThreadStateException`.

**Q9 → B.** `ConcurrentHashMap` **n'accepte pas** `null` (clé ou valeur).

**Q10 → A (15).** `sum()` est une réduction associative : résultat **déterministe** même en parallèle (seul l'ordre de traitement varie).

**Q11 → B.** `volatile` assure la **visibilité**, **pas** l'atomicité (`i++` reste non atomique).

**Q12 → B.** `ArrayList` n'est pas thread-safe : `add` concurrent → taille incorrecte ou `ArrayIndexOutOfBoundsException`/`NullPointerException`.

**Q13 → C (ab).** `thenCompose` enchaîne deux étapes asynchrones : `"a"` puis `"a"+"b"` = `"ab"`.

**Q14 → B.** `get()` déclare `InterruptedException`/`ExecutionException` (checked) ; `join()` lève une `CompletionException` non checked.

**Q15 → A (Ne compile pas).** `compteur++` mute la variable locale `compteur` capturée par la lambda : elle n'est donc **pas** effectively final → erreur de compilation. (Et même si on contournait via un champ, `count++` non synchronisé serait une race condition.)

**Q16 → B et C.** `AtomicInteger` et `synchronized` garantissent l'incrément correct. `volatile` ne suffit pas (n'assure pas l'atomicité).

**Q17 → B.** Un pool fixe de 2 exécute 2 tâches à la fois ; les autres patientent dans la file d'attente interne.
