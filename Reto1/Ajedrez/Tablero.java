package Clase.Reto1.Ajedrez;

import java.util.Scanner;

public class Tablero {
    static final String RESET = "\u001B[0m";
    static final String FONDO_BLANCO = "\u001B[47m";
    static final String FONDO_NEGRO = "\u001B[100m";
    static final String TEXTO_BLANCO = "\u001B[37m";
    static final String TEXTO_NEGRO = "\u001B[30m";


    private Pieza[][] tablero = new Pieza[8][8];

    private char simbolo(Pieza p) {
        if (p.color == Color.BLANCO){
            return switch (p.tipo) {
                case REY -> simboloPiezas.REY_BLANCO;
                case DAMA -> simboloPiezas.DAMA_BLANCA;
                case TORRE -> simboloPiezas.TORRE_BLANCA;
                case ALFIL -> simboloPiezas.ALFIL_BLANCO;
                case CABALLO -> simboloPiezas.CABALLO_BLANCO;
                default -> simboloPiezas.PEON_BLANCO;
            };
        } else {
            return switch (p.tipo) {
                case REY -> simboloPiezas.REY_NEGRO;
                case DAMA -> simboloPiezas.DAMA_NEGRA;
                case TORRE -> simboloPiezas.TORRE_NEGRA;
                case ALFIL -> simboloPiezas.ALFIL_NEGRO;
                case CABALLO -> simboloPiezas.CABALLO_NEGRO;
                default -> simboloPiezas.PEON_NEGRO;
            };
        }
    }

    public boolean colocarPieza(Pieza pieza, char letra, int num) {
        int columna = letra - 'a';
        int fila = 8 - num;

        if (!dentro(fila, columna)) return  false;

        if (tablero[fila][columna] != null) return false;

        tablero[fila][columna] = pieza;
        return true;
    }

    public boolean mover(String pos, Color color) {
        Scanner sc = new Scanner(System.in);
        char tipoPieza;
        int columna, fila, contador = 0, x = 0, y = 0, buscaPieza;
        Tipo tipo;
        Pieza pieza = null;

        if (pos.length() < 2 || pos.length() > 4) return false;

        if (pos.length() == 3) {
            tipoPieza = pos.charAt(0);
            columna = pos.charAt(1) - 'a';
            System.out.println(columna);
            fila = 8 - Character.getNumericValue(pos.charAt(2));
            System.out.println(fila);

            if (!dentro(fila, columna)) return false;

            tipo = obtenerTipo(tipoPieza);
            if (tipo == null) return false;

            for (int i = 0; i < 8; i++){
                for (int j = 0; j < 8; j++){
                    pieza = tablero[i][j];

                    if (pieza != null && pieza.color == color && pieza.tipo == tipo && comprobarMovimiento(pieza, i, j, fila, columna) && (tablero[fila][columna] == null || tablero[fila][columna].color != color)) {
                      contador++;
                      x = i;
                      y = j;
                    }
                }
            }

            if (contador == 1) {
                pieza = tablero[x][y];
                tablero[x][y] = null;
                tablero[fila][columna] = pieza;
                return true;
            } else if (contador > 1) {
                System.out.println("Hay ambigüedad al buscar la pieza que quieres mover.");
                System.out.print("Introduce su columna: ");
                buscaPieza =  sc.next().charAt(0) - 'a';

                if (buscaPieza < 0 || buscaPieza > 7) return false;

                contador = 0;
                for (int i = 0; i < 8; i++){
                    pieza = tablero[i][buscaPieza];
                    if (pieza != null && pieza.color == color && pieza.tipo == tipo && comprobarMovimiento(pieza, i, buscaPieza, fila, columna) && (tablero[fila][columna] == null || tablero[fila][columna].color != color)) {
                        contador++;
                        x = i;
                        y = buscaPieza;
                    }
                }

                if (contador == 1) {
                    pieza = tablero[x][y];
                    tablero[x][y] = null;
                    tablero[fila][columna] = pieza;
                    return true;
                } else if (contador > 1) {
                    return false;
                } else return false;
            }
        } else if (pos.length() == 4 && Character.isLetter(pos.charAt(1))) {
            tipoPieza = pos.charAt(0);
            buscaPieza = pos.charAt(1) - 'a';
            fila = 8 - Character.getNumericValue(pos.charAt(3));
            columna = pos.charAt(2) - 'a';

            if (buscaPieza < 0 || buscaPieza > 7) return false;
            if (!dentro(fila, columna)) return false;

            tipo = obtenerTipo(tipoPieza);
            if (tipo == null) return false;

            for (int i = 0; i < 8; i++){
                pieza = tablero[i][buscaPieza];
                if (pieza != null && pieza.color == color && pieza.tipo == tipo && comprobarMovimiento(pieza, i, buscaPieza, fila, columna) && (tablero[fila][columna] == null || tablero[fila][columna].color != color)) {
                    contador++;
                    x = i;
                    y = buscaPieza;
                }
            }

            if (contador == 1) {
                pieza = tablero[x][y];
                tablero[x][y] = null;
                tablero[fila][columna] = pieza;
                return true;
            } else return false;

        } else if (pos.length() == 4 && Character.isDigit(pos.charAt(1))){
            tipoPieza = pos.charAt(0);
            buscaPieza = 8 - Character.getNumericValue(pos.charAt(1));
            fila = 8 - Character.getNumericValue(pos.charAt(3));
            columna = pos.charAt(2) - 'a';

            if (buscaPieza < 0 || buscaPieza > 7) return false;
            if (!dentro(fila, columna)) return false;

            tipo = obtenerTipo(tipoPieza);
            if (tipo == null) return false;

            for (int i = 0; i < 8; i++){
                pieza = tablero[buscaPieza][i];
                if (pieza != null && pieza.color == color && pieza.tipo == tipo && comprobarMovimiento(pieza, buscaPieza, i, fila, columna) && (tablero[fila][columna] == null || tablero[fila][columna].color != color)) {
                    contador++;
                    x = buscaPieza;
                    y = i;
                }
            }

            if (contador == 1) {
                pieza = tablero[x][y];
                tablero[x][y] = null;
                tablero[fila][columna] = pieza;
                return true;
            } else return false;

        } else if (pos.length() == 2) {
            columna = pos.charAt(0) - 'a';
            fila = 8 - Character.getNumericValue(pos.charAt(1));
            tipo = Tipo.PEON;

            for (int i = 1; i < 8; i++){
                for (int j = 1; j < 8; j++){
                    pieza = tablero[i][j];
                    if (pieza != null && pieza.color == color && pieza.tipo == tipo && comprobarMovimiento(pieza, i, j, fila, columna) && (tablero[fila][columna] == null || tablero[fila][columna].color != color)){
                        contador++;
                        x = i;
                        y = j;
                    }
                }
            }

            if (contador == 1) {
                pieza = tablero[x][y];
                tablero[x][y] = null;
                tablero[fila][columna] = pieza;
                return true;
            } else return false;
        }else return false;
        return false;
    }

