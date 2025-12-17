package Clase.Reto1.Ajedrez;

import java.util.Scanner;

public class Main {
    static void main() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Escribe la posición inicial de las piezas negras separadas por espacios: ");
        String piezas = scan.nextLine();
        String pieza = "";
        for (int i = 0; i < piezas.length(); i++) {
            int contador = 0;
            contador = piezas.indexOf(" ");
            if (!(contador == -1)) {
                pieza = piezas.substring(0,contador);
                piezas = piezas.substring(contador+1);
                System.out.println(pieza);
            }
            if (contador == piezas.length()) {
                i = piezas.length();
            }
        }

        System.out.println(piezas);

        Tablero tablero = new Tablero();
        tablero.mostrarTablero();

        System.out.println("Gracias a Juan por su aportación!!! ;)");
    }
}
