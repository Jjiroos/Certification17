package cert.d01types;

import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

/**
 * Domaine 1 — API Date-Time (java.time), immuable.
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d01types.DateTimeDemo
 */
public class DateTimeDemo {
    public static void main(String[] args) {
        // --- Création (mois de 1 à 12, PAS 0..11 comme l'ancien Calendar) ---
        LocalDate d = LocalDate.of(2026, 1, 31); // 31 janvier 2026
        LocalTime t = LocalTime.of(14, 30);       // 14:30
        LocalDateTime dt = LocalDateTime.of(d, t);
        System.out.println(d);   // 2026-01-31
        System.out.println(t);   // 14:30
        System.out.println(dt);  // 2026-01-31T14:30

        // --- Immutabilité : plusXxx renvoie une NOUVELLE date ---
        d.plusDays(1);                 // ignoré
        System.out.println(d);         // 2026-01-31 (inchangé)
        LocalDate demain = d.plusDays(1);
        System.out.println(demain);    // 2026-02-01 (passe au mois suivant)

        // --- plusMonths : ajustement automatique du jour ---
        LocalDate fin = LocalDate.of(2026, 1, 31).plusMonths(1);
        System.out.println(fin);       // 2026-02-28 (pas de 31 février)

        // --- Period (ans/mois/jours) vs Duration (heures/min/sec) ---
        Period p = Period.of(1, 2, 3);
        System.out.println(p);              // P1Y2M3D
        System.out.println(Period.ofWeeks(2)); // P14D (converti en jours, pas P2W)
        Duration dur = Duration.ofMinutes(90);
        System.out.println(dur);            // PT1H30M

        // --- Combiner Period avec une date ---
        System.out.println(LocalDate.of(2026, 1, 1).plus(Period.ofDays(40)));
        // 2026-02-10

        // --- Différence entre deux dates ---
        long jours = ChronoUnit.DAYS.between(
                LocalDate.of(2026, 1, 1), LocalDate.of(2026, 1, 11));
        System.out.println(jours); // 10

        // --- Parsing & formatage ---
        LocalDate parsed = LocalDate.parse("2026-12-25"); // format ISO par défaut
        System.out.println(parsed);                       // 2026-12-25
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println(parsed.format(fmt));           // 25/12/2026

        // --- Fuseau horaire ---
        ZonedDateTime z = ZonedDateTime.of(
                LocalDateTime.of(2026, 6, 25, 12, 0), ZoneId.of("Europe/Paris"));
        System.out.println(z.getZone()); // Europe/Paris
    }
}
