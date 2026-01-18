package Clase.Reto1.Ajedrez;

import java.util.Scanner;

public class Tablero {

    static final String RESET = "\u001B[0m";
    static final String FONDO_BLANCO = "\u001B[47m";
    static final String FONDO_NEGRO = "\u001B[100m";
    static final String TEXTO_BLANCO = "\u001B[37m";
    static final String TEXTO_NEGRO = "\u001B[30m";

    private Pieza[][] tablero = new Pieza[8][8];


    public boolean colocarPieza(Pieza pieza, char letra, int num) {
        int columna = letra - 'a';
        int fila = 8 - num;

        if (!dentro(fila, columna)) return false;
        if (tablero[fila][columna] != null) return false;

        tablero[fila][columna] = pieza;
        return true;
    }


    public boolean mover(String pos, Color color) {

        Scanner sc = new Scanner(System.in);
        char tipoPieza;
        int columna, fila, contador = 0, x = 0, y = 0, buscaPieza;
        Tipo tipo;
        Pieza pieza;

        if (pos == null || pos.length() < 2 || pos.length() > 4) return false;

        if (pos.length() == 3) {

            tipoPieza = pos.charAt(0);
            columna = pos.charAt(1) - 'a';
            fila = 8 - Character.getNumericValue(pos.charAt(2));

            if (!dentro(fila, columna)) return false;

            tipo = obtenerTipo(tipoPieza);
            if (tipo == null) return false;

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {

                    pieza = tablero[i][j];
                    if (pieza == null || pieza.color != color || pieza.tipo != tipo) continue;

                    // peon usa su propio metodo
                    boolean movimientoValido;

                    if (pieza.tipo == Tipo.PEON) {
                        movimientoValido = movimientoPeonValido(pieza, i, j, fila, columna);
                    } else {
                        movimientoValido = comprobarMovimiento(pieza, i, j, fila, columna);
                    }

                    if (movimientoValido &&
                            (!requiereColision(pieza.tipo) || caminoLibre(i, j, fila, columna)) && (tablero[fila][columna] == null || tablero[fila][columna].color != color)) {

                        contador++;
                        x = i;
                        y = j;
                    }
                }
            }

            if (contador == 1) {
                moverPieza(x, y, fila, columna);
                return true;
            }

            if (contador > 1) {
                System.out.println("Hay ambigüedad. Introduce la columna de origen:");
                buscaPieza = sc.next().charAt(0) - 'a';
                if (buscaPieza < 0 || buscaPieza > 7) return false;

                contador = 0;
                for (int i = 0; i < 8; i++) {
                    pieza = tablero[i][buscaPieza];
                    if (pieza == null || pieza.color != color || pieza.tipo != tipo) continue;

                    boolean movimientoValido;

                    if (pieza.tipo == Tipo.PEON) {
                        movimientoValido = movimientoPeonValido(pieza, i, buscaPieza, fila, columna);
                    } else {
                        movimientoValido = comprobarMovimiento(pieza, i, buscaPieza, fila, columna);
                    }

                    if (movimientoValido && (!requiereColision(pieza.tipo) || caminoLibre(i, buscaPieza, fila, columna)) && (tablero[fila][columna] == null || tablero[fila][columna].color != color)) {

                        contador++;
                        x = i;
                        y = buscaPieza;
                    }
                }

                if (contador == 1) {
                    moverPieza(x, y, fila, columna);
                    return true;
                }
                return false;
            }
        }

        if (pos.length() == 2) {

            columna = pos.charAt(0) - 'a';
            fila = 8 - Character.getNumericValue(pos.charAt(1));
            tipo = Tipo.PEON;

            for (int i = 0; i < 8; i++) {
                for (int j = 0; j < 8; j++) {

                    pieza = tablero[i][j];
                    if (pieza == null || pieza.color != color || pieza.tipo != tipo) continue;

                    // movimiento del peon
                    if (movimientoPeonValido(pieza, i, j, fila, columna)) {
                        contador++;
                        x = i;
                        y = j;
                    }
                }
            }

            if (contador == 1) {
                moverPieza(x, y, fila, columna);
                return true;
            }
        }

