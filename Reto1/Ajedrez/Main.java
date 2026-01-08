package Clase.Reto1.Ajedrez;

import javax.swing.plaf.synth.SynthOptionPaneUI;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        System.out.println("Escribe la posición inicial de las piezas blancas separadas por espacios: ");
        String piezas = scan.nextLine();
        pedirpiezas(piezas, Color.BLANCO);

        System.out.println("Escribe la posición inicial de las piezas negras separadas por espacios: ");
        String piezas2 = scan.nextLine();
        pedirpiezas(piezas2, Color.NEGRO);
        Tablero tablero = new Tablero();
        tablero.mostrar();

        System.out.println("Gracias a Juan por su aportación!!! ;)");
    }

    public static Tipo tipo(String str1) {
        String pieza = "";

        if (str1.length() > 2) {
            pieza = str1.substring(0, 1);
        } else if (str1.length() == 2) {
            pieza = str1;
        }
        pieza = pieza.toUpperCase();
        String valor = "";
        switch (pieza) {
            case "T" -> valor = "TORRE";
            case "A" -> valor = "ALFIL";
            case "R" -> valor = "REY";
            case "D" -> valor = "DAMA";
            case "C" -> valor = "CABALLO";
            case "P" -> valor = "PEON";
            default -> valor = "PEON";
        }
        return Tipo.valueOf(valor);
    }

    public static void crearpieza(Tipo tipo, Color color) {

        Pieza p1 = new Pieza(tipo, color);
        System.out.println(p1.toString());
    }
    public static void pedirpiezas(String entrada, Color color) {
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
                case 'R' -> crearpieza(Tipo.REY, color);
                case 'D' -> crearpieza(Tipo.DAMA, color);
                case 'T' -> crearpieza(Tipo.TORRE, color);
                case 'C' -> crearpieza(Tipo.CABALLO, color);
                case 'A' -> crearpieza(Tipo.ALFIL, color);
                case '0' -> crearpieza(Tipo.PEON, color);
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
