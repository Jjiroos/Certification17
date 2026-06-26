package cert.d08concurrency;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Domaine 8 — AtomicInteger : incréments concurrents au résultat garanti.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d08concurrency.AtomicVsRaceDemo
 */
public class AtomicVsRaceDemo {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger compteur = new AtomicInteger(0);
        ExecutorService pool = Executors.newFixedThreadPool(4);

        // 1000 incréments concurrents : AtomicInteger garantit le total exact
        for (int i = 0; i < 1000; i++) {
            pool.submit(compteur::incrementAndGet);
        }
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS); // attend la fin de toutes les tâches

        System.out.println("compteur atomique = " + compteur.get()); // 1000

        // sémantique get/and
        AtomicInteger a = new AtomicInteger(10);
        System.out.println(a.addAndGet(5));      // 15 (nouvelle valeur)
        System.out.println(a.getAndIncrement()); // 15 (ancienne valeur)
        System.out.println(a.get());              // 16
    }
}
