# Préparation à la certification Oracle Java SE 17 Developer (1Z0-829)

Environnement de révision complet pour l'examen **Oracle Certified Professional: Java SE 17 Developer**.

## L'examen en bref

| | |
|---|---|
| **Code** | 1Z0-829 |
| **Titre** | Java SE 17 Developer |
| **Questions** | 50 (QCM) |
| **Durée** | 90 minutes |
| **Score de passage** | 68 % |
| **Format** | Beaucoup de questions « quelle est la sortie ? » et « est-ce que ça compile ? » |

> ⚠️ L'examen teste la **lecture fine de code** : compilation, exceptions à l'exécution, valeurs exactes. Entraîne-toi à *tracer le code mentalement*, pas seulement à reconnaître des concepts.

### Conditions réelles (à reproduire en t'entraînant)
- **Aucun compilateur, aucun IDE, aucune note** — closed-book, sur ordinateur (Pearson VUE).
- Tu lis du **code statique** et tu choisis parmi des réponses. Questions à **une ou plusieurs** bonnes réponses (« Choose two »).
- Options pièges classiques : *Compilation fails* / *An exception is thrown at runtime* / *The code produces no output*.
- 👉 **Fais les exercices et QCM en PRÉDISANT d'abord, sans lancer le code.** Tu ne lances `mvn test` que pour **te corriger**. C'est ainsi qu'on s'entraîne aux conditions réelles.

### Niveaux de difficulté
Les QCM et exercices sont étiquetés :
- 🟢 **N1 — Fondamentaux** : vérifier que la base est acquise.
- 🟡 **N2 — Intermédiaire** : combinaisons de notions, cas réalistes.
- 🔴 **N3 — Pièges & format examen** : snippets complexes, options trompeuses, comme le jour J.

## Comment ce dépôt est organisé

```
docs/
├── plan-revision.md     ← ⭐ COMMENCE ICI : roadmap 8 semaines + suivi de progression
├── flashcards.md        ← ~110 cartes Q/R repliables (révision mobile, 5–10 min)
├── fiches/              ← fiches de cours par domaine (concepts + pièges)
└── qcm/                 ← QCM type examen (questions + corrigés expliqués en bas de fichier)

src/main/java/cert/      ← exemples de code commentés, exécutables (une classe = un main())
src/test/java/cert/      ← exercices « à compléter » validés par tests JUnit
```

Chaque domaine d'examen (`d01types`, `d02flow`, …) possède ses 4 supports :
**fiche** (`docs/fiches/`) + **exemples** (`src/main`) + **exercices** (`src/test`) + **QCM** (`docs/qcm/`).

## Pré-requis

- JDK 17 (`java -version` doit afficher 17.x)
- Maven (`mvn -version`) — pour lancer les tests/exercices. Optionnel pour les exemples.

## Comment l'utiliser

### 1. Lire la fiche du domaine
Ouvre `docs/fiches/NN-xxx.md`, repère les concepts et surtout les **🪤 Pièges**.

### 2. Lancer et trafiquer les exemples
Chaque classe d'exemple a une méthode `main()`. La sortie attendue est écrite en commentaire.
Le mieux : modifie une ligne, prédis la sortie, relance, vérifie.

```bash
# Via Maven (depuis la racine du projet)
mvn -q compile exec:java -Dexec.mainClass=cert.d01types.StringVsStringBuilderDemo

# Ou compile une fois puis lance avec java
mvn -q compile
java -cp target/classes cert.d01types.StringVsStringBuilderDemo
```

Dans IntelliJ : bouton ▶ à gauche de la méthode `main`.

### 3. Faire les exercices (auto-corrigés)
Les fichiers sous `src/test/java/cert/` contiennent des méthodes **à compléter**
(`// TODO`). Tant que tu n'as pas écrit la bonne réponse, le test échoue : c'est voulu.

```bash
mvn -q test                              # lance tous les tests
mvn -q test -Dtest=D01TypesExercises     # un seul fichier d'exercices
```

### 4. Passer le QCM
`docs/qcm/NN-xxx.md` : réponds d'abord sans regarder, puis compare aux **corrigés**
(en bas du fichier, avec l'explication de pourquoi les autres réponses sont fausses).

### 5. Cocher ta progression
Mets à jour les cases `- [ ]` → `- [x]` dans `docs/plan-revision.md`.

### Bonus : flashcards mobiles
`docs/flashcards.md` rassemble ~110 cartes Q/R couvrant les 11 domaines, idéales
pour réviser **5–10 min sur ton téléphone** (GitHub mobile, Obsidian, un viewer
Markdown). Lis la question, **prédis la réponse de tête**, puis déplie
**« Réponse »** pour vérifier. Fais un 1er passage en lisant tout, un 2nd en
cachant les réponses.

## État d'avancement du matériel

- [x] Socle (pom.xml, README, plan de révision)
- [x] Domaine 1 — Types, texte, nombres, dates
- [x] Domaine 2 — Contrôle de flux
- [x] Domaine 3 — POO
- [x] Domaine 4 — Exceptions
- [x] Domaine 5 — Tableaux & collections
- [x] Domaine 6 — Streams & lambdas
- [x] Domaine 7 — Modules (JPMS)
- [x] Domaine 8 — Concurrence
- [x] Domaine 9 — I/O & NIO.2
- [x] Domaine 10 — JDBC *(exemples exécutables sur une base H2 en mémoire)*
- [x] Domaine 11 — Localisation
- [x] Examen blanc (50 questions)
- [x] Flashcards de révision (~110 cartes, tous les domaines)

> **Tout le matériel est en place.** Chaque domaine = 1 fiche + des exemples exécutables
> + des exercices auto-corrigés (multi-niveaux) + un QCM avec corrigés. Bon courage ! 💪
