package Clase.Reto1.Ajedrez;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Tablero tablero = new Tablero();
        System.out.println("Escribe la posición inicial de las piezas blancas separadas por espacios: ");
        String piezas = scan.nextLine();
        pedirpiezas(tablero, piezas, Color.BLANCO);

        System.out.println("Escribe la posición inicial de las piezas negras separadas por espacios: ");
        String piezas2 = scan.nextLine();
        pedirpiezas(tablero, piezas2, Color.NEGRO);
        tablero.mostrar();

        System.out.println("Gracias a Juan por su aportación!!! ;)");
    }

    public static void crearpieza(Tablero tablero, Tipo tipo, Color color, char letra, int num) {
        Pieza p1 = new Pieza(tipo, color);

        System.out.println(p1.toString());

        if (!tablero.colocarPieza(p1, letra, num)){
            System.out.println("Posición incorrecta, no se han podido colocar las piezas.");
        }
    }
    public static void pedirpiezas(Tablero tablero, String entrada, Color color) {
        String[] posiciones = entrada.split(" ");

        for (String pos : posiciones) {
            pos = pos.trim();

            char pieza = 0;
            char columna;
            int fila;

            if (Character.isUpperCase(pos.charAt(0))) {
                // Tiene pieza (ej: Cb1)
                pieza = pos.charAt(0);
                columna = pos.charAt(1);
                fila = Character.getNumericValue(pos.charAt(2));
            } else {
                // No tiene pieza (ej: g3)
                columna = pos.charAt(0);
                fila = Character.getNumericValue(pos.charAt(1));
            }

            switch (pieza) {
                case 'R' -> crearpieza(tablero, Tipo.REY, color, columna, fila);
                case 'D' -> crearpieza(tablero, Tipo.DAMA, color, columna, fila);
                case 'T' -> crearpieza(tablero, Tipo.TORRE, color, columna, fila);
                case 'C' -> crearpieza(tablero, Tipo.CABALLO, color, columna, fila);
                case 'A' -> crearpieza(tablero, Tipo.ALFIL, color, columna, fila);
                case '0' -> crearpieza(tablero, Tipo.PEON, color, columna, fila);
            }

            System.out.println("Posición: " + pos);
            if (pieza != 0) {
                System.out.println("  Pieza: " + pieza);
            } else {
                System.out.println("  Pieza: Peón");
            }
            System.out.println("  Columna: " + columna);
            System.out.println("  Fila: " + fila);
            System.out.println(color);
        }
    }
}