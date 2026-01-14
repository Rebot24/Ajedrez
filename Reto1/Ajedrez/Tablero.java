package Clase.Reto1.Ajedrez;

public class Tablero {

    static final String RESET = "\u001B[0m";
    static final String FONDO_BLANCO = "\u001B[47m";
    static final String FONDO_NEGRO = "\u001B[40m";
    static final String TEXTO_BLANCO = "\u001B[37m";
    static final String TEXTO_NEGRO = "\u001B[30m";

    private Pieza[][] tablero = new Pieza[8][8];

    //Métodos internos

    // convierte posición tipo "e4" a coordenadas [fila, columna]
    private int[] convertirPosicion(String posicion) {
        int fila = 8 - Character.getNumericValue(posicion.charAt(1));
        int columna = posicion.charAt(0) - 'a';
        return new int[]{fila, columna};
    }

    private boolean dentro(int f, int c) {
        return f >= 0 && f < 8 && c >= 0 && c < 8;
    }

    private char simbolo(Pieza p) {
        if (p.color == Color.BLANCO) {
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


    public boolean colocarPieza(Pieza pieza, String posicion) {

        if (posicion == null || posicion.length() != 2) {
            return false;
        }

        char columnaChar = posicion.charAt(0);
        char filaChar = posicion.charAt(1);

        if (columnaChar < 'a' || columnaChar > 'h') {
            return false;
        }

        if (filaChar < '1' || filaChar > '8') {
            return false;
        }

        int fila = 8 - Character.getNumericValue(filaChar);
        int columna = columnaChar - 'a';

        if (tablero[fila][columna] != null) {
            return false;
        }

        tablero[fila][columna] = pieza;
        return true;
    }


    // no valida movimiento según reglas del ajedrez
    // solo comprueba la básica del tablero
    public boolean mover(String origen, String destino) {
        int[] o = convertirPosicion(origen);
        int[] d = convertirPosicion(destino);

        int f1 = o[0];
        int c1 = o[1];
        int f2 = d[0];
        int c2 = d[1];

        if (!dentro(f1, c1) || !dentro(f2, c2)) return false;

        Pieza p = tablero[f1][c1];
        if (p == null) return false;

        if (tablero[f2][c2] != null && tablero[f2][c2].color == p.color) {
            return false;
        }

        tablero[f2][c2] = p;
        tablero[f1][c1] = null;
        return true;
    }

    public void mostrarTablero() {
        for (int fila = 0; fila < 8; fila++) {
            System.out.print((8 - fila) + " ");
            for (int columna = 0; columna < 8; columna++) {
                boolean esBlanca = (fila + columna) % 2 == 0;
                String fondo = esBlanca ? FONDO_BLANCO : FONDO_NEGRO;
                String texto = esBlanca ? TEXTO_NEGRO : TEXTO_BLANCO;

                if (tablero[fila][columna] == null) {
                    System.out.print(fondo + texto + "   " + RESET);
                } else {
                    char s = simbolo(tablero[fila][columna]);
                    System.out.print(fondo + texto + " " + s + " " + RESET);
                }

            }
            System.out.println();
        }
        System.out.println("   a  b  c  d  e  f  g  h");
    }

    // devuelve la pieza en una posición o null si esta vacía
    public Pieza obtenerPieza(String posicion) {
        int[] coord = convertirPosicion(posicion);
        int fila = coord[0];
        int columna = coord[1];

        if (!dentro(fila, columna)) {
            return null;
        }

        return tablero[fila][columna];
    }

    // vaciar tablero
    public void limpiar() {
        for (int f = 0; f < 8; f++) {
            for (int c = 0; c < 8; c++) {
                tablero[f][c] = null;
            }
        }
    }

    //existe el rey del color

    public boolean hayRey(Color color) {
        for (int f = 0; f < 8; f++) {
            for (int c = 0; c < 8; c++) {
                Pieza p = tablero[f][c];
                if (p != null) {
                    if (p.tipo == Tipo.REY && p.color == color) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    //compruebat si la composición minima del tablero es válida
    // un rey blanco y uno negro
    public boolean composicionMinimaValida() {
        boolean reyBlanco = false;
        boolean reyNegro = false;

        for (int f = 0; f < 8; f++) {
            for (int c = 0; c < 8; c++) {
                Pieza p = tablero[f][c];
                if (p != null && p.tipo == Tipo.REY) {
                    if (p.color == Color.BLANCO) {
                        if (reyBlanco) return false;
                        reyBlanco = true;
                    } else {
                        if (reyNegro) return false;
                        reyNegro = true;
                    }
                }
            }
        }

        return reyBlanco && reyNegro;
    }

}
