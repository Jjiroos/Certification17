# Fiche 02 — Contrôle de flux

> Objectif d'examen : *Controlling Program Flow.*

## 1. `if` / `else`

- La condition **doit être un `boolean`** : `if (x)` avec `x` de type `int` **ne compile pas** (≠ C/C++).
- Sans accolades, **une seule instruction** appartient au `if` :
  ```java
  if (cond)
      a();   // dans le if
      b();   // TOUJOURS exécutée (hors du if), même si l'indentation suggère le contraire
  ```
- 🪤 **Dangling else** : un `else` se rattache au **`if` le plus proche** non encore apparié.
- Affectation vs comparaison : `if (x = true)` compile si `x` est `boolean` (affectation !), mais `if (x = 5)` ne compile pas.

## 2. `switch` — instruction classique (`:`)

Types autorisés : **`byte`, `short`, `char`, `int`** et leurs wrappers, **`String`**, **`enum`**.
🪤 **Interdits** : `long`, `float`, `double`, `boolean`.

- Les étiquettes `case` doivent être des **constantes de compilation** (littéraux ou `final` constants).
- 🪤 **Fall-through** : sans `break`, l'exécution **continue dans les `case` suivants** (et dans `default`).
- `default` peut être placé **n'importe où** (pas forcément en dernier) ; il s'exécute si aucun `case` ne correspond (en respectant le fall-through).
- 🪤 `case` avec une valeur **dupliquée** → erreur de compilation.

## 3. `switch` — expression (`->`) (Java 14+)

```java
String t = switch (n) {
    case 1, 2 -> "petit";          // plusieurs étiquettes séparées par des virgules
    case 3 -> "moyen";
    default -> "grand";
};
```
- Renvoie **une valeur** ; **pas de fall-through** avec `->`.
- Pour un **bloc**, on produit la valeur avec **`yield`** :
  ```java
  int x = switch (n) {
      case 1 -> 10;
      default -> { int v = calcul(); yield v; }   // yield, PAS return
  };
  ```
- 🪤 **Exhaustivité** : une *expression* switch doit couvrir **tous** les cas → sinon `default` obligatoire.
  Avec un **`enum`**, si tous les constants sont couverts, `default` est facultatif.
- 🪤 On **ne mélange pas** les formes `->` et `:` dans le **même** switch → erreur de compilation.
- Une *instruction* switch en forme `->` est permise (sans valeur de retour) ; un `case ... -> throw ...;` aussi.

## 4. Boucles

| Boucle | Particularité |
|--------|---------------|
| `while (cond) { }` | teste **avant** ; peut ne jamais s'exécuter |
| `do { } while (cond);` | s'exécute **au moins une fois** ; ⚠️ `;` final |
| `for (init; cond; maj)` | les trois sections sont **optionnelles** ; `for (;;)` = boucle infinie |
| `for (T x : iterable)` | for-each ; pas d'accès à l'indice |

- Le `for` accepte plusieurs variables et plusieurs mises à jour : `for (int i=0, j=9; i<j; i++, j--)`.
- 🪤 La condition d'un `for`/`while` doit être `boolean`.

## 5. `break` / `continue` (et étiquettes)

- `break` quitte la boucle (ou le `switch`) **englobant le plus proche**.
- `continue` passe à l'**itération suivante**.
- 🪤 `continue` dans un `while` placé **avant** l'incrément → **boucle infinie** :
  ```java
  int i = 0;
  while (i < 3) {
      if (i == 1) continue; // saute i++ -> i reste 1 pour toujours
      i++;
  }
  ```
- **Étiquettes** : `label: for (...)` permet `break label;` / `continue label;` pour agir sur une **boucle externe**.

## 6. Code inatteignable

- 🪤 Une instruction **après un `break`/`return`/`throw` inconditionnel** dans le même bloc → erreur de compilation (*unreachable statement*).
- `while (false) { ... }` → corps inatteignable → **ne compile pas**. Mais `if (false) { ... }` compile (cas particulier toléré).

---

### 🪤 Récap des pièges
1. La condition d'un `if`/`while`/`for` **doit être `boolean`**.
2. `switch` n'accepte pas `long`/`float`/`double`/`boolean`.
3. Fall-through sans `break` dans le switch classique.
4. `default` peut être au milieu.
5. Expression switch : **exhaustive** (sinon `default`), **pas de mélange** `->`/`:`, `yield` (≠ `return`).
6. `continue` avant l'incrément d'un `while` → boucle infinie.
7. `while (false) {}` ne compile pas (code mort).
8. Sans accolades, une seule instruction est dans le `if`/la boucle.
