# Fiche 03 — Approche orientée objet

> Objectif d'examen : *Utilizing Java Object-Oriented Approach.* (Le domaine le plus important.)

## 1. Classes, constructeurs, `this` / `super`

- Si **aucun** constructeur n'est déclaré → constructeur **par défaut** sans argument fourni par le compilateur.
  🪤 Dès qu'on déclare **un** constructeur, le constructeur par défaut **disparaît**.
- `this(...)` appelle un autre constructeur **de la même classe** ; `super(...)` appelle celui du parent.
  - Doit être la **première** instruction du constructeur. On ne peut pas avoir `this()` **et** `super()` dans le même constructeur.
  - 🪤 Si le parent n'a **pas** de constructeur sans argument, l'enfant **doit** appeler `super(args)` explicitement.
- **Ordre d'initialisation** :
  1. blocs `static` (parent puis enfant), **une seule fois** au chargement de la classe ;
  2. à chaque `new` : `super(...)`, puis **initialiseurs d'instance + champs** (ordre textuel), puis corps du constructeur.

## 2. Encapsulation & immutabilité

- Encapsulation : champs `private` + accesseurs/mutateurs.
- Classe **immuable** : `final` (classe ou champs), champs `private final`, pas de mutateur, copies défensives des objets mutables.

## 3. Héritage & polymorphisme

- **Override** (redéfinition) : même signature ; liaison **dynamique** (selon l'objet réel). `@Override` recommandé.
  - La visibilité ne peut **pas** être réduite ; le type de retour peut être **covariant** ; les exceptions *checked* déclarées ne peuvent **pas** être élargies.
- **Overload** (surcharge) : même nom, **paramètres différents** ; liaison **statique** (selon les types déclarés).
- 🪤 **Champs** : pas de polymorphisme → **masquage** (hiding) selon le **type de la référence**, pas de l'objet.
- 🪤 **Méthodes `static`** : masquées, pas redéfinies → résolues selon le **type déclaré**.
- `final` sur une méthode interdit la redéfinition ; sur une classe interdit l'héritage.

### Résolution de surcharge (du plus prioritaire au moins prioritaire)
1. Correspondance exacte / **élargissement** (`int`→`long`→`double`)
2. **Autoboxing** (`int`→`Integer`)
3. **Varargs** (`int...`)
🪤 `f(int)` est choisi avant `f(Integer)` avant `f(int...)`.

## 4. Casting & `instanceof` (pattern matching)

- *Upcast* implicite ; *downcast* explicite, vérifié à l'exécution (`ClassCastException` si faux).
- **Pattern matching `instanceof`** (standard en 17) :
  ```java
  if (o instanceof String s && !s.isEmpty()) { /* s est un String ici */ }
  ```
  La variable liée (`s`) n'est visible que là où le test est **forcément** vrai.
- 🪤 Le *pattern matching pour `switch`* et les *record patterns* sont **preview** en Java 17 → **pas à l'examen**.

## 5. Interfaces

- Méthodes `abstract` (implicitement `public`), constantes `public static final`.
- **`default`** : implémentation par défaut héritée ; **`static`** : appelée via le nom de l'interface ;
  **`private`** (Java 9+) : factorisation interne.
- 🪤 **Conflit de `default` (diamant)** : si deux interfaces fournissent le même `default`, la classe **doit** redéfinir la méthode (et peut appeler `Interface.super.methode()`).
- Une classe peut implémenter **plusieurs** interfaces ; héritage **multiple de type** autorisé.

## 6. Classes abstraites

- `abstract class` : ne s'instancie pas ; peut avoir constructeurs, champs, méthodes concrètes et `abstract`.
- 🪤 Une méthode `abstract` ne peut pas être `private`, `final` ni `static`.

## 7. `enum`

- Liste de constantes ; peut avoir **constructeur (privé)**, champs, méthodes.
- 🪤 Le constructeur d'un enum est **implicitement `private`** (et ne peut pas être `public`).
- Méthodes utiles : `values()`, `valueOf(String)` (🪤 `IllegalArgumentException` si inconnu), `name()`, `ordinal()`.
- Peut déclarer une méthode `abstract` redéfinie par **chaque** constante (corps spécifique).
- Utilisable dans un `switch` (on écrit le nom de la constante **sans** préfixe).

## 8. `record` (Java 16+)

- `record Point(int x, int y) {}` génère : champs `private final`, **accesseurs** `x()`/`y()` (sans `get`), `equals`/`hashCode`/`toString`, constructeur canonique.
- **Implicitement `final`** ; ne peut **pas** `extends` (mais peut `implements`).
- **Constructeur compact** : `Point { if (x < 0) throw ...; }` — valide/normalise, **sans** réaffecter les champs (fait automatiquement).
- 🪤 Pas de champ d'instance supplémentaire en dehors des composants ; les champs `static` sont permis.

## 9. `sealed` (Java 17, finalisé)

- `sealed interface S permits A, B {}` : limite les sous-types autorisés.
- Chaque sous-type **doit** être `final`, `sealed` (avec son propre `permits`) ou **`non-sealed`** (rouvre l'héritage).
- Les types `permits` doivent être dans le **même module** (ou même package si sans module).

## 10. Classes imbriquées

| Type | Accès membres d'instance englobants | Instanciation |
|------|-------------------------------------|---------------|
| `static` nested | non | `new Outer.Nested()` |
| **inner** (non statique) | oui | `outer.new Inner()` |
| **locale** (dans une méthode) | oui (+ variables `final`/effectively final) | `new Locale()` dans la méthode |
| **anonyme** | oui | `new Type() { ... }` |

🪤 Une classe interne ne peut pas avoir de membre `static` (sauf constantes `static final`).

## 11. Lambdas & interfaces fonctionnelles

- **Interface fonctionnelle** = **une seule** méthode abstraite (`@FunctionalInterface` facultatif). Les `default`/`static` ne comptent pas.
- Standard (`java.util.function`) : `Supplier<T>` (`get`), `Consumer<T>` (`accept`), `Function<T,R>` (`apply`), `Predicate<T>` (`test`), `BiFunction`, `UnaryOperator`, `BinaryOperator`.
- 🪤 Une lambda capture des variables locales **`final` ou effectively final** uniquement.
- Référence de méthode : `String::isEmpty`, `System.out::println`, `Integer::parseInt`, `ArrayList::new`.

---

### 🪤 Récap des pièges
1. Déclarer un constructeur supprime le constructeur par défaut.
2. `super(...)` obligatoire si le parent n'a pas de constructeur vide.
3. Champs et méthodes `static` : **masqués**, résolus selon le **type de la référence**.
4. Surcharge : élargissement > autoboxing > varargs.
5. Constructeur d'`enum` toujours `private` ; `valueOf` inconnu → exception.
6. `record` : final, accesseurs sans `get`, constructeur compact sans réaffectation.
7. Sous-type d'un `sealed` : `final` / `sealed` / `non-sealed` obligatoire.
8. Switch pattern matching & record patterns = **preview en 17** (hors examen).
