import java.util.Scanner;

public class Comienzo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        System.out.println("¿Quién empieza?");
        System.out.println("1. Jugador");
        System.out.println("2. Máquina");
        System.out.print("Elige una opción (1 o 2): ");

        opcion = scanner.nextInt();

        while (opcion != 1 && opcion != 2) {
            System.out.print("Opción inválida. Elige 1 o 2: ");
            opcion = scanner.nextInt();
        }

        if (opcion == 1) {
            System.out.println("Empieza el jugador.");
        } else {
            System.out.println("Empieza la máquina.");
        }
        scanner.close();
    }
}
