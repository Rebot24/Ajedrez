
import java.awt.*;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);
        Tablero tablero = new Tablero();
        boolean posicionValida = false;

        while (!posicionValida) {

            tablero.limpiar();

            System.out.println("Escribe la posición inicial de las piezas BLANCAS separadas por espacios:");
            String piezasBlancas = scan.nextLine().toUpperCase();

            System.out.println("Escribe la posición inicial de las piezas NEGRAS separadas por espacios:");
            String piezasNegras = scan.nextLine().toUpperCase();

            boolean blancasOk = pedirpiezas(tablero, piezasBlancas, Color.BLANCO);
            boolean negrasOk = pedirpiezas(tablero, piezasNegras, Color.NEGRO);

            if (!blancasOk || !negrasOk) {
                System.out.println("Error en la entrada. Vuelve a introducir las piezas.\n");
                continue;
            }

            if (!tablero.composicionMinimaValida()) {
                System.out.println("Composición inválida: debe haber un rey blanco y uno negro.\n");
                continue;
            }

            posicionValida = true;
        }

        System.out.println("Posición inicial correcta:");
        tablero.mostrar();
    }


    //El Tablero se crea, pero no recibe ninguna pieza
    // crearpieza( crea objetos que se pierden y no sirven para nada
    public static boolean pedirpiezas(Tablero tablero, String entrada, Color color) {

        if (entrada == null || entrada.isEmpty()) {
            return true;
        }

        String[] posiciones = entrada.split(" ");

        for (String pos : posiciones) {

            pos = pos.trim();
            if (pos.length() < 2) {
                return false;
            }

            char letraPieza;
            String casilla;

            if (Character.isUpperCase(pos.charAt(0))) {
                letraPieza = pos.charAt(0);
                casilla = pos.substring(1);
            } else {
                letraPieza = 'P';
                casilla = pos;
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
                return false;
            }

            Pieza pieza = new Pieza(tipo, color);

            if (!tablero.colocarPieza(pieza, casilla)) {
                return false;
            }
        }

        return true;
    }
}
