# Fiche 11 — Localisation (i18n)

> Objectif d'examen : *Implementing Localization.*

## 1. `Locale`

- Représente une **langue** (+ pays optionnel) : `fr`, `fr_FR`, `en_US`.
- Création (Java 17) :
  - `Locale.FRANCE`, `Locale.US`, `Locale.ENGLISH`… (constantes)
  - **`new Locale("fr")`** ou **`new Locale("fr", "FR")`** (constructeur — `Locale.of(...)` n'existe qu'à partir de Java 19).
  - `new Locale.Builder().setLanguage("fr").setRegion("FR").build()`.
- 🪤 Convention : **langue en minuscules**, **pays en majuscules** (`fr_FR`).
- `getLanguage()`, `getCountry()`, `getDisplayLanguage(locale)`.
- `Locale.getDefault()` / `Locale.setDefault(locale)` : locale par défaut de la JVM.

## 2. `ResourceBundle` (textes traduits)

- Fichiers `.properties` (clé=valeur) sur le classpath : `messages.properties` (base/défaut),
  `messages_fr.properties`, `messages_fr_FR.properties`…
- Chargement : `ResourceBundle.getBundle("messages", locale)` puis `bundle.getString("cle")`.
- 🪤 **Ordre de résolution** (du plus spécifique au plus général) :
  `messages_<langue>_<pays>` → `messages_<langue>` → **bundle de la locale par DÉFAUT** → `messages` (base).
  → Une locale inconnue retombe d'abord sur la **locale par défaut de la JVM**, puis sur le fichier de base.
- 🪤 Clé absente → `MissingResourceException`.
- 🪤 Les `.properties` sont lus en **ISO-8859-1** par défaut (caractères non-latins échappés en `\uXXXX`).

## 3. Formatage des nombres — `NumberFormat`

- `NumberFormat.getInstance(locale)` (général), `getCurrencyInstance(locale)` (devise),
  `getPercentInstance(locale)` (🪤 **multiplie par 100** : `0.25` → `25%`), `getIntegerInstance`.
- `format(nombre)` → `String` ; `parse(texte)` → `Number` (🪤 lève `ParseException`).
- 🪤 Les **séparateurs dépendent de la locale** : `1,234.5` (US) vs `1.234,5` (DE) vs `1 234,5` (FR, espace insécable).

## 4. `DecimalFormat` (motifs)

| Symbole | Sens |
|---------|------|
| `0` | chiffre **obligatoire** (zéro de remplissage) |
| `#` | chiffre **optionnel** (rien si absent) |
| `.` | séparateur décimal |
| `,` | séparateur de groupes |
| `%` | pourcentage (×100) |

- `new DecimalFormat("#,##0.00")` → `1,234.50`. 🪤 **Arrondi par défaut = HALF_EVEN**.
- 🪤 `new DecimalFormat(pattern)` utilise les symboles de la **locale par défaut** ; passer `DecimalFormatSymbols.getInstance(locale)` pour fixer la locale.

## 5. Dates localisées — `DateTimeFormatter`

- `DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM).withLocale(locale)`.
- `DateTimeFormatter.ofPattern("dd MMMM yyyy", locale)` (noms de mois traduits).
- `date.format(formatter)` / `LocalDate.parse(texte, formatter)`.

## 6. `MessageFormat`

- Paramètres positionnels : `MessageFormat.format("{0} sur {1}", 3, 10)` → `3 sur 10`.
- Utile avec les bundles : `MessageFormat.format(bundle.getString("cle"), args...)`.

---

### 🪤 Récap des pièges
1. En Java 17 : `new Locale("fr","FR")` (pas `Locale.of`, qui est en 19+).
2. Résolution d'un bundle : spécifique → langue → **locale par défaut** → base.
3. Clé absente → `MissingResourceException`.
4. `getPercentInstance` multiplie par 100.
5. Séparateurs nombre/décimal selon la locale.
6. `0` = chiffre obligatoire, `#` = optionnel ; arrondi HALF_EVEN.
7. `new DecimalFormat(pattern)` dépend de la locale par défaut.
