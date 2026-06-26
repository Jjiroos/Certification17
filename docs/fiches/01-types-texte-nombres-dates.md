# Fiche 01 — Types, texte, nombres, dates & booléens

> Objectif d'examen : *Handling date, time, text, numeric and boolean values.*

## 1. Types primitifs

| Type | Bits | Plage / valeurs | Littéral |
|------|------|-----------------|----------|
| `boolean` | — | `true` / `false` | `true` |
| `byte` | 8 | −128 … 127 | `(byte) 10` |
| `short` | 16 | −32 768 … 32 767 | `(short) 10` |
| `int` | 32 | ≈ ±2,1 milliards | `10`, `0xFF`, `0b101`, `010` |
| `long` | 64 | très grand | `10L` |
| `float` | 32 | flottant | `1.5f` |
| `double` | 64 | flottant (défaut) | `1.5`, `1.5d` |
| `char` | 16 | 0 … 65 535 (Unicode) | `'A'`, `'A'` |

- Un littéral décimal entier est un **`int`** par défaut → `long gros = 10000000000;` **ne compile pas** sans `L`.
- Un littéral à virgule est un **`double`** par défaut → `float f = 1.5;` **ne compile pas** sans `f`.
- Préfixes : `0x` hexa, `0b` binaire, **`0` = octal** (🪤 `010` vaut **8**, pas 10).
- `_` autorisé **entre** des chiffres : `1_000_000`. Interdit en début/fin ou collé à `.`/préfixe.

## 2. Wrappers, autoboxing & cache

- Chaque primitive a sa classe : `Integer`, `Double`, `Boolean`, `Character`, `Long`…
- **Autoboxing/unboxing** automatique : `Integer i = 5;` / `int j = i;`.
- 🪤 **Unboxing d'un wrapper `null` → `NullPointerException`** : `Integer x = null; int y = x;`.
- 🪤 **Cache des `Integer`** pour les valeurs **−128 à 127** : deux `Integer` autoboxés dans cette plage sont le **même objet** (`==` vaut `true`). Hors plage, `==` vaut `false`. **Toujours comparer avec `.equals()`**.
- `Integer.parseInt("42")` → `int` ; `Integer.valueOf("42")` → `Integer`. Texte invalide → `NumberFormatException`.

## 3. Promotion numérique & casting

- Dans une expression, `byte`/`short`/`char` sont **promus en `int`** : `byte a, b; a + b` est un `int`.
  → `byte c = a + b;` **ne compile pas** (résultat `int`, pas de réduction implicite).
- Réduction (`int`→`byte`, `double`→`int`…) : **cast explicite obligatoire**, peut **perdre des données**.
- 🪤 **Affectation composée** (`+=`, `*=`, …) **inclut un cast implicite** :
  `byte b = 10; b += 5;` compile (équivaut à `b = (byte)(b + 5)`).
