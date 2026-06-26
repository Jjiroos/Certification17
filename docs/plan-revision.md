# Plan de révision — 8 semaines vers le 1Z0-829

Rythme cible : **niveau intermédiaire, examen dans 1 à 2 mois**.
Coche les cases au fur et à mesure (`- [ ]` → `- [x]`).

Pour chaque domaine, la routine est la même :
1. 📘 Lire la **fiche** (`docs/fiches/`)
2. 💻 Lancer et modifier les **exemples** (`src/main/java/cert/`)
3. ✅ Faire les **exercices** JUnit (`src/test/java/cert/`) → `mvn test`
4. 📝 Passer le **QCM** (`docs/qcm/`) et lire les corrigés

> 🃏 **En complément, à tout moment** : révise `docs/flashcards.md` (~110 cartes Q/R)
> par tranches de 5–10 min, idéalement sur ton téléphone. Parfait pour réactiver
> un domaine déjà vu pendant les temps morts.

---

## Semaine 1 — Types & contrôle de flux

**Objectif :** ne plus jamais se tromper sur une promotion numérique, un `switch` expression ou l'immutabilité d'un `String`.

### Domaine 1 — Types, texte, nombres, dates
- [ ] Lire `docs/fiches/01-types-texte-nombres-dates.md`
- [ ] Lancer les exemples `cert.d01types.*`
- [ ] Faire les exercices `D01TypesExercises`
- [ ] QCM `docs/qcm/01-types.md`

### Domaine 2 — Contrôle de flux
- [ ] Lire `docs/fiches/02-controle-de-flux.md`
- [ ] Lancer les exemples `cert.d02flow.*`
- [ ] Faire les exercices `D02FlowExercises`
- [ ] QCM `docs/qcm/02-controle-de-flux.md`

---

## Semaine 2 — POO (partie A : héritage & polymorphisme)

**Objectif :** maîtriser constructeurs/`this`/`super`, le polymorphisme, le casting et le pattern matching `instanceof`.

- [ ] Lire `docs/fiches/03-poo.md` (sections classes → polymorphisme)
- [ ] Lancer les exemples `cert.d03oop.*` (partie A)
- [ ] Faire les exercices `D03OopExercises` (partie A)
- [ ] QCM `docs/qcm/03-poo.md` (questions 1 à mi-parcours)

---

## Semaine 3 — POO (partie B : types modernes)

**Objectif :** interfaces (default/static/private), classes abstraites, **enums, records, sealed**, classes imbriquées, lambdas & interfaces fonctionnelles.

- [ ] Lire `docs/fiches/03-poo.md` (sections interfaces → lambdas)
- [ ] Lancer les exemples `cert.d03oop.*` (partie B)
- [ ] Faire les exercices `D03OopExercises` (partie B)
- [ ] Terminer le QCM `docs/qcm/03-poo.md`

---

## Semaine 4 — Exceptions & collections

### Domaine 4 — Exceptions
- [ ] Lire `docs/fiches/04-exceptions.md`
- [ ] Exemples `cert.d04exceptions.*` + exercices `D04ExceptionsExercises`
- [ ] QCM `docs/qcm/04-exceptions.md`

### Domaine 5 — Tableaux & collections
- [ ] Lire `docs/fiches/05-tableaux-collections.md`
- [ ] Exemples `cert.d05collections.*` + exercices `D05CollectionsExercises`
- [ ] QCM `docs/qcm/05-tableaux-collections.md`

---

## Semaine 5 — Streams & lambdas (domaine le plus dense)

**Objectif :** pipelines, `Optional`, `Collectors` (`groupingBy`/`partitioningBy`/`joining`), streams primitifs, `reduce`, `flatMap`.

- [ ] Lire `docs/fiches/06-streams-lambdas.md`
- [ ] Lancer les exemples `cert.d06streams.*`
- [ ] Faire les exercices `D06StreamsExercises`
- [ ] QCM `docs/qcm/06-streams-lambdas.md`

> 💡 Prévois plus de temps ici : c'est le domaine le plus représenté à l'examen.

---

## Semaine 6 — Concurrence

**Objectif :** `ExecutorService`, `Callable`/`Future`, `CompletableFuture`, collections concurrentes, atomics, `parallelStream`, problèmes classiques (data race, deadlock).

- [ ] Lire `docs/fiches/08-concurrence.md`
- [ ] Lancer les exemples `cert.d08concurrency.*`
- [ ] Faire les exercices `D08ConcurrencyExercises`
- [ ] QCM `docs/qcm/08-concurrence.md`

---

## Semaine 7 — I/O & Modules

### Domaine 9 — I/O & NIO.2
- [ ] Lire `docs/fiches/09-io-nio2.md`
- [ ] Exemples `cert.d09io.*` + exercices `D09IoExercises`
- [ ] QCM `docs/qcm/09-io-nio2.md`

### Domaine 7 — Modules (JPMS)
- [ ] Lire `docs/fiches/07-modules-jpms.md`
- [ ] Exemples `cert.d07modules.*` + exercices `D07ModulesExercises`
- [ ] QCM `docs/qcm/07-modules-jpms.md`

---

## Semaine 8 — JDBC, localisation & révision finale

### Domaine 10 — JDBC
- [ ] Lire `docs/fiches/10-jdbc.md`
- [ ] Exemples `cert.d10jdbc.*` + exercices `D10JdbcExercises`
- [ ] QCM `docs/qcm/10-jdbc.md`

### Domaine 11 — Localisation
- [ ] Lire `docs/fiches/11-localisation.md`
- [ ] Exemples `cert.d11i18n.*` + exercices `D11I18nExercises`
- [ ] QCM `docs/qcm/11-localisation.md`

### Révision finale
- [ ] Relire toutes les sections **🪤 Pièges** des fiches
- [ ] Passer les flashcards `docs/flashcards.md` (insister sur les cartes hésitantes)
- [ ] Refaire les QCM où j'ai eu < 80 %
- [ ] **Examen blanc** `docs/qcm/examen-blanc.md` (50 questions, 90 min, chrono)
- [ ] Réviser les erreurs de l'examen blanc

---

## Suivi des scores de QCM

| Domaine | 1er essai | 2e essai | Objectif |
|---------|-----------|----------|----------|
| 01 Types | ___ / __ | ___ / __ | ≥ 80 % |
| 02 Flux  | ___ / __ | ___ / __ | ≥ 80 % |
| 03 POO   | ___ / __ | ___ / __ | ≥ 80 % |
| 04 Exceptions | ___ / __ | ___ / __ | ≥ 80 % |
| 05 Collections | ___ / __ | ___ / __ | ≥ 80 % |
| 06 Streams | ___ / __ | ___ / __ | ≥ 80 % |
| 07 Modules | ___ / __ | ___ / __ | ≥ 80 % |
| 08 Concurrence | ___ / __ | ___ / __ | ≥ 80 % |
| 09 I/O | ___ / __ | ___ / __ | ≥ 80 % |
| 10 JDBC | ___ / __ | ___ / __ | ≥ 80 % |
| 11 Localisation | ___ / __ | ___ / __ | ≥ 80 % |
| **Examen blanc** | ___ / 50 | ___ / 50 | ≥ 34/50 |

> Vise **≥ 80 %** aux QCM par domaine avant le jour J (marge au-dessus des 68 % réels).
