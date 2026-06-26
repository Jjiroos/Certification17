package cert.d08concurrency;

import java.util.concurrent.CompletableFuture;

/**
 * Domaine 8 — CompletableFuture : enchaînements asynchrones.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d08concurrency.CompletableFutureDemo
 */
public class CompletableFutureDemo {
    public static void main(String[] args) {
        // supplyAsync -> thenApply (transforme) -> join (récupère)
        CompletableFuture<Integer> cf = CompletableFuture
                .supplyAsync(() -> 10)
                .thenApply(x -> x * 2)
                .thenApply(x -> x + 1);
        System.out.println(cf.join()); // 21

        // thenCompose : enchaîner deux étapes asynchrones
        CompletableFuture<String> cf2 = CompletableFuture
                .supplyAsync(() -> "a")
                .thenCompose(s -> CompletableFuture.supplyAsync(() -> s + "b"));
        System.out.println(cf2.join()); // ab

        // thenCombine : combiner deux futures indépendants
        CompletableFuture<Integer> somme = CompletableFuture.supplyAsync(() -> 3)
                .thenCombine(CompletableFuture.supplyAsync(() -> 4), Integer::sum);
        System.out.println(somme.join()); // 7
    }
}
