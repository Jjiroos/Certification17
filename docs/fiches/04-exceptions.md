# Fiche 04 — Gestion des exceptions

> Objectif d'examen : *Handling Exceptions.*

## 1. Hiérarchie

```
Throwable
├── Error              (non récupérable : OutOfMemoryError, StackOverflowError) — UNCHECKED
└── Exception          — CHECKED (sauf RuntimeException)
    └── RuntimeException — UNCHECKED (NPE, ClassCastException, ArithmeticException,
                           ArrayIndexOutOfBounds, NumberFormatException, IllegalArgument…)
```

- **Checked** : `Exception` (hors `RuntimeException`) → **doivent** être attrapées ou déclarées (`throws`). Vérifié à la **compilation**.
- **Unchecked** : `RuntimeException` et `Error` → aucune obligation.
- 🪤 `catch (Exception e)` attrape aussi les `RuntimeException` ; `catch (Throwable t)` attrape **tout** (y compris `Error`).

## 2. `try` / `catch` / `finally`

- `finally` s'exécute **toujours** (succès, exception, ou `return` dans le `try`).
  - Exception : `System.exit(...)` ou l'arrêt de la JVM.
- 🪤 Un `return` (ou `throw`) dans le **`finally`** **écrase** celui du `try` (mauvaise pratique, mais testé).
- 🪤 **Ordre des `catch`** : du **plus spécifique au plus général**. Mettre `catch (Exception e)` avant `catch (IOException e)` → **erreur de compilation** (catch inatteignable).
- Un `try` doit avoir **au moins** un `catch` **ou** un `finally` (ou des ressources).

## 3. `try`-with-resources

```java
try (Reader r = open(); Writer w = create()) {  // ressources AutoCloseable
    ...
}  // close() appelé automatiquement
```
- La ressource doit implémenter **`AutoCloseable`** (ou `Closeable`).
- 🪤 Les ressources sont **fermées dans l'ordre inverse** de leur déclaration.
- 🪤 `close()` est appelé **avant** le `catch`/`finally` éventuel.
- Depuis Java 9 : on peut référencer une variable **`final` ou effectively final** dans le `try(...)` (pas besoin de la déclarer dedans).
- **Exceptions supprimées** : si le corps lance une exception **et** `close()` aussi, celle de `close()` est *supprimée* (accessible via `getSuppressed()`).

## 4. Multi-catch

```java
catch (IOException | SQLException e) { ... }
```
- 🪤 Les types alternatifs **ne doivent pas** être liés par héritage (`catch (IOException | FileNotFoundException e)` → erreur, `FileNotFoundException` est une sous-classe).
- La variable `e` est implicitement **`final`** ; son type est le **supertype commun**.

## 5. Exceptions personnalisées

- Étendre `Exception` (checked) ou `RuntimeException` (unchecked).
- Constructeurs utiles : `super(message)`, `super(message, cause)`. Chaînage via `getCause()`.

## 6. Redéfinition & `throws`

- 🪤 Une méthode qui **redéfinit** ne peut **pas** déclarer d'exceptions **checked plus larges** que la méthode parente (elle peut en déclarer moins, ou des sous-types).
- Elle peut **toujours** ajouter des **unchecked**.

---

### 🪤 Récap des pièges
1. `RuntimeException` et `Error` sont **unchecked** ; les autres `Exception` sont **checked**.
2. `finally` s'exécute toujours ; un `return`/`throw` dedans **écrase** le reste.
3. `catch` du plus spécifique au plus général, sinon code inatteignable → compile error.
4. try-with-resources : fermeture en **ordre inverse**, **avant** catch/finally.
5. Multi-catch : pas de types liés par héritage ; `e` est `final`.
6. Redéfinition : pas d'élargissement des exceptions **checked**.
