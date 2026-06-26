package cert.d08concurrency;

import org.junit.jupiter.api.Test;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Exercices Domaine 8 — Concurrence (résultats déterministes). PRÉDIS sans lancer.
 * 🟢 N1   🟡 N2   🔴 N3
 * Pour assertThrows : remplace Error.class par la classe d'exception PRÉCISE.
 */
class D08ConcurrencyExercises {

    @Test // 🟢 N1
    void atomicAddAndGet() {
        AtomicInteger a = new AtomicInteger(7);
        int reponse = -1; // TODO : a.addAndGet(3)
        assertEquals(reponse, a.addAndGet(3));
    }

    @Test // 🟢 N1 — getAndIncrement renvoie l'ANCIENNE valeur
    void atomicGetAndIncrement() {
        AtomicInteger a = new AtomicInteger(5);
        int reponse = -1; // TODO : a.getAndIncrement()
        assertEquals(reponse, a.getAndIncrement());
    }

    @Test // 🟡 N2
    void completableChain() throws Exception {
        int reponse = -1; // TODO : (4*3)+2
        assertEquals(reponse, CompletableFuture.supplyAsync(() -> 4)
                .thenApply(x -> x * 3)
                .thenApply(x -> x + 2)
                .get());
    }

    @Test // 🟡 N2
    void thenCombine() {
        int reponse = -1; // TODO : 6 + 7
        assertEquals(reponse, CompletableFuture.supplyAsync(() -> 6)
                .thenCombine(CompletableFuture.supplyAsync(() -> 7), Integer::sum)
                .join());
    }

    @Test // 🟡 N2
    void parallelSum() {
        int reponse = -1; // TODO : somme 1..10
        assertEquals(reponse, IntStream.rangeClosed(1, 10).parallel().sum());
    }

    @Test // 🟡 N2
    void callableFuture() throws Exception {
        ExecutorService pool = Executors.newSingleThreadExecutor();
        Future<Integer> f = pool.submit(() -> 6 * 7);
        int reponse = -1; // TODO : f.get()
        assertEquals(reponse, f.get());
        pool.shutdown();
    }

    @Test // 🔴 N3 — 500 incréments atomiques concurrents
    void incrementsConcurrents() throws Exception {
        AtomicInteger c = new AtomicInteger();
        ExecutorService pool = Executors.newFixedThreadPool(4);
        for (int i = 0; i < 500; i++) pool.submit(c::incrementAndGet);
        pool.shutdown();
        pool.awaitTermination(5, TimeUnit.SECONDS);
        int reponse = -1; // TODO : c.get()
        assertEquals(reponse, c.get());
    }

    @Test // 🔴 N3 — ConcurrentHashMap et null
    void concurrentMapNull() {
        ConcurrentHashMap<String, String> m = new ConcurrentHashMap<>();
        assertThrows(Error.class, () -> m.put("k", null)); // TODO : quelle exception ?
    }

    @Test // 🔴 N3 — compareAndSet
    void compareAndSet() {
        AtomicInteger a = new AtomicInteger(10);
        boolean reponse = false; // TODO : a.compareAndSet(10, 20)
        assertEquals(reponse, a.compareAndSet(10, 20));
    }
}
