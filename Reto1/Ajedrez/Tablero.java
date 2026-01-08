package Clase.Reto1.Ajedrez;

public class Tablero {
    static final String RESET = "\u001B[0m";
    static final String FONDO_BLANCO = "\u001B[47m";
    static final String FONDO_NEGRO = "\u001B[40m";
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
        int fila = letra - 'a';
        int columna = 8 - num;

        if (!dentro(fila, columna)) return  false;

        if (tablero[fila][columna] != null) return false;

        tablero[fila][columna] = pieza;
        return true;
    }

    public boolean mover(String origen, String destino) {
        int f1 = 8 - Character.getNumericValue(origen.charAt(1));
        int c1 = origen.charAt(0) - 'a';
        int f2 = 8 - Character.getNumericValue(destino.charAt(1));
        int c2 = destino.charAt(0) - 'a';

        if (!dentro(f1, c1) || !dentro(f2, c2)) return false;

        Pieza p = tablero[f1][c1];
        if (p == null) return false;

        if (tablero[f2][c2] != null && tablero[f2][c2].color == p.color) return false;

        if (!comprobarMovimiento(tablero[f1][c1], f1, c1, f2, c2)) return false;

       tablero[f2][c2] = p;
       tablero[f1][c1] = null;
       return true;
    }

    public boolean comprobarMovimiento(Pieza pieza, int f1, int c1, int f2, int c2) {
        int dx = Math.abs(f1 - f2);
        int dy = Math.abs(c1 - c2);

        switch (pieza.tipo) {
            case PEON: return (dx == 0 && dy == 1);
            case TORRE: return ((dx == 0 && dy != 0)||(dx != 0 && dy == 0));
            case ALFIL: return (dx == dy);
            case CABALLO: return ((dx == 2 && dy == 1) || (dx == 1 && dy == 2));
            case DAMA: return ((dx == 0 && dy != 0)||(dx != 0 && dy == 0) || (dx == dy));
            case REY: return ((dx == dy) || (dx == 0 && dy == 1) || (dx == 1 && dy == 0));
            default: return false;
        }
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
                String texto = esBlanca ? TEXTO_NEGRO : TEXTO_BLANCO;

                if (tablero[fila][columna] == null) {
                    System.out.print(fondo + texto + "   " + RESET);
                } else {
                    char s = simbolo(tablero[fila][columna]);
                    System.out.print(fondo + texto + "   " + s + "   " + RESET);
                }
            }
            System.out.println();
        }
        System.out.println("   a  b  c  d  e  f  g  h");
    }
}
