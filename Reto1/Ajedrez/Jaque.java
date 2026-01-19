package Clase.Reto1.Ajedrez;

public class Jaque {

    private final Tablero tablero;

    public Jaque(Tablero tablero) {
        this.tablero = tablero;
    }

    // Devuelve true si el rey del color indicado está en jaque
    public boolean hayJaque(Color reyColor) {
        int reyFila = -1, reyCol = -1;
        Color atacante = (reyColor == Color.BLANCO) ? Color.NEGRO : Color.BLANCO;

        // Buscar la posición del rey
        for (int f = 0; f < 8; f++) {
            for (int c = 0; c < 8; c++) {
                Pieza p = tablero.getPieza(f, c);
                if (p != null && p.tipo == Tipo.REY && p.color == reyColor) {
                    reyFila = f;
                    reyCol = c;
                    break;
                }
            }
        }

        if (reyFila == -1) return false; // Rey no encontrado

        // Revisar si alguna pieza atacante puede moverse al rey
        for (int f = 0; f < 8; f++) {
            for (int c = 0; c < 8; c++) {
                Pieza p = tablero.getPieza(f, c);
                if (p != null && p.color == atacante) {
                    boolean puedeAtacar;

                    if (p.tipo == Tipo.PEON) {
                        puedeAtacar = movimientoPeonAtaca(p, f, c, reyFila, reyCol);
                    } else {
                        puedeAtacar = tablero.comprobarMovimiento(p, f, c, reyFila, reyCol) &&
                                (!requiereColision(p.tipo) || tablero.caminoLibre(f, c, reyFila, reyCol));
                    }

                    if (puedeAtacar) return true;
                }
            }
        }

        return false;
    }

    // Determina si un peón en (f1,c1) puede atacar a (f2,c2)
    private boolean movimientoPeonAtaca(Pieza peon, int f1, int c1, int f2, int c2) {
        int direccion = (peon.color == Color.BLANCO) ? -1 : 1;
        return (f2 == f1 + direccion) && (Math.abs(c2 - c1) == 1);
    }

    // Solo piezas que requieren camino libre
    private boolean requiereColision(Tipo tipo) {
        return tipo == Tipo.TORRE || tipo == Tipo.ALFIL || tipo == Tipo.DAMA;
    }
}