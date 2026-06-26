# QCM 07 — Modules (JPMS)

> Réponds **sans lancer le code**. Corrigés en bas.
> 🟢 N1 · 🟡 N2 · 🔴 N3 · ⚠️ plusieurs réponses possibles sur certaines questions.

---

## 🟢 Niveau 1 — Fondamentaux

**Q1.** Où se place le descripteur d'un module ?
- A) `META-INF/module.xml`
- B) `module-info.java` à la racine des sources du module
- C) `package-info.java`
- D) Dans le `pom.xml`

**Q2.** Quel module est **toujours** requis implicitement ?
- A) `java.lang`  B) `java.base`  C) `java.se`  D) `java.util`

**Q3.** Quelle directive rend un package accessible (API) aux autres modules ?
- A) `opens`  B) `requires`  C) `exports`  D) `provides`

**Q4.** (Choose two) Vrai à propos du **classpath** (module sans nom) ?
- A) Son nom (`getName()`) est `null`
- B) Un module nommé peut le `requires`
- C) Il lit tous les autres modules
- D) Il doit déclarer un `module-info.java`

**Q5.** Quel outil crée une **image d'exécution minimale** ?
- A) `jdeps`  B) `jar`  C) `jlink`  D) `javac`

## 🟡 Niveau 2 — Intermédiaire

**Q6.** Différence entre `exports P` et `opens P` ?
- A) Aucune
- B) `exports` = accès à la compilation (API) ; `opens` = accès réflexif au runtime
- C) `opens` = API publique ; `exports` = réflexion
- D) `opens` ne fonctionne que pour les tests

**Q7.** `requires transitive java.sql;` signifie…
- A) java.sql est optionnel à l'exécution
- B) tout module qui requiert le mien lit **aussi** java.sql
- C) java.sql n'est requis qu'à la compilation
- D) java.sql est ouvert à la réflexion

**Q8.** `requires static M;` signifie que M est…
- A) requis seulement à l'**exécution**
- B) requis à la compilation, **optionnel** à l'exécution
- C) un module statique immuable
- D) exporté transitivement

**Q9.** Un JAR **sans** `module-info` placé sur le **module-path** est…
- A) ignoré
- B) un module automatique (nom dérivé du fichier, exporte tout)
- C) le module sans nom
- D) une erreur

**Q10.** (Choose two) Quelles directives concernent les **services** (`ServiceLoader`) ?
- A) `uses S`  B) `exports S`  C) `provides S with Impl`  D) `opens S`

**Q11.** `exports com.app.internal to com.app.test;` est…
- A) un export qualifié (visible seulement par com.app.test)
- B) une erreur de syntaxe
- C) équivalent à `exports com.app.internal`
- D) un `opens`

## 🔴 Niveau 3 — Pièges & format examen

**Q12.** Un package `public` non `exports` est-il visible depuis un autre module ?
- A) Oui, `public` suffit
- B) Non, il faut `exports`
- C) Oui à l'exécution seulement
- D) Oui via réflexion automatiquement

**Q13.** Un framework utilise la réflexion (`setAccessible`) sur vos classes `model`. Quelle directive faut-il ?
- A) `exports com.app.model`
- B) `opens com.app.model`
- C) `requires com.app.model`
- D) `uses com.app.model`

**Q14.** Quel outil sert principalement à **analyser les dépendances** lors d'une migration ?
- A) `jlink`  B) `jmod`  C) `jdeps`  D) `jar`

**Q15.** Un module nommé `com.app` **peut-il** dépendre de code situé sur le classpath (module sans nom) ?
- A) Oui avec `requires unnamed`
- B) Non, un module nommé ne peut pas lire le module sans nom
- C) Oui automatiquement
- D) Oui si `opens`

**Q16.**
```java
module com.app {
    requires com.app;
}
```
- A) Compile
- B) Erreur : un module ne peut pas se requérir lui-même (cycle)
- C) Avertissement seulement
- D) Crée un module automatique

**Q17.** `open module com.app { ... }` signifie…
- A) Tous les packages sont **exportés**
- B) Tous les packages sont **ouverts à la réflexion**
- C) Le module est public
- D) Le module n'a pas de dépendances

---
---

## Corrigés

**Q1 → B.** `module-info.java` à la racine des sources du module.

**Q2 → B (java.base).** Toujours requis implicitement ; contient `java.lang`, `java.util`, `java.io`…

**Q3 → C (`exports`).** `exports` rend l'API d'un package accessible aux autres modules.

**Q4 → A et C.** Le module sans nom a un nom `null` et lit tous les modules. ⚠️ Un module **nommé ne peut pas** `requires` le module sans nom (B faux) ; pas de module-info (D faux).

**Q5 → C (`jlink`).** Crée un runtime sur mesure contenant uniquement les modules nécessaires.

**Q6 → B.** `exports` = accessibilité **compilation/API** ; `opens` = accès **réflexif au runtime** (deep reflection).

**Q7 → B.** `requires transitive` propage la lisibilité (implied readability) aux modules dépendants.

**Q8 → B.** `requires static` : présent à la compilation, **optionnel** à l'exécution.

**Q9 → B.** JAR sans module-info **sur le module-path** = module **automatique** (nom dérivé du fichier, exporte tout, lit tout).

**Q10 → A et C.** `uses` (consommer) et `provides ... with ...` (fournir) concernent les services. `exports`/`opens` non.

**Q11 → A.** Export **qualifié** : le package n'est visible que par les modules listés après `to`.

**Q12 → B.** Sans `exports`, un package reste **interne** au module, même si ses classes sont `public`.

**Q13 → B (`opens`).** La réflexion profonde au runtime nécessite `opens` (et non `exports`).

**Q14 → C (`jdeps`).** Analyse les dépendances (utile pour préparer la modularisation).

**Q15 → B.** Un module nommé **ne peut pas** lire le module sans nom : il faut transformer le code en module (nommé ou automatique).

**Q16 → B.** Un module ne peut pas se `requires` lui-même.

**Q17 → B.** `open module` ouvre **tous** ses packages à la réflexion (équivaut à `opens` sur chacun).
