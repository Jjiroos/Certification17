# Fiche 10 — JDBC

> Objectif d'examen : *Accessing databases using JDBC.*

## 1. Les 4 interfaces clés (`java.sql`)

| Interface | Rôle |
|-----------|------|
| `Connection` | session avec la base (obtenue via `DriverManager`) |
| `Statement` | exécute du SQL **statique** |
| `PreparedStatement` | SQL **précompilé** avec paramètres `?` (recommandé) |
| `CallableStatement` | appel de **procédures stockées** (`{call proc(?)}`) |
| `ResultSet` | curseur sur les lignes d'un `SELECT` |

- En Java 17, **pas besoin** de `Class.forName(...)` : le pilote est chargé automatiquement (ServiceLoader) s'il est sur le classpath.

## 2. Obtenir une connexion

```java
String url = "jdbc:h2:mem:test"; // jdbc:<sgbd>:<détails>
try (Connection con = DriverManager.getConnection(url, user, password)) {
    ...
}
```
- 🪤 Toutes les opérations JDBC lèvent **`SQLException`** (checked).

## 3. Les 3 méthodes d'exécution

| Méthode | Pour | Retour |
|---------|------|--------|
| `executeQuery(sql)` | `SELECT` | un **`ResultSet`** |
| `executeUpdate(sql)` | `INSERT`/`UPDATE`/`DELETE`/DDL | un **`int`** (nb de lignes affectées) |
| `execute(sql)` | n'importe quoi | un **`boolean`** (`true` si le résultat est un `ResultSet`) |

## 4. `PreparedStatement` (paramètres)

```java
try (PreparedStatement ps = con.prepareStatement(
        "SELECT nom FROM personne WHERE age > ?")) {
    ps.setInt(1, 18);              // 🪤 paramètres 1-based
    try (ResultSet rs = ps.executeQuery()) { ... }
}
```
- 🪤 Les `?` sont indexés à partir de **1**. `setString`, `setInt`, `setObject`…
- 🪤 Un paramètre non positionné → `SQLException` à l'exécution.
- Avantages : protection contre l'**injection SQL**, réutilisable, précompilé.

## 5. `ResultSet` (parcours)

```java
while (rs.next()) {                 // next() renvoie un boolean, avance le curseur
    String nom = rs.getString("nom");   // par nom de colonne
    int age = rs.getInt(2);             // 🪤 colonnes 1-based
}
```
- 🪤 Au départ, le curseur est **avant** la 1re ligne : lire **sans** `next()` → `SQLException`.
- 🪤 Colonnes indexées à partir de **1** (jamais 0).
- Par défaut un `ResultSet` est `TYPE_FORWARD_ONLY` + `CONCUR_READ_ONLY` (parcours avant uniquement).

## 6. Transactions

- Par défaut **auto-commit** : chaque instruction est validée immédiatement.
- `con.setAutoCommit(false)` → grouper, puis `con.commit()` ou `con.rollback()`.
- `Savepoint` pour des points de restauration partiels.

## 7. Fermeture des ressources

- 🪤 Fermer dans l'ordre **inverse** : `ResultSet`, puis `Statement`, puis `Connection`.
- Le **try-with-resources** s'en charge automatiquement (fermeture inverse de la déclaration).
- Fermer une `Connection` ferme implicitement ses `Statement`/`ResultSet`.

---

### 🪤 Récap des pièges
1. Tout lève `SQLException` (checked).
2. `executeQuery` → `ResultSet` ; `executeUpdate` → `int` ; `execute` → `boolean`.
3. Colonnes ET paramètres `?` sont **1-based**.
4. Lire un `ResultSet` sans `next()` → `SQLException`.
5. `PreparedStatement` protège de l'injection SQL et est réutilisable.
6. Fermeture en ordre inverse (ou try-with-resources).
7. Auto-commit actif par défaut.