        return false;
    }

    // movimiento peon
    private boolean movimientoPeonValido(Pieza peon, int f1, int c1, int f2, int c2) {

        int direccion;
        int filaInicial;

        if (peon.color == Color.BLANCO) {
            direccion = -1;
            filaInicial = 6;
        } else {
            direccion = 1;
            filaInicial = 1;
        }

        // avanza uno
        if (c1 == c2 && f2 == f1 + direccion && tablero[f2][c2] == null) {
            return true;
        }

        // avanza dos
        if (f1 == filaInicial && c1 == c2 && f2 == f1 + 2 * direccion && tablero[f1 + direccion][c1] == null && tablero[f2][c2] == null) {
            return true;
        }

        // captura en diagonal
        if (Math.abs(c2 - c1) == 1 && f2 == f1 + direccion && tablero[f2][c2] != null && tablero[f2][c2].color != peon.color) {
            return true;
        }

        return false;
    }


    public boolean comprobarMovimiento(Pieza pieza, int f1, int c1, int f2, int c2) {

        if (pieza.tipo == Tipo.PEON) return false;

        int dx = Math.abs(f1 - f2);
        int dy = Math.abs(c1 - c2);

        // ya no validamos peon aqui
        if (pieza.tipo == Tipo.PEON) return false;

        return switch (pieza.tipo) {
            case TORRE -> ((dx == 0 && dy != 0) || (dx != 0 && dy == 0));
            case ALFIL -> (dx == dy);
            case CABALLO -> ((dx == 2 && dy == 1) || (dx == 1 && dy == 2));
            case DAMA -> ((dx == 0 && dy != 0) || (dx != 0 && dy == 0) || (dx == dy));
            case REY -> (dx <= 1 && dy <= 1);
            default -> false;
        };
    }


    private boolean requiereColision(Tipo tipo) {
        return tipo == Tipo.TORRE || tipo == Tipo.ALFIL || tipo == Tipo.DAMA;
    }

    private boolean caminoLibre(int f1, int c1, int f2, int c2) {

        int pasoF = Integer.compare(f2, f1);
        int pasoC = Integer.compare(c2, c1);

        int f = f1 + pasoF;
        int c = c1 + pasoC;

        while (f != f2 || c != c2) {
            if (tablero[f][c] != null) return false;
            f += pasoF;
            c += pasoC;
        }
        return true;
    }


    private void moverPieza(int f1, int c1, int f2, int c2) {
        Pieza p = tablero[f1][c1];
        tablero[f1][c1] = null;
        tablero[f2][c2] = p;
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

    public boolean dentro(int f, int c) {
        return f >= 0 && f < 8 && c >= 0 && c < 8;
    }


    public void mostrar() {
        for (int fila = 0; fila < 8; fila++) {
            System.out.print((8 - fila) + " ");
            for (int columna = 0; columna < 8; columna++) {
               
                boolean esBlanca = (fila + columna) % 2 == 0;
                String fondo;

                if (esBlanca) {
                    fondo = FONDO_BLANCO;
                } else {
                    fondo = FONDO_NEGRO;
                }

                if (tablero[fila][columna] == null) {
                    System.out.print(fondo + "   " + RESET);
                } else {
                    char s = simbolo(tablero[fila][columna]);
                    System.out.print(fondo + TEXTO_NEGRO + " " + s + " " + RESET);
                }
            }
            System.out.println();
        }
        System.out.println("   a b c d e f g h");
    }


    public void limpiar() {
        for (int f = 0; f < 8; f++)
            for (int c = 0; c < 8; c++)
                tablero[f][c] = null;
    }


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
}

}
