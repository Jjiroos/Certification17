package cert.d03oop;

/**
 * Domaine 3 — Classes/interfaces scellées (sealed, Java 17 finalisé).
 *
 * Chaque sous-type DOIT être final, sealed, ou non-sealed.
 * NB : on utilise instanceof (et non le switch pattern, qui est preview en 17).
 *
 *   mvn -q compile exec:java -Dexec.mainClass=cert.d03oop.SealedDemo
 */
public class SealedDemo {

    sealed interface Vehicle permits Car, Truck, Motorcycle {}

    static final class Car implements Vehicle {}
    static sealed class Truck implements Vehicle permits Pickup {}
    static final class Pickup extends Truck {}
    static non-sealed class Motorcycle implements Vehicle {} // rouvre l'héritage
    static class SportBike extends Motorcycle {}             // permis : Motorcycle est non-sealed

    static String type(Vehicle v) {
        if (v instanceof Car c)             return "voiture";
        else if (v instanceof Truck t)      return "camion";
        else if (v instanceof Motorcycle m) return "moto";
        return "?";
    }

    public static void main(String[] args) {
        System.out.println(type(new Car()));        // voiture
        System.out.println(type(new Pickup()));     // camion (Pickup est un Truck)
        System.out.println(type(new SportBike()));  // moto  (SportBike est une Motorcycle)
    }
}
