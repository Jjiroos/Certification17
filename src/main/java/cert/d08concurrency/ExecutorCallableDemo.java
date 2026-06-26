package cert.d08concurrency;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Domaine 8 — ExecutorService + Callable + Future.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d08concurrency.ExecutorCallableDemo
 */
public class ExecutorCallableDemo {
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        ExecutorService pool = Executors.newFixedThreadPool(3);

        // Callable<Integer> renvoie une valeur, récupérée via Future
        List<Future<Integer>> futures = new ArrayList<>();
        for (int i = 1; i <= 5; i++) {
            int n = i;                          // effectively final
            futures.add(pool.submit(() -> n * n));
        }

        int total = 0;
        for (Future<Integer> f : futures) {
            total += f.get();                   // bloque jusqu'au résultat
        }
        System.out.println("somme des carres 1..5 = " + total); // 55

        pool.shutdown();                        // refuse les nouvelles tâches
        System.out.println("shutdown demande");
    }
}
