import java.util.Scanner;

public class Comienzo {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        System.out.println("¿Quien empieza?");
        System.out.println("1. Jugador");
        System.out.println("2. Maquina");
        System.out.print("Elige una opcion (1 o 2): ");

        opcion = scanner.nextInt();

        while (opcion != 1 && opcion != 2) {
            System.out.print("Opcion invalida. Elige 1 o 2: ");
            opcion = scanner.nextInt();
        }

        if (opcion == 1) {
            System.out.println("Empieza el jugador.");
        } else {
            System.out.println("Empieza la maquina.");
        }
        scanner.close();
    }
}
