# Fiche 09 — I/O & NIO.2

> Objectif d'examen : *Using Java I/O API.*

## 1. Flux : octets vs caractères

| | Octets (binaire) | Caractères (texte) |
|---|------------------|--------------------|
| Lecture | `InputStream` (`FileInputStream`) | `Reader` (`FileReader`) |
| Écriture | `OutputStream` (`FileOutputStream`) | `Writer` (`FileWriter`) |
| Bufferisé | `BufferedInputStream` / `BufferedOutputStream` | `BufferedReader` / `BufferedWriter` |

- 🪤 **Octets** = données binaires (images…) ; **caractères** = texte (encodage). Ne pas confondre `InputStream` (octets) et `Reader` (caractères).
- `BufferedReader.readLine()` lit une ligne (`null` en fin). `PrintWriter` : `print`/`println`/`printf`.
- Les flux implémentent `AutoCloseable` → à utiliser en **try-with-resources**.

## 2. Console & entrée standard

- `System.in` (InputStream), `System.out` / `System.err` (PrintStream).
- `new BufferedReader(new InputStreamReader(System.in))` pour lire des lignes.
- `System.console()` → `Console` (🪤 peut être **`null`** si pas de console, ex. dans un IDE) : `readLine()`, `readPassword()`.
- `Scanner` : `nextInt()`, `nextLine()`, `hasNext()`.

## 3. NIO.2 — `Path` & `Files`

### `Path` (manipulation de chemins, **sans toucher au disque**)
- Création : `Path.of("a/b")` ou `Paths.get("a/b")`.
- `getFileName()`, `getParent()`, `getName(i)`, `getNameCount()`, `getRoot()`, `subpath(a, b)`.
- `resolve(autre)` : concatène ; 🪤 si `autre` est **absolu**, il **remplace** tout.
- `relativize(autre)` : chemin relatif de `this` vers `autre`.
- `normalize()` : élimine `.` et `..`.
- `toAbsolutePath()`, `toRealPath()` (accède au disque, lève si inexistant).

### `Files` (opérations sur le disque, lèvent `IOException`)
- Existence : `exists`, `notExists`, `isDirectory`, `isRegularFile`, `size`.
- Création : `createFile`, `createDirectory`, `createDirectories` (parents inclus), `createTempFile`.
- Lecture/écriture : `readAllLines`, `readString` (11+), `write`, `writeString`, `newBufferedReader/Writer`.
- Streams : `Files.lines(path)` (à **fermer**), `Files.list(dir)`, `Files.walk(dir)` (récursif), `Files.find`.
- 🪤 `Files.walk`/`Files.lines` renvoient des **`Stream`** qu'il faut fermer (try-with-resources).
- Copie/déplacement/suppression : `copy`, `move`, `delete` (🪤 `NoSuchFileException` si absent), `deleteIfExists`.

## 4. Sérialisation

- Une classe doit implémenter **`Serializable`** (interface marqueur).
- 🪤 Un champ **`transient`** (ou `static`) n'est **pas sérialisé** → restauré à sa valeur **par défaut** (`null`, `0`, `false`).
- `ObjectOutputStream.writeObject(o)` / `ObjectInputStream.readObject()` (cast nécessaire, lève `ClassNotFoundException`).
- 🪤 Si un champ est un objet non-`Serializable` (et non transient) → `NotSerializableException`.
- `serialVersionUID` : identifie la version de la classe (incohérence → `InvalidClassException`).
- 🪤 La **désérialisation n'appelle PAS le constructeur** de la classe sérialisable (mais appelle celui du premier ancêtre non-sérialisable).

---

### 🪤 Récap des pièges
1. Octets (`InputStream`/`OutputStream`) vs caractères (`Reader`/`Writer`).
2. `System.console()` peut être `null`.
3. `Path` ne touche pas le disque ; `Files` oui (lève `IOException`).
4. `resolve` avec un chemin absolu remplace tout.
5. `Files.lines`/`Files.walk` → Stream à fermer.
6. `transient`/`static` non sérialisés → valeur par défaut au retour.
7. Désérialisation : pas d'appel au constructeur de la classe sérialisée.
