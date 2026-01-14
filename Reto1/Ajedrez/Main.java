package Ajedrez.Reto1.Ajedrez;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Tablero tablero = new Tablero();
        int continuar = 1;
        do {
            System.out.println("Escribe la posición inicial de las piezas blancas separadas por espacios: ");
            String piezas = scan.nextLine();
            if (pedirpiezas(tablero, piezas, Color.BLANCO)){
                System.out.println("Escribe la posición inicial de las piezas negras separadas por espacios: ");
                String piezas2 = scan.nextLine();
                if (pedirpiezas(tablero, piezas2, Color.NEGRO)){
                    tablero.mostrar();
                    continuar = 0;

                    //si hay jaque empieza ese equipo
                    System.out.println("¿Quién empieza a mover: (b, n) ");
                    String color = scan.nextLine();

                    System.out.println("Ahora dime el movimiento con este formato: (Ch3, e3, Rd4)");
                    String movimiento = scan.nextLine();
                    movimiento = movimiento.trim();

                    if (color == "b"){
                        tablero.mover(movimiento, Color.BLANCO);
                    } else if (color == "n") {
                        tablero.mover(movimiento, Color.NEGRO);
                    }
                    tablero.mostrar();
                }
            }
            tablero.limpiar();
        } while (continuar != 0);


        System.out.println("Gracias a Juan por su aportación!!! ;)");
    }

    public static boolean crearpieza(Tablero tablero, Tipo tipo, Color color, char letra, int num, boolean isTrue) {
        Pieza p1 = new Pieza(tipo, color);

        System.out.println(p1.toString());

        if (!tablero.colocarPieza(p1, letra, num)){
            System.out.println("Posición incorrecta, no se han podido colocar las piezas.");
            isTrue = false;
        }
        return isTrue;
    }
    public static boolean pedirpiezas(Tablero tablero, String entrada, Color color) {
        String[] posiciones = entrada.split(" ");
        boolean isTrue = true;

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

            if (isTrue) {
                switch (pieza) {
                    case 'R' -> isTrue = crearpieza(tablero, Tipo.REY, color, columna, fila, isTrue);
                    case 'D' -> isTrue = crearpieza(tablero, Tipo.DAMA, color, columna, fila, isTrue);
                    case 'T' -> isTrue = crearpieza(tablero, Tipo.TORRE, color, columna, fila, isTrue);
                    case 'C' -> isTrue = crearpieza(tablero, Tipo.CABALLO, color, columna, fila, isTrue);
                    case 'A' -> isTrue = crearpieza(tablero, Tipo.ALFIL, color, columna, fila, isTrue);
                    default -> isTrue = crearpieza(tablero, Tipo.PEON, color, columna, fila, isTrue);
                }
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
        return isTrue;
    }
}