package Clase.Reto1.Ajedrez;

import java.util.Objects;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Tablero tablero = new Tablero();
        Jaque jaque = new Jaque(tablero);

        System.out.println("Escribe la posición inicial de las piezas blancas separadas por espacios: ");
        String piezasBlancas = scan.nextLine();
        if (!pedirpiezas(tablero, piezasBlancas, Color.BLANCO)) {
            System.out.println("Error al colocar las piezas blancas.");
            return;
        }

        System.out.println("Escribe la posición inicial de las piezas negras separadas por espacios: ");
        String piezasNegras = scan.nextLine();
        if (!pedirpiezas(tablero, piezasNegras, Color.NEGRO)) {
            System.out.println("Error al colocar las piezas negras.");
            return;
        }

        tablero.mostrar();

        String colorTurno = "";

        if (jaque.hayJaque(Color.BLANCO) && jaque.hayJaque(Color.NEGRO)) {
            System.out.println("Posición ilegal: dos reyes en jaque.");
        } else if (jaque.hayJaque(Color.NEGRO)) {
            colorTurno = "n";
            System.out.println("Jaque a las NEGRAS. Deben mover ellas.");
        } else if (jaque.hayJaque(Color.BLANCO)){
            colorTurno = "b";
            System.out.println("Jaque a las BLANCAS. Deben mover ellas.");
        } else {
            System.out.println("¿Quién empieza a mover? (b: blancas, n: negras): ");
            colorTurno = scan.nextLine().trim().toLowerCase();
        }

        if (!colorTurno.isEmpty()){
            Color turno = colorTurno.equals("b") ? Color.BLANCO : Color.NEGRO;
            System.out.println("Introduce tu movimiento (ej: Ch3, e3, Rd4): ");
            String movimiento = scan.nextLine().trim();

            if (tablero.mover(movimiento, turno)) {
                tablero.promocion(turno, tablero);

                tablero.mostrar();
            } else {
                System.out.println("Movimiento inválido.");
            }

            System.out.println("Se acabó.");
        }
    }

    public static boolean crearpieza(Tablero tablero, Tipo tipo, Color color, char letra, int num, boolean isTrue) {
        Pieza p1 = new Pieza(tipo, color);

        System.out.println(p1.toString());

        if (!tablero.colocarPieza(p1, letra, num)) {
            System.out.println("Posición incorrecta, no se han podido colocar las piezas.");
            isTrue = false;
        }
        return isTrue;
    }

    public static boolean pedirpiezas(Tablero tablero, String entrada, Color color) {

        String[] posiciones = entrada.trim().split("[,\\s]+");

        int peones = 0;
        int reyes = 0;
        int piezas = 0;

        for (String pos : posiciones) {
            if (pos.isEmpty()) return false;

            char pieza = 0;
            char columna;
            int fila;

            if (pos.length() != 2 && pos.length() != 3) return false;

            if (pos.length() == 3) {
                pieza = pos.charAt(0);
                columna = pos.charAt(1);
                fila = Character.getNumericValue(pos.charAt(2));

                if (!"RDTCA".contains(String.valueOf(pieza))) return false;
            } else {
                columna = pos.charAt(0);
                fila = Character.getNumericValue(pos.charAt(1));
            }

            if (columna < 'a' || columna > 'h') return false;

            if (fila < 1 || fila > 8) return false;

            if (pieza == 0 && (fila == 1 || fila == 8)) return false;

            if (pieza == 0) peones++;
            if (pieza == 'R') reyes++;

            if (peones > 8) return false;
            if (reyes > 1) return false;

            if (tablero.getPieza(fila, columna) != null) return false;

            boolean creada;
            switch (pieza) {
                case 'R' -> creada = crearpieza(tablero, Tipo.REY, color, columna, fila, true);
                case 'D' -> creada = crearpieza(tablero, Tipo.DAMA, color, columna, fila, true);
                case 'T' -> creada = crearpieza(tablero, Tipo.TORRE, color, columna, fila, true);
                case 'C' -> creada = crearpieza(tablero, Tipo.CABALLO, color, columna, fila, true);
                case 'A' -> creada = crearpieza(tablero, Tipo.ALFIL, color, columna, fila, true);
                default -> creada = crearpieza(tablero, Tipo.PEON, color, columna, fila, true);
            }

            if (!creada) return false;

            piezas++;
            if (piezas > 16) return false;
        }

        return reyes == 1;
    }


}

/*
Cb1, a2, b2, c2, d2, e2, f2, g2, h2, Re1, Dd1, Ta1, Th1, Cg1, Ac1, Af1
Cb8, a7, b7, c7, d7, e7, f7, g7, h7, Re8, Dd8, Ta8, Th8, Cg8, Ac8, Af8
*/