    private Tipo obtenerTipo(char c) {
        return switch (c) {
            case 'R' -> Tipo.REY;
            case 'D' -> Tipo.DAMA;
            case 'A' -> Tipo.ALFIL;
            case 'C' -> Tipo.CABALLO;
            case 'T' -> Tipo.TORRE;
            default -> Tipo.PEON;
        };
    }


    public boolean comprobarMovimiento(Pieza pieza, int f1, int c1, int f2, int c2) {
        int dx = Math.abs(f1 - f2);
        int dy = Math.abs(c1 - c2);

        return switch (pieza.tipo) {
            case PEON -> (dx == 1 && dy == 0);
            case TORRE -> ((dx == 0 && dy != 0) || (dx != 0 && dy == 0));
            case ALFIL -> (dx == dy);
            case CABALLO -> ((dx == 2 && dy == 1) || (dx == 1 && dy == 2));
            case DAMA -> ((dx == 0 && dy != 0) || (dx != 0 && dy == 0) || (dx == dy));
            case REY -> ((dx == dy) || (dx == 0 && dy == 1) || (dx == 1 && dy == 0));
        };
    }

    public boolean dentro (int f, int c) {
        return f >= 0 && f < 8 && c >= 0 && c < 8;
    }

    public void mostrar() {
        for (int fila = 0; fila < 8; fila++) {
            System.out.print((8 - fila) + " ");
            for (int columna = 0; columna < 8; columna++) {

                boolean esBlanca = (fila + columna) % 2 == 0;
                String fondo = esBlanca ? FONDO_BLANCO : FONDO_NEGRO;

                if (tablero[fila][columna] == null) {
                    System.out.print(fondo + " " + "\u2003" + " " + RESET);
                } else {
                    Pieza p = tablero[fila][columna];
                    char s = simbolo(p);

                    System.out.print(fondo + TEXTO_NEGRO + " " + s + " " + RESET);
                }
            }
            System.out.println();
        }
        System.out.println("\u2003\u2003a \u2003b \u2003c \u2003d \u2003e \u2003f \u2003g \u2003h");
    }

    public void limpiar() {
        for (int fila = 0; fila < 8; fila++) {
            for (int columna = 0; columna < 8; columna++) {
                tablero[fila][columna] = null;
            }
        }
    }
}