- 🪤 **Débordement silencieux** : `Integer.MAX_VALUE + 1` → `Integer.MIN_VALUE` (pas d'exception).
- `char` participe à l'arithmétique comme un `int` : `'a' + 1` vaut **`98` (int)**, pas `'b'`.

## 4. Opérateurs (pièges fréquents)

- **Division entière tronquée** : `7 / 2` → `3`. Un opérande flottant → résultat flottant : `7.0 / 2` → `3.5`.
- 🪤 **Division entière par zéro** → `ArithmeticException`. **Flottante par zéro** → `Infinity` ou `NaN` (jamais d'exception).
- `i++` renvoie l'**ancienne** valeur, `++i` la **nouvelle**.
- `&&` / `||` **court-circuitent** (l'opérande droit peut ne pas être évalué) ; `&` / `|` évaluent **toujours** les deux.
- `==` sur objets compare les **références** ; utiliser `.equals()` pour le contenu.

## 5. `var` (inférence de type local)

Autorisé **uniquement pour les variables locales** (y compris boucles `for`, `for-each`, et `try-with-resources`).
🪤 **Interdit** (ne compile pas) :
- sans initialiseur : `var x;`
- avec `null` seul : `var x = null;`
- pour un **champ**, un **paramètre**, un **type de retour**
- déclarations multiples : `var a = 1, b = 2;`
- initialiseur de tableau sans type : `var t = {1, 2};`

`var` **n'est pas un mot-clé réservé** : on peut nommer une variable `var`, mais pas une classe.

## 6. `String` (immuable)

- 🪤 **Immuable** : toute « modification » renvoie une **nouvelle** chaîne. `s.toUpperCase();` seul ne change rien → il faut **réaffecter**.
- **Pool de chaînes** : les littéraux identiques partagent le même objet (`==` vaut `true`).
  - `new String("x")` crée un objet **hors pool** (`==` faux, `.equals()` vrai). `.intern()` ramène au pool.
  - 🪤 La concaténation de **constantes de compilation** est repliée et **interne** : `"x" + "y" == "xy"` → `true`.
- Indices à partir de **0**. Méthodes clés :
  `length()`, `charAt(i)`, `indexOf(s[, from])`, `substring(deb[, fin])` (**fin exclue**),
  `replace`, `strip()`/`trim()`, `contains`, `startsWith`/`endsWith`, `isBlank()`, `repeat(n)`, `String.format`.
- 🪤 `substring`/`charAt` hors limites → `StringIndexOutOfBoundsException`.
- **Bloc de texte** (`"""`) : multi-lignes ; l'indentation accidentelle est supprimée selon la ligne la moins indentée et le délimiteur de fin.

## 7. `StringBuilder` (mutable)

- **Mutable** : les méthodes modifient l'objet **et** renvoient `this` → **chaînage** possible.
- `append`, `insert(pos, x)`, `delete(deb, fin)` (**fin exclue**), `deleteCharAt`, `replace(deb, fin, s)`, `reverse`, `length()`, `capacity()`.
- 🪤 `StringBuilder` **ne redéfinit pas `equals()`** → `equals` compare les références. Comparer via `.toString().equals(...)`.

## 8. `Math` (utilitaire statique)

- `Math.round(double)` → **`long`** ; `Math.round(float)` → `int`. Arrondit au plus proche (`.5` vers le haut).
- `Math.ceil` / `Math.floor` → **`double`**. `Math.abs`, `Math.max/min`, `Math.pow` (→ `double`), `Math.sqrt`, `Math.random()` (∈ [0,1[).

## 9. API Date-Time (`java.time`, immuable)

| Classe | Contient | Exemple `toString` |
|--------|----------|--------------------|
| `LocalDate` | date | `2026-01-31` |
| `LocalTime` | heure | `14:30` |
| `LocalDateTime` | date + heure | `2026-01-31T14:30` |
| `ZonedDateTime` | date + heure + fuseau | `…+01:00[Europe/Paris]` |
| `Instant` | point sur la timeline (UTC) | `2026-…Z` |
| `Period` | années/mois/jours | `P1Y2M3D` |
| `Duration` | heures/min/sec/nanos | `PT1H30M` |

- 🪤 **Mois de 1 à 12** (et `Month` enum), **pas** 0–11 comme l'ancien `Calendar`.
- 🪤 **Immuable** : `date.plusDays(1);` seul est ignoré → réaffecter.
- `plusMonths`/`plusYears` **ajustent le jour** si invalide : `31 janv. + 1 mois → 28 (ou 29) févr.`
- **`Period`** s'utilise avec `LocalDate` (jours/mois/ans) ; **`Duration`** avec le temps (`LocalTime`/`Instant`).
  🪤 Ajouter une `Duration` à une `LocalDate` → exception (pas d'unité temps).
- `Period.ofWeeks(2)` → **`P14D`** (converti en jours), pas `P2W`.
- Différences : `ChronoUnit.DAYS.between(d1, d2)`, `Duration.between(t1, t2)`.
- Parsing/format : `LocalDate.parse("2026-12-25")` (ISO par défaut) ; `DateTimeFormatter.ofPattern("dd/MM/yyyy")`.
  Mauvais format → `DateTimeParseException`.

---

### 🪤 Récap des pièges les plus testés
1. `010` (octal) = 8.
2. `Integer` cache −128..127 → `==` parfois `true`, parfois `false`.
3. Unboxing de `null` → NPE.
4. `byte c = a + b;` ne compile pas, mais `b += 5;` oui.
5. `1 + 2 + "x"` = `"3x"` mais `"x" + 1 + 2` = `"x12"`.
6. `String` et dates **immuables** → réaffecter sinon « modification » perdue.
7. Division entière `/0` → exception ; flottante `/0` → `Infinity`/`NaN`.
8. `Math.round(double)` renvoie un `long`.
9. `var x;` / `var x = null;` ne compilent pas.
