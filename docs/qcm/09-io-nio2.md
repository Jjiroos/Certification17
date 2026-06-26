# QCM 09 — I/O & NIO.2

> Réponds **sans lancer le code**. Corrigés en bas.
> 🟢 N1 · 🟡 N2 · 🔴 N3 · ⚠️ plusieurs réponses possibles sur certaines questions.

---

## 🟢 Niveau 1 — Fondamentaux

**Q1.** (Choose two) Quelles classes manipulent du **texte (caractères)** ?
- A) `FileReader`  B) `FileInputStream`  C) `BufferedWriter`  D) `FileOutputStream`

**Q2.**
```java
System.out.println(Path.of("/a/b/c.txt").getFileName());
```
- A) /a/b  B) c.txt  C) /a/b/c.txt  D) a

**Q3.** Quelle interface une classe doit-elle implémenter pour être sérialisable ?
- A) `Cloneable`  B) `Serializable`  C) `Externalizable` uniquement  D) `Comparable`

**Q4.** `Files.lines(path)` renvoie…
- A) une `List<String>`  B) un `String`  C) un `Stream<String>` (à fermer)  D) un `byte[]`

**Q5.** Que renvoie `System.console()` quand le programme tourne sans console (ex. IDE) ?
- A) une `Console` vide  B) `null`  C) une exception  D) `System.out`

## 🟡 Niveau 2 — Intermédiaire

**Q6.**
```java
System.out.println(Path.of("/home/user").resolve("/etc/conf"));
```
- A) /home/user/etc/conf  B) /etc/conf  C) /home/user  D) Exception

**Q7.**
```java
System.out.println(Path.of("/a/b").relativize(Path.of("/a/b/c/d")));
```
- A) /a/b/c/d  B) c/d  C) ../../c/d  D) Exception

**Q8.**
```java
System.out.println(Path.of("/x/y/../z/./w").normalize());
```
- A) /x/y/z/w  B) /x/z/w  C) /x/y/../z/w  D) /z/w

**Q9.**
```java
class C implements Serializable {
    transient int n = 5;
    String s = "hi";
}
// après sérialisation puis désérialisation :
```
- A) n=5, s="hi"  B) n=0, s="hi"  C) n=5, s=null  D) n=0, s=null

**Q10.** (Choose two) Quelles méthodes de `Files` lèvent `IOException` ?
- A) `Files.readAllLines`  B) `Path.getFileName`  C) `Files.delete`  D) `Path.resolve`

**Q11.**
```java
System.out.println(Path.of("a/b/c").getNameCount());
```
- A) 2  B) 3  C) 4  D) 1

## 🔴 Niveau 3 — Pièges & format examen

**Q12.** Lors de la **désérialisation** d'un objet `Serializable`…
- A) le constructeur de la classe est appelé
- B) le constructeur n'est PAS appelé (mais celui du 1er ancêtre non-sérialisable l'est)
- C) le bloc static est rejoué
- D) une exception est levée

**Q13.**
```java
class A { int x; }                 // PAS Serializable
class B implements Serializable { A a = new A(); }
// on sérialise un B
```
- A) Fonctionne, `a` est null après  B) NotSerializableException  C) Fonctionne normalement  D) Compilation fails

**Q14.** Différence entre `Path` et `Files` ?
- A) Aucune
- B) `Path` manipule des chemins sans toucher au disque ; `Files` effectue les opérations disque
- C) `Files` ne touche pas au disque
- D) `Path` lit le contenu des fichiers

**Q15.**
```java
try (var s = Files.lines(Path.of("data.txt"))) {
    System.out.println(s.count());
}
```
Pourquoi le `try-with-resources` ?
- A) Inutile, `Stream` n'a pas de ressource
- B) `Files.lines` ouvre un fichier qu'il faut fermer
- C) Pour attraper les exceptions
- D) `count()` l'exige

**Q16.** `BufferedReader.readLine()` à la fin du flux renvoie…
- A) `""`  B) `null`  C) `-1`  D) une exception

**Q17.**
```java
System.out.println(Path.of("/a/b/c").getName(0));
```
- A) /a  B) a  C) c  D) /a/b/c

---
---

## Corrigés

**Q1 → A et C.** `FileReader`/`BufferedWriter` traitent des caractères ; `FileInputStream`/`FileOutputStream` des octets.

**Q2 → B (c.txt).** `getFileName()` renvoie le dernier élément.

**Q3 → B (`Serializable`).** Interface marqueur (`Externalizable` est une alternative avancée, mais `Serializable` est la réponse de base).

**Q4 → C.** `Files.lines` renvoie un `Stream<String>` paresseux **à fermer**.

**Q5 → B (`null`).** `System.console()` peut renvoyer `null` hors d'un terminal.

**Q6 → B (/etc/conf).** `resolve` avec un chemin **absolu** remplace tout.

**Q7 → B (c/d).** Chemin relatif de `/a/b` vers `/a/b/c/d`.

**Q8 → B (/x/z/w).** `normalize` supprime `y/..` (annulé) et `.`.

**Q9 → B (n=0, s="hi").** `transient int n` n'est pas sérialisé → valeur par défaut `0`. `s` est restauré.

**Q10 → A et C.** `Files.readAllLines` et `Files.delete` font des accès disque → `IOException`. `Path.getFileName`/`resolve` sont purement en mémoire.

**Q11 → B (3).** Trois éléments : `a`, `b`, `c`.

**Q12 → B.** La désérialisation **ne rappelle pas** le constructeur de la classe sérialisable (mais celui du premier ancêtre **non** sérialisable).

**Q13 → B (NotSerializableException).** Un champ non `transient` d'un type non `Serializable` provoque cette exception à la sérialisation.

**Q14 → B.** `Path` = manipulation de chemins (mémoire) ; `Files` = opérations sur le système de fichiers.

**Q15 → B.** `Files.lines` ouvre une ressource fichier sous-jacente qui doit être fermée → try-with-resources.

**Q16 → B (`null`).** `readLine()` renvoie `null` en fin de flux.

**Q17 → B (a).** `getName(0)` = premier élément **sans** le séparateur racine.
