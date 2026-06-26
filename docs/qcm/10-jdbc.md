# QCM 10 — JDBC

> Réponds **sans lancer le code**. Corrigés en bas.
> 🟢 N1 · 🟡 N2 · 🔴 N3 · ⚠️ plusieurs réponses possibles sur certaines questions.

---

## 🟢 Niveau 1 — Fondamentaux

**Q1.** Quel objet obtient-on en premier pour parler à la base ?
- A) `Statement`  B) `Connection`  C) `ResultSet`  D) `Driver`

**Q2.** Quelle méthode exécute un `SELECT` ?
- A) `executeUpdate`  B) `executeQuery`  C) `execute` uniquement  D) `run`

**Q3.** Que renvoie `executeUpdate("INSERT ...")` ?
- A) un `ResultSet`  B) le nombre de lignes affectées  C) `true`/`false`  D) rien

**Q4.** Les colonnes d'un `ResultSet` sont indexées à partir de…
- A) 0  B) 1  C) -1  D) le nom uniquement

**Q5.** Que renvoie `ResultSet.next()` ?
- A) la ligne suivante  B) un `boolean` (true s'il y a une ligne)  C) un `int`  D) void

## 🟡 Niveau 2 — Intermédiaire

**Q6.**
```java
PreparedStatement ps = con.prepareStatement("SELECT * FROM t WHERE id = ?");
ps.setInt(1, 5);
```
- A) Le paramètre `?` est l'index 0
- B) Le paramètre `?` est l'index 1
- C) `setInt` n'existe pas
- D) Il faut `setString`

**Q7.** (Choose two) Avantages de `PreparedStatement` sur `Statement` ?
- A) Protège contre l'injection SQL
- B) Réutilisable avec des paramètres
- C) Plus rapide à taper
- D) Ne nécessite pas de Connection

**Q8.** Pour appeler une **procédure stockée**, on utilise…
- A) `Statement`  B) `PreparedStatement`  C) `CallableStatement`  D) `ResultSet`

**Q9.**
```java
try (Connection con = DriverManager.getConnection(url);
     Statement st = con.createStatement();
     ResultSet rs = st.executeQuery("SELECT COUNT(*) FROM t")) {
    rs.next();
    System.out.println(rs.getInt(1));
}
```
Si la table contient 3 lignes, qu'affiche-t-on ?
- A) 0  B) 1  C) 3  D) Exception

**Q10.** Dans quel ordre faut-il fermer les ressources JDBC (sans try-with-resources) ?
- A) Connection, Statement, ResultSet
- B) ResultSet, Statement, Connection
- C) L'ordre n'importe pas
- D) Statement, ResultSet, Connection

## 🔴 Niveau 3 — Pièges & format examen

**Q11.**
```java
ResultSet rs = st.executeQuery("SELECT nom FROM t");
String nom = rs.getString(1);   // sans rs.next() avant
```
- A) Renvoie la 1re valeur
- B) SQLException (curseur avant la 1re ligne)
- C) null
- D) Compilation fails

**Q12.** Que fait `con.setAutoCommit(false)` ?
- A) Désactive la base
- B) Les modifications ne sont validées qu'au `commit()` explicite
- C) Annule toutes les requêtes
- D) Rend la connexion en lecture seule

**Q13.** Quelle exception est typiquement levée par les opérations JDBC ?
- A) `IOException`  B) `SQLException`  C) `RuntimeException`  D) `DataException`

**Q14.**
```java
Statement st = con.createStatement();
boolean b = st.execute("UPDATE t SET x = 1");
```
Que vaut `b` ?
- A) `true`  B) `false`  C) le nombre de lignes  D) Exception

**Q15.** (Choose two) Vrai à propos de `DriverManager.getConnection(url, user, password)` ?
- A) Renvoie une `Connection`
- B) Peut lever `SQLException`
- C) Renvoie un `ResultSet`
- D) Ne nécessite jamais de pilote sur le classpath

**Q16.**
```java
PreparedStatement ps = con.prepareStatement("INSERT INTO t VALUES (?, ?)");
ps.setInt(1, 1);
// on oublie de positionner le paramètre 2
ps.executeUpdate();
```
- A) Insère avec null  B) SQLException (paramètre non défini)  C) Compile pas  D) Insère 0

---
---

## Corrigés

**Q1 → B (Connection).** `DriverManager.getConnection(...)` fournit la `Connection`, point d'entrée.

**Q2 → B (`executeQuery`).** Renvoie un `ResultSet`. (`execute` marche aussi mais renvoie un `boolean`.)

**Q3 → B.** `executeUpdate` (INSERT/UPDATE/DELETE/DDL) renvoie le **nombre de lignes affectées**.

**Q4 → B (1).** Les colonnes (et les paramètres `?`) sont **1-based**.

**Q5 → B.** `next()` renvoie un `boolean` et avance le curseur.

**Q6 → B.** Le premier `?` correspond à l'index **1** (`setInt(1, ...)`).

**Q7 → A et B.** Précompilé/paramétré : protège de l'injection SQL et est réutilisable.

**Q8 → C (`CallableStatement`).** Pour `{call procedure(...)}`.

**Q9 → C (3).** `getInt(1)` lit la valeur de `COUNT(*)` après `next()`.

**Q10 → B.** Fermer dans l'ordre **inverse** de création : `ResultSet`, puis `Statement`, puis `Connection`. (try-with-resources le fait automatiquement.)

**Q11 → B (SQLException).** Sans `rs.next()`, le curseur est **avant** la première ligne → lire une colonne lève `SQLException`.

**Q12 → B.** En désactivant l'auto-commit, les changements ne sont persistés qu'au `commit()` (et annulables par `rollback()`).

**Q13 → B (`SQLException`).** Exception **checked** centrale de JDBC.

**Q14 → B (`false`).** `execute` renvoie `false` quand le résultat n'est **pas** un `ResultSet` (cas d'un UPDATE) ; le compte s'obtient via `getUpdateCount()`.

**Q15 → A et B.** Renvoie une `Connection` et peut lever `SQLException`. (Un pilote JDBC doit être sur le classpath.)

**Q16 → B (SQLException).** Tous les paramètres `?` doivent être positionnés avant l'exécution, sinon `SQLException`.
