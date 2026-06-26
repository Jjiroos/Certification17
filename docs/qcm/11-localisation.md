# QCM 11 — Localisation

> Réponds **sans lancer le code**. Corrigés en bas.
> 🟢 N1 · 🟡 N2 · 🔴 N3 · ⚠️ plusieurs réponses possibles sur certaines questions.

---

## 🟢 Niveau 1 — Fondamentaux

**Q1.** En Java 17, comment créer `Locale("fr", "FR")` ?
- A) `Locale.of("fr", "FR")`
- B) `new Locale("fr", "FR")`
- C) `Locale.create("fr_FR")`
- D) `Locale.FR`

**Q2.**
```java
System.out.println(Locale.FRANCE.getLanguage());
```
- A) FR  B) fr  C) French  D) fr_FR

**Q3.** Quel fichier sert de **base** pour `ResourceBundle.getBundle("messages", ...)` ?
- A) `messages_default.properties`
- B) `messages.properties`
- C) `messages_base.properties`
- D) `messages.xml`

**Q4.**
```java
System.out.println(NumberFormat.getPercentInstance(Locale.US).format(0.25));
```
- A) 0.25%  B) 25%  C) 0,25  D) 25.0

**Q5.** Que lève `bundle.getString("cle_absente")` ?
- A) `null`  B) `""`  C) `MissingResourceException`  D) `IllegalArgumentException`

## 🟡 Niveau 2 — Intermédiaire

**Q6.**
```java
System.out.println(NumberFormat.getInstance(Locale.GERMANY).format(1234.5));
```
- A) 1,234.5  B) 1.234,5  C) 1234.5  D) 1 234,5

**Q7.** Dans `new DecimalFormat("00.0#")`, que signifie `0` vs `#` ?
- A) Identiques
- B) `0` = chiffre obligatoire, `#` = chiffre optionnel
- C) `#` = obligatoire, `0` = optionnel
- D) `0` = zéro littéral

**Q8.**
```java
System.out.println(MessageFormat.format("{0} et {1}", "a", "b"));
```
- A) {0} et {1}  B) a et b  C) a, b  D) Exception

**Q9.** Ordre de résolution d'un bundle pour `Locale("fr","FR")` (du plus prioritaire) ?
- A) messages → messages_fr → messages_fr_FR
- B) messages_fr_FR → messages_fr → (locale défaut) → messages
- C) messages_fr_FR uniquement
- D) messages uniquement

**Q10.** (Choose two) Vrai à propos de `NumberFormat.parse(String)` ?
- A) Renvoie un `Number`
- B) Peut lever `ParseException`
- C) Renvoie toujours un `int`
- D) Ne dépend pas de la locale

## 🔴 Niveau 3 — Pièges & format examen

**Q11.** Une locale **inconnue** sans fichier dédié retombe sur…
- A) une exception immédiate
- B) le bundle de la **locale par défaut** de la JVM, puis le fichier de base
- C) `null`
- D) le premier fichier trouvé alphabétiquement

**Q12.**
```java
DecimalFormat df = new DecimalFormat("#.##");
System.out.println(df.format(5.0));
```
- A) 5.00  B) 5  C) 5.0  D) 05

**Q13.** Les fichiers `.properties` sont lus par défaut avec l'encodage…
- A) UTF-8  B) UTF-16  C) ISO-8859-1  D) ASCII strict

**Q14.**
```java
System.out.println(Locale.ENGLISH.getCountry().isEmpty());
```
- A) true  B) false  C) Exception  D) "US"

**Q15.**
```java
DecimalFormat df = new DecimalFormat("000.0",
        DecimalFormatSymbols.getInstance(Locale.US));
System.out.println(df.format(7.25));
```
- A) 007.3  B) 007.2  C) 7.25  D) 007.25

---
---

## Corrigés

**Q1 → B.** En Java 17, on utilise le **constructeur** `new Locale(...)`. `Locale.of(...)` n'arrive qu'en Java 19.

**Q2 → B (fr).** `getLanguage()` renvoie le code langue en minuscules.

**Q3 → B (`messages.properties`).** Fichier de base (sans suffixe de locale).

**Q4 → B (25%).** `getPercentInstance` **multiplie par 100**.

**Q5 → C (`MissingResourceException`).** Clé absente du bundle.

**Q6 → B (1.234,5).** En Allemagne : `.` groupe les milliers, `,` est le séparateur décimal.

**Q7 → B.** `0` impose un chiffre (zéro de remplissage) ; `#` n'affiche le chiffre que s'il existe.

**Q8 → B (a et b).** `MessageFormat` substitue `{0}`→"a", `{1}`→"b".

**Q9 → B.** Du plus spécifique au plus général : `messages_fr_FR` → `messages_fr` → bundle de la **locale par défaut** → `messages` (base).

**Q10 → A et B.** `parse` renvoie un `Number` et peut lever `ParseException`. Le résultat dépend de la locale.

**Q11 → B.** La résolution retombe d'abord sur la **locale par défaut** de la JVM, puis sur le fichier de base.

**Q12 → B (5).** Avec `#.##`, les décimales optionnelles nulles ne sont **pas** affichées → `5`.

**Q13 → C (ISO-8859-1).** Encodage historique des `.properties` (caractères non-latins échappés en `\uXXXX`).

**Q14 → A (true).** `Locale.ENGLISH` a une langue (`en`) mais **pas** de pays → `getCountry()` est vide.

**Q15 → B (007.2).** Arrondi par défaut **HALF_EVEN** : 7.25 → 7.2 (2 est pair).
