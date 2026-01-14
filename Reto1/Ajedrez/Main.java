package Clase.Reto1.Ajedrez;

import java.awt.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Tablero tablero = new Tablero();
        boolean posicionValida = false;

        // Entrada de posición inicial
        while (!posicionValida) {

            tablero.limpiar();

            System.out.println("Escribe la posición inicial de las piezas BLANCAS separadas por espacios:");
            String blancas = scan.nextLine();

            System.out.println("Escribe la posición inicial de las piezas NEGRAS separadas por espacios:");
            String negras = scan.nextLine();

            boolean blancasOk = pedirPiezas(tablero, blancas, Color.BLANCO);
            boolean negrasOk = pedirPiezas(tablero, negras, Color.NEGRO);

            if (!blancasOk || !negrasOk) {
                System.out.println("Error en la entrada. Vuelve a intentarlo.\n");
                continue;
            }

            if (!tablero.composicionMinimaValida()) {
                System.out.println("Composición inválida: debe haber un rey blanco y uno negro.\n");
                continue;
            }

            posicionValida = true;
        }

        System.out.println("Posición inicial correcta:");
        tablero.mostrarTablero();

        // Jugada
        System.out.println("\nIntroduce la jugada (ejemplos: e2 e4  |  Cc5):");
        String jugada = scan.nextLine().trim();

        boolean exito;

        if (jugada.contains(" ")) {
            String[] partes = jugada.split(" ");
            exito = partes.length == 2 &&
                    tablero.mover(partes[0].toLowerCase(), partes[1].toLowerCase());
        } else {
            exito = moverPorTipo(tablero, jugada, Color.BLANCO);
        }

        if (!exito) {
            System.out.println("Jugada ilegal. Pierde el bando.");
        } else {
            System.out.println("Tablero tras la jugada:");
            tablero.mostrarTablero();
        }
    }

    // ----------------------------
    // Colocación de piezas
    // ----------------------------
    public static boolean pedirPiezas(Tablero tablero, String entrada, Color color) {

        if (entrada == null || entrada.trim().isEmpty()) {
            return true;
        }

        String[] posiciones = entrada.split(" ");

        for (String pos : posiciones) {

            pos = pos.trim();
            if (pos.length() < 2) {
                System.out.println("Entrada inválida: " + pos);
                return false;
            }

            char letraPieza;
            String casilla;

            if (Character.isLetter(pos.charAt(0)) && Character.isUpperCase(pos.charAt(0))) {
                letraPieza = Character.toUpperCase(pos.charAt(0));
                casilla = pos.substring(1).toLowerCase();
            } else {
                letraPieza = 'P';
                casilla = pos.toLowerCase();
            }

            Tipo tipo = switch (letraPieza) {
                case 'R' -> Tipo.REY;
                case 'D' -> Tipo.DAMA;
                case 'T' -> Tipo.TORRE;
                case 'C' -> Tipo.CABALLO;
                case 'A' -> Tipo.ALFIL;
                case 'P' -> Tipo.PEON;
                default -> null;
            };

            if (tipo == null) {
                System.out.println("Tipo de pieza inválido: " + pos);
                return false;
            }

            Pieza pieza = new Pieza(tipo, color);

            if (!tablero.colocarPieza(pieza, casilla)) {
                System.out.println("No se pudo colocar la pieza en: " + casilla);
                return false;
            }
        }

        return true;
    }

    // ----------------------------
    // Jugada tipo "Cc5" simplificada
    // ----------------------------
    public static boolean moverPorTipo(Tablero tablero, String jugada, Color color) {

        if (jugada.length() < 3) return false;

        char letra = Character.toUpperCase(jugada.charAt(0));
        String destino = jugada.substring(1).toLowerCase();

        Tipo tipo = switch (letra) {
            case 'R' -> Tipo.REY;
            case 'D' -> Tipo.DAMA;
            case 'T' -> Tipo.TORRE;
            case 'C' -> Tipo.CABALLO;
            case 'A' -> Tipo.ALFIL;
            default -> Tipo.PEON;
        };

        for (char col = 'a'; col <= 'h'; col++) {
            for (int fila = 1; fila <= 8; fila++) {
                String origen = "" + col + fila;
                Pieza p = tablero.obtenerPieza(origen);

                if (p != null && p.tipo == tipo && p.color == color) {
                    if (tablero.mover(origen, destino)) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}
