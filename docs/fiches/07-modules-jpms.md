# Fiche 07 — Java Platform Module System (JPMS)

> Objectif d'examen : *Packaging and deploying Java code and use the Java Platform Module System.*

## 1. `module-info.java`

À la **racine** des sources d'un module. Exemple commenté :

```java
module com.exemple.app {           // nom du module (souvent = package racine)
    requires com.exemple.core;      // dépend d'un autre module
    requires transitive java.sql;   // les modules qui requièrent app voient AUSSI java.sql
    requires static lombok;         // requis à la COMPILATION seulement (optionnel à l'exécution)

    exports com.exemple.app.api;                 // package visible par TOUS
    exports com.exemple.app.internal to com.exemple.test; // export QUALIFIÉ (ciblé)

    opens com.exemple.app.model;     // ouvre à la RÉFLEXION (runtime) — ex. frameworks
    opens com.exemple.app.dto to com.fasterxml.jackson.databind; // open qualifié

    uses com.exemple.spi.Service;                 // consomme un service (ServiceLoader)
    provides com.exemple.spi.Service              // fournit une implémentation
        with com.exemple.app.ServiceImpl;
}
```

## 2. Les directives

| Directive | Rôle |
|-----------|------|
| `requires M` | dépendance vers le module M (compilation + exécution) |
| `requires transitive M` | + **réexporte** la dépendance (implied readability) |
| `requires static M` | dépendance **optionnelle** à l'exécution |
| `exports P` | rend le package P **accessible** (API publique) à la compilation/exécution |
| `exports P to M1, M2` | export **qualifié** : seulement vers M1, M2 |
| `opens P` | ouvre P à la **réflexion profonde** (runtime), sans le rendre accessible à la compilation |
| `opens P to M` | open qualifié |
| `uses S` | déclare consommer le **service** S via `ServiceLoader` |
| `provides S with Impl` | déclare fournir une **implémentation** de S |

- 🪤 `exports` = accès à la **compilation** (API). `opens` = accès **réflexif au runtime** (setAccessible). Ce ne sont **pas** la même chose.
- 🪤 Un module **`open module`** ouvre **tous** ses packages à la réflexion.
- 🪤 Sans `exports`, un package est **interne** (invisible des autres modules), même `public`.

## 3. Types de modules

- **Module nommé (named)** : a un `module-info.java`.
- **Module automatique (automatic)** : un JAR **sans** module-info placé sur le *module-path* → nom dérivé du nom de fichier ; **exporte tout** et **lit tous** les autres.
- **Module sans nom (unnamed)** : tout ce qui est sur le **classpath** ; lit tous les modules, mais un module nommé **ne peut pas** dépendre de l'unnamed.

`java.base` est **toujours** implicitement requis (contient `java.lang`, `java.util`, `java.io`…).

## 4. Outils en ligne de commande

| Commande | Rôle |
|----------|------|
| `javac --module-path mp -d out $(find src -name '*.java')` | compile un module |
| `java --module-path mp --module com.app/com.app.Main` | exécute (`-p` / `-m` en raccourci) |
| `jar --create --file app.jar --main-class ... -C out .` | crée un JAR modulaire |
| `jdeps` | analyse les **dépendances** (sert à la migration) |
| `jlink` | crée une **image d'exécution** minimale (runtime sur mesure) |
| `jmod` | crée/inspecte des fichiers **.jmod** (modules avec ressources natives) |
| `java --describe-module com.app` | affiche le descripteur |
| `java --list-modules` | liste les modules observables |

## 5. Migration (bottom-up vs top-down)

- **Bottom-up** : on modularise d'abord les dépendances (du bas vers le haut). Nécessite que toutes les dépendances soient modulaires.
- **Top-down** : on modularise l'application en s'appuyant sur des **modules automatiques** pour les libs pas encore modulaires.

## 6. API réflexive (`java.lang.Module`)

- `obj.getClass().getModule()` → l'objet `Module`.
- `module.getName()` → nom (ou **`null`** pour l'unnamed), `module.isNamed()`.
- Classes JDK : `String.class.getModule().getName()` → `"java.base"`.

---

### 🪤 Récap des pièges
1. `exports` (accès compilation/API) ≠ `opens` (accès réflexion runtime).
2. `requires transitive` propage la lisibilité aux dépendants.
3. `requires static` = optionnel à l'exécution.
4. Un module **automatique** (JAR sans module-info sur le module-path) exporte tout.
5. Le **classpath** = module sans nom (`getName()` → `null`).
6. `java.base` est toujours requis implicitement.
7. `provides ... with ...` + `uses ...` pour les services (`ServiceLoader`).